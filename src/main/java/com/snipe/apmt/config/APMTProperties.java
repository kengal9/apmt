package com.snipe.apmt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties("apmt")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APMTProperties {

	private String apmtUrl;
	private String accessTokenKey;
	private String headerApiKey;


	public String getAccessTokenKey() {
		return accessTokenKey;
	}

	public void setAccessTokenKey(String accessTokenKey) {
		this.accessTokenKey = accessTokenKey;
	}

	public String getHeaderApiKey() {
		return headerApiKey;
	}

	public void setHeaderApiKey(String headerApiKey) {
		this.headerApiKey = headerApiKey;
	}
	

}
