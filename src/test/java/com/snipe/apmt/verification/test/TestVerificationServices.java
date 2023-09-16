
package com.snipe.apmt.verification.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import com.snipe.apmt.admin.DAO.CategoryRepository;
import com.snipe.apmt.admin.domain.CategoryDomain;
import com.snipe.apmt.admin.service.CategoryService;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderMapper;
import com.snipe.apmt.uploader.service.UploaderService;
import com.snipe.apmt.verification.service.IVerificationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestVerificationServices {

	@Autowired
	UploaderService uploaderService;

	@MockBean
	UploaderRepository uploaderRepository;

	@Autowired
	CategoryService categoryService;

	@MockBean
	CategoryRepository categoryRepository;

	@Autowired
	UploaderMapper uploaderMapper;

	@Autowired
	IVerificationService verificationService;

	private List<UploaderDomain> projectList;

	private UploaderDomain uploaderOne, uploaderTwo, uploaderThree;

	private List<CategoryDomain> categoryList;

	private CategoryDomain category1, category2;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);

		projectList = new ArrayList<UploaderDomain>();

		uploaderOne = new UploaderDomain(1, 1005, "DS", "DS", "DS", "DS", "DS", 500, "DS", "DS", 100, 0, "DS", "10456",
				LocalDateTime.now(), LocalDateTime.now(), 0,"DS");
		uploaderTwo = new UploaderDomain(2, 1006, "DS", "DS", "DS", "DS", "DS", 500, "DS", "DS", 101, 1, "DS", "10457",
				LocalDateTime.now(), LocalDateTime.now(), 0,"DS");
		uploaderThree = new UploaderDomain(3, 1007, "Database", "DS", "DS", "DS", "DS", 500, "DS", "DS", 100, 0, "DS",
				"10458", LocalDateTime.now(), LocalDateTime.now(), 0,"DS");

		projectList.add(uploaderOne);
		projectList.add(uploaderTwo);
		projectList.add(uploaderThree);

		categoryList = new ArrayList<CategoryDomain>();

		category1 = new CategoryDomain(1, 100, "Computer", LocalDateTime.now(), LocalDateTime.now());
		category2 = new CategoryDomain(2, 101, "IOT", LocalDateTime.now(), LocalDateTime.now());

		categoryList.add(category1);
		categoryList.add(category2);

	}

	@Test
	public void test_getAllProjects() {

		when(uploaderRepository.findAll()).thenReturn(projectList);

		try {
			assertEquals(3, uploaderService.getProjectList().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_getNewProjects() {

		when(uploaderRepository.findProjectsByStatus(0)).thenReturn(Stream.of(uploaderOne, uploaderThree).toList());

		try {
			assertEquals(2, verificationService.getNewProjects().size());
			assertEquals(1007, verificationService.getNewProjects().get(1).getProjectId());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_getVerifiedProjects() {

		when(uploaderRepository.findProjectsByStatus(1)).thenReturn(Stream.of(uploaderTwo).toList());

		try {
			assertEquals(1, verificationService.getVerifiedProjects().size());
			assertEquals(1006, verificationService.getVerifiedProjects().get(0).getProjectId());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_getProjectCategories() {

		when(categoryRepository.findAll()).thenReturn(categoryList);

		try {
			assertEquals(2, categoryService.getCategoryList().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_getProjectsByCategoryId() {

		when(uploaderRepository.getProjectsListByCategoryId(100))
				.thenReturn(Stream.of(uploaderOne, uploaderThree).toList());

		try {

			assertEquals(2, verificationService.getProjectsListByCategoryId(100).size());
			assertEquals("DS", verificationService.getProjectsListByCategoryId(100).get(0).getTitle());
			assertEquals("Database", verificationService.getProjectsListByCategoryId(100).get(1).getTitle());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_getProjectDetailsById() {

		when(uploaderRepository.findProjectByProjectId(1005)).thenReturn(uploaderOne);

		try {

			assertEquals(10456, uploaderService.getProjectByProjectId(1005).getUploaderId());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Yet to refine

	/*
	 * @Test public void test_verifyProject() {
	 * 
	 * MockHttpSession session = new MockHttpSession();
	 * 
	 * UserDomain user = new UserDomain(); user.setFirstName("Christi");
	 * user.setLastName("V");
	 * 
	 * session.setAttribute("userModel", user);
	 * 
	 * MockHttpServletRequest request = new MockHttpServletRequest();
	 * request.setSession(session);
	 * 
	 * when(uploaderRepository.findProjectByProjectId(1005)).thenReturn(uploaderOne)
	 * ;
	 * 
	 * try {
	 * 
	 * String verifyProject = verificationService.verifyProject(1005, true);
	 * System.out.println("verifyProject" + verifyProject);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
}
