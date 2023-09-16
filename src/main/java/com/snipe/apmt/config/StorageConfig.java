package com.snipe.apmt.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {
	
	
	@Value("${cloud.linode.credentials.access-key}")
	private String accessKey;
	
	@Value("${cloud.linode.credentials.secret-key}")
	private String accessSecret;
	
	@Value("${cloud.linode.region.static}")
	private String region;

	@Bean
	public AmazonS3 linodeClient() {
	    AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
	    return AmazonS3ClientBuilder
	            .standard()
	            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
	                    "https://" + region + ".linodeobjects.com", region))
	            .withCredentials(new AWSStaticCredentialsProvider(credentials))
	            .build();
	}
}
