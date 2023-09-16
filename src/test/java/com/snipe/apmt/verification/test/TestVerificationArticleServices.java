 package com.snipe.apmt.verification.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.amazonaws.services.s3.AmazonS3Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snipe.apmt.admin.DAO.CategoryRepository;
import com.snipe.apmt.admin.domain.CategoryDomain;
import com.snipe.apmt.admin.service.CategoryService;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.dao.UserLogRepository;
import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.domain.UserLogDomain;
import com.snipe.apmt.mapper.UserMapper;
import com.snipe.apmt.service.UserService;

import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.mapper.UploaderArticleMapper;
import com.snipe.apmt.uploader.service.UploaderArticleService;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.verification.dao.VerificationArticleRepository;
import com.snipe.apmt.verification.domain.VerificationArticleDomain;
import com.snipe.apmt.verification.service.VerificationArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestVerificationArticleServices {

	@Autowired
	UploaderArticleService uploaderArticleService;

	@MockBean
	UploaderArticleRepository uploadedArticleRepository;

	@Autowired
	CategoryService categoryService;

	@MockBean
	CategoryRepository categoryRepository;

	@Autowired
	VerificationArticleService verificationArticleService;

	@MockBean
	VerificationArticleRepository verificationArticleRepository;

	@MockBean
	UserLogRepository userLogRepository;

	private List<UploaderArticleDomain> articleList;

	private UploaderArticleDomain uploaderOne, uploaderTwo, uploaderThree;

	private List<CategoryDomain> categoryList;

	private CategoryDomain category1, category2;

	private UserDomain userDomain;

	private VerificationArticleDomain verificationArticleDomain;

	private UserLogDomain userLogDomain;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

		articleList = new ArrayList<UploaderArticleDomain>();

		uploaderOne = new UploaderArticleDomain(1, 1005, "DS", "DS", "DS", "DS", "DS", 500, "DS", 100, 0, "DS",
				"10456", LocalDateTime.now(), LocalDateTime.now(), 0,"DS");
		uploaderTwo = new UploaderArticleDomain(2, 1006, "DS", "DS", "DS", "DS", "DS", 500, "DS", 100, 0, "DS",
				"10457", LocalDateTime.now(), LocalDateTime.now(), 0,"DS");
		uploaderThree = new UploaderArticleDomain(3, 1007, "DS", "DS", "DS", "DS", "DS", 500, "DS", 100, 0, "DS",
				"10458", LocalDateTime.now(), LocalDateTime.now(), 0,"DS");

		articleList.add(uploaderOne);
		articleList.add(uploaderTwo);
		articleList.add(uploaderThree);

		categoryList = new ArrayList<CategoryDomain>();

		category1 = new CategoryDomain(1, 100, "Computer", LocalDateTime.now(), LocalDateTime.now());
		category2 = new CategoryDomain(2, 101, "IOT", LocalDateTime.now(), LocalDateTime.now());

		categoryList.add(category1);
		categoryList.add(category2);

		userDomain = new UserDomain("1001", "DS", "Ds", 1234567890L, 1234567890L, "DS",
				new AddressDomain("1", "1001", 1, "address1", "address2", "101", "17", "5", new BigInteger("560064"),
						new BigInteger("456"), "street", LocalDateTime.now(), LocalDateTime.now(), true),
				new BankDomain(), new RoleDomain(1, "ADMIN", null, null, true, null), null, "01-01-1955", 1,
				new BigInteger("20282738393"), "app", null, LocalDateTime.now(), LocalDateTime.now(), true, false,
				null);

		verificationArticleDomain = new VerificationArticleDomain(1L, 1L, "DS", 1, "Verified Article",
				LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

		userLogDomain = new UserLogDomain(1, "1001", "DS", "DS", 1234567890L, 1234567890L, "DS", "DS", 1,
				new BigInteger("23451456"), 1, "1001", "1", LocalDateTime.now(), LocalDateTime.now(), false, null);
	}

	@Test
	public void test_getAllArticels() {

		when(uploadedArticleRepository.findAll()).thenReturn(articleList);

		try {
			assertEquals(3, uploaderArticleService.getListByArticleId().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_getNewArticles() {

		when(uploadedArticleRepository.findArticlesByStatus(0))
				.thenReturn(Stream.of(uploaderOne, uploaderThree).toList());

		try {
			assertEquals(2, verificationArticleService.getNewArticles().size());
			assertEquals(1007, verificationArticleService.getNewArticles().get(1).getArticleId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_getVerifiedArticles() {

		when(uploadedArticleRepository.findArticlesByStatus(1)).thenReturn(Stream.of(uploaderTwo).toList());

		try {
			assertEquals(1, verificationArticleService.getVerifiedArticles().size());
			assertEquals(1006, verificationArticleService.getVerifiedArticles().get(0).getArticleId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_getArticleCategories() {

		when(categoryRepository.findAll()).thenReturn(categoryList);

		try {
			assertEquals(2, categoryService.getCategoryList().size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_getArticlesByCategoryId() {

		when(uploadedArticleRepository.getArticlesListByCategoryId(100))
				.thenReturn(Stream.of(uploaderOne, uploaderThree).toList());

		try {
			assertEquals(2, verificationArticleService.getArticlesListByCategoryId(100).size());
			assertEquals("DS", verificationArticleService.getArticlesListByCategoryId(100).get(0).getTitle());
			assertEquals("DS", verificationArticleService.getArticlesListByCategoryId(100).get(1).getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_getArticleDetailsById() {

		when(uploadedArticleRepository.getByArticleId(1005)).thenReturn(uploaderOne);

		try {
			assertEquals(10456, uploaderArticleService.getArticleByArticleId(1005).getUploaderId());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	@Test
//	public void test_verifyArticle() throws Exception {
//
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//		HttpSession session = attr.getRequest().getSession(true);
//		session.setAttribute("userModel", userDomain);
//
//		try (MockedStatic<CommonUtils> mockedStatic = Mockito.mockStatic(CommonUtils.class)) {
//			mockedStatic.when(() -> CommonUtils.geSsession()).thenReturn(session);
//		}
//
//		when(uploadedArticleRepository.getByArticleId(1005)).thenReturn(uploaderOne);
//		when(userLogRepository.save(userLogDomain)).thenReturn(userLogDomain);
//		when(verificationArticleRepository.save(verificationArticleDomain)).thenReturn(verificationArticleDomain);
//		assertEquals("Article Verified", verificationArticleService.verifyArticle(1005, true));
//	}
}
