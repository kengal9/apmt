package com.snipe.apmt.verification.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.snipe.apmt.verification.controller.VerificationController;

@SpringBootTest
public class TestVerificationController {

	@Autowired
	VerificationController verificationController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(verificationController).isNotNull();
	}

}
