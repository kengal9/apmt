package com.snipe.apmt.uploader.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.amazonaws.services.s3.AmazonS3Client;
import com.snipe.apmt.admin.domain.CategoryDomain;
import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.domain.UserDomain;

import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.uploader.service.UploaderArticleService;
import com.snipe.apmt.utils.CommonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUploaderArticleService {

	@MockBean
	UploaderArticleRepository uploadedArticleRepository;

	@Autowired
	UploaderArticleService uploaderArticleService;
	

	private List<UploaderArticleDomain> articleList;

	private UploaderArticleDomain uploaderOne, uploaderTwo, uploaderThree;

	private List<CategoryDomain> categoryList;

	private CategoryDomain category1, category2;
	private UserDomain userDomain;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

		articleList = new ArrayList<UploaderArticleDomain>();

		uploaderOne = new UploaderArticleDomain(1, 1005, "DS", "DS", "DS", "DS", "DS", 500, "DS", 100, 0, "DS",
				"10456", LocalDateTime.now(), LocalDateTime.now(), 0,"DS");
		uploaderTwo = new UploaderArticleDomain(2, 1006, "DS", "DS", "DS", "DS", "Project/Image/222_img_download.jpg", 500, "DS", 100, 0, "DS",
				"10457", LocalDateTime.now(), LocalDateTime.now(), 0,"DS");
		uploaderThree = new UploaderArticleDomain(3, 1007, "DS", "DS", "DS", "DS", "Project/Image/222_img_download.jpg", 500, "DS", 100, 0, "DS",
				"10458", LocalDateTime.now(), LocalDateTime.now(), 0,"DS");

		categoryList = new ArrayList<CategoryDomain>();

		category1 = new CategoryDomain(1, 100, "Computer", LocalDateTime.now(), LocalDateTime.now());
		category2 = new CategoryDomain(2, 101, "IOT", LocalDateTime.now(), LocalDateTime.now());

		categoryList.add(category1);
		categoryList.add(category2);

		userDomain = new UserDomain("1001", "DS", "Ds", 1234567890L, 1234567890L, "DS",
				new AddressDomain("1", "1001", 1, "address1", "address2", "101", "17", "5", new BigInteger("560064"),
						new BigInteger("456"), "street", LocalDateTime.now(), LocalDateTime.now(), true),
				new BankDomain(), new RoleDomain(1, "ADMIN", null, null, true, null),
				null, "01-01-1955", 1,
				new BigInteger("20282738393"), "app", null, LocalDateTime.now(), LocalDateTime.now(), true, false,
				null);

	}

	@Test
	public void test_saveArticle() {
		
		ServletRequestAttributes attr =(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("userModel", userDomain);

		try (MockedStatic<CommonUtils> mockedStatic = Mockito.mockStatic(CommonUtils.class)) {
			mockedStatic.when(() -> CommonUtils.geSsession()).thenReturn(session);
		}

		when(uploadedArticleRepository.save(uploaderOne)).thenReturn(uploaderOne);
		UploaderArticleModel uploaderArticleModel = new UploaderArticleModel();
		BeanUtils.copyProperties(uploaderOne, uploaderArticleModel);
		
		MockMultipartFile multipartFile=new MockMultipartFile("image", "image".getBytes());
		
		
		try {
			assertEquals("Article saved", uploaderArticleService.saveArticle(uploaderArticleModel, multipartFile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void test_getArticleByArticleId() {

		when(uploadedArticleRepository.getByArticleId(1)).thenReturn(uploaderTwo);

		try {
			assertEquals("10456", uploaderArticleService.getArticleByArticleId(1005).getUploaderId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_updateArticle() {

		when(uploadedArticleRepository.save(uploaderOne)).thenReturn(uploaderOne);
		UploaderArticleModel uploaderArticleModel = new UploaderArticleModel();
		BeanUtils.copyProperties(uploaderOne, uploaderArticleModel);

		try {
			assertEquals("article updated", uploaderArticleService.updateArticle(uploaderArticleModel));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void test_deleteByArticleId() {

		when(uploadedArticleRepository.getByArticleId(1)).thenReturn(uploaderOne);

		assertEquals("deleted", uploaderArticleService.deleteByArticleId(1));

	}

	@Test
	public void test_getListByArticleId() {

		when(uploadedArticleRepository.findAll()).thenReturn(articleList);

		assertEquals(articleList, uploaderArticleService.getListByArticleId());

	}

}
