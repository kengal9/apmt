package com.snipe.apmt.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.snipe.apmt.common.Status;
import com.snipe.apmt.config.APMTProperties;
import com.snipe.apmt.constants.Constants;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.utils.CommonUtils;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@ComponentScan(basePackages = { "com.snipe.apmt" })
@SuppressWarnings("rawtypes")
 public class APMTFilter implements Constants, Filter {

	@Autowired
	APMTProperties APMTProperties;

	@Autowired
	JwtToken jwtToken;


	private static final Logger logger = LoggerFactory.getLogger(APMTFilter.class);

	@SuppressWarnings("unused")
	public void init(FilterConfig config) throws ServletException {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(config.getServletContext());
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("In doFilter");
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		// response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,X-API-KEY,X-API-TOKEN");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addIntHeader("Access-Control-Max-Age", 3600);
		response.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		String url = request.getRequestURL().toString();

		String mobileNumber = null, role = null, deleteProfile = null;
		if (url.contains("swagger") || url.contains("api-docs")
				|| request.getRequestURI().equals(request.getContextPath() + "/health") || url.contains("/save/profile")
				|| url.contains("/save/user") || url.contains("/oauth2/login") || url.contains("/countryList")||url.contains("/stateList")
				|| url.contains("/state/List") || url.contains("/cityList") || url.contains("/genderList")
				|| url.contains("/admin/bankList") || url.contains("/businessRoleList")
				|| url.contains("/employeeRoleList") || url.contains("/download") || url.contains("/projectList")
				|| url.contains("/booksList") || url.contains("/articleList") || url.contains("/categoryList")
				|| url.contains("/projectList/{projectId}") || url.contains("/booksList/{bookId}")
				|| url.contains("/getArticle/{articleId}")|| url.contains("/approvedProjects")|| url.contains("/approvedBooks")
				|| url.contains("/approvedArticles")|| url.contains("/admin/bankList")||url.contains("/generateOtp")
				||url.contains("/validateOtp")) {
			chain.doFilter(request, response);
		} else {
			String xApiToken = request.getHeader(ACCESS_TOKEN_HEADER);
			String xApiKey = request.getHeader(ACCESS_KEY_HEADER);
			String jsonResponse = null;
			APMTProperties.setAccessTokenKey(xApiToken);
			if (url.contains("/getMobileNumber/")) {
				mobileNumber = request.getRequestURI().substring(request.getContextPath().length());
			}
			if (url.contains("/role/")) {
				role = request.getRequestURI().substring(request.getContextPath().length());
			}
			if (url.contains("/deleteAuthProfile/")) {
				deleteProfile = request.getRequestURI().substring(request.getContextPath().length());
			}
			if (url.contains("/deleteAuthProfile/")) {
				deleteProfile = request.getRequestURI().substring(request.getContextPath().length());
			}
			APMTProperties.setAccessTokenKey(xApiToken);
			if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
				jsonResponse = validateRequest(xApiKey);
			}

//			APMTProperties.setAccessTokenKey(xApiToken);
			String requestPath = request.getRequestURI().substring(request.getContextPath().length());
			if (jsonResponse == null && !request.getMethod().equalsIgnoreCase("OPTIONS")) {
				String xByPassToken = request.getHeader(BY_PASS_TOKEN);
				if (!"Yes".equalsIgnoreCase(xByPassToken))
					jsonResponse = validateApiToken(xApiToken, requestPath,
							Arrays.asList("/v1/user/login", "/v1/login", "/v1/logout", "/v1/verifyMobileId",
									"/v1/getSmsOtp", "/v1/profile", mobileNumber, "/v1/forgotPassword/verfiyMobileId",
									role, "/v1/companyProfile2", deleteProfile, "/v1/updatePassword",
									"/v1/viewDashboardcustomer", "/v1/profile/forgotPassword/verfiyMobileId",
									"/v1/session/last/access/time"), request);
			}

			if (jsonResponse != null) {
				response.setStatus(401);
				response.getWriter().println(jsonResponse);
			} else {
				chain.doFilter(request, response);
				return;
			}
		}
	}

	public void destroy() {
	}

	public String validateApiToken(String xApiToken, String requestPath, List<String> exludePathList, HttpServletRequest request) {
		GenericRes response = new GenericRes();
		if (!exludePathList.contains(requestPath)) {
			boolean isInvalid = true;
			try {
				isInvalid = (null == xApiToken || !jwtToken.isValidToken(xApiToken, requestPath));
			} catch (Exception e) {
				isInvalid = true;
			}

			if (isInvalid) {
				response.setExCode("INVALID_HEADER_API_TOKEN");
				response.setResCode(1012);
				response.setMessage("Invalid Header Api Token");
				response.setStatus(Status.FAILURE);
				return CommonUtils.getJson(response);
			}
		}
		return null;
	}

	public String validateRequest(String xApiKey) {
		GenericRes response = new GenericRes();
		if (!APMTProperties.getHeaderApiKey().equalsIgnoreCase(xApiKey)) {
			response.setExCode("INVALID_HEADER_API_KEY");
			response.setResCode(1011);
			response.setMessage("Invalid Header Api Key");
			response.setStatus(Status.FAILURE);
			return CommonUtils.getJson(response);
		}
		return null;
	}
}
