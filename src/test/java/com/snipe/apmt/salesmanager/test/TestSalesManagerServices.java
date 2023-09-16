package com.snipe.apmt.salesmanager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
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
import org.springframework.test.context.junit4.SpringRunner;

import com.snipe.apmt.admin.DAO.CategoryRepository;
import com.snipe.apmt.admin.domain.CategoryDomain;
import com.snipe.apmt.admin.service.CategoryService;
import com.snipe.apmt.domain.AddressDomain;
import com.snipe.apmt.domain.BankDomain;
import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.salesmanager.service.SalesManagerService;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderMapper;
import com.snipe.apmt.uploader.service.UploaderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSalesManagerServices {
	

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
	SalesManagerService salesmanagerservice ;
	
	
    private List<UploaderDomain> projectList;

	private UploaderDomain uploaderOne, uploaderTwo, uploaderThree;

	private List<CategoryDomain> categoryList;

	private CategoryDomain category1, category2;
	
	private UserDomain userDomain;
	
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
        
		userDomain = new UserDomain("1001", "DS", "Ds", 1234567890L, 1234567890L, "DS",
				new AddressDomain("1", "1001", 1, "address1", "address2", "101", "17", "5", new BigInteger("560064"),
						new BigInteger("456"), "street", LocalDateTime.now(), LocalDateTime.now(), true),
				new BankDomain(), new RoleDomain(1, "ADMIN", null, null, true, null),
				null, "01-01-1955", 1,
				new BigInteger("20282738393"), "app", null, LocalDateTime.now(), LocalDateTime.now(), true, false,
				null);
	}
	
	@Test
	public void test_getallSalesProjects() {

		when(uploaderRepository.findAll()).thenReturn(projectList);

		try {
			assertEquals(3, uploaderService.getProjectList().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test_getNewProjects() {

		when(uploaderRepository.findProjectsByStatus(2)).thenReturn(Stream.of(uploaderOne, uploaderThree).toList());

		try {
			assertEquals(2, salesmanagerservice.salesNewProjects().size());
			assertEquals(1007, salesmanagerservice.salesNewProjects().get(1).getProjectId());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
