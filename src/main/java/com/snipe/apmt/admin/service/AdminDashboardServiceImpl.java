package com.snipe.apmt.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.uploader.dao.UploaderBookRepository;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderArticleMapper;
import com.snipe.apmt.uploader.mapper.UploaderBookMapper;
import com.snipe.apmt.uploader.mapper.UploaderMapper;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.uploader.model.UploaderModel;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

	@Autowired
	UploaderRepository uploaderRepository;

	@Autowired
	UploaderMapper uploaderMapper;
	
	@Autowired
	UploaderBookMapper uploaderBookMapper;
	
	@Autowired
	UploaderBookRepository uploaderBookRepository;
	
	@Autowired
	UploaderArticleMapper uploaderArticleMapper;
	
	@Autowired
	UploaderArticleRepository uploaderArticleRepository;

	@Override
	public List<UploaderModel> listNewProject() {
		List<UploaderDomain> listofAllProject = uploaderRepository.findAll();
		List<UploaderDomain> listNewProject = listofAllProject.stream().filter((project) -> project.getStatus() == 0)
				.collect(Collectors.toList());

		List<UploaderModel> entityList = uploaderMapper.entityList(listNewProject);
		return entityList;
	}

	@Override
	public List<UploaderModel> listOldProject() {
		List<UploaderDomain> listofAllProject = uploaderRepository.findAll();
		List<UploaderDomain> listOldProject = listofAllProject.stream()
				.filter((project) -> project.getStatus() == 1 || project.getStatus() == 2 || project.getStatus() == 3)
				.collect(Collectors.toList());

		List<UploaderModel> entityList = uploaderMapper.entityList(listOldProject);
		return entityList;
	}

	@Override
	public Map<String, Long> countOfRecords() {
		Map<String, Long> count = new HashMap<String, Long>();

		List<UploaderDomain> listofAllProject = uploaderRepository.findAll();
		long countNewProject = listofAllProject.stream().filter((project) -> project.getStatus() == 0).count();
		long countOldProject = listofAllProject.stream()
				.filter((project) -> project.getStatus() == 1 || project.getStatus() == 2 || project.getStatus() == 3)
				.count();
		count.put("newProjects", countNewProject);
		count.put("oldProjects", countOldProject);

		return count;
	}
	
	
	public List<UploaderBookModel> listNewBook() {
		List<UploaderBookDomain> listofAllBook = uploaderBookRepository.findAll();
		List<UploaderBookDomain> listNewBook = listofAllBook.stream().filter((book) -> book.getStatus() == 0)
				.collect(Collectors.toList());

		List<UploaderBookModel> entityList = uploaderBookMapper.entityList(listNewBook);
		return entityList;
	}

	
	public List<UploaderBookModel> listOldBook() {		
		List<UploaderBookDomain> listofAllBook = uploaderBookRepository.findAll();
		List<UploaderBookDomain> listOldBook = listofAllBook.stream()
				.filter((book) -> book.getStatus() == 1 || book.getStatus() == 2 || book.getStatus() == 3)
				.collect(Collectors.toList());

		List<UploaderBookModel> entityList = uploaderBookMapper.entityList(listOldBook);
		return entityList;
	}

	
	public Map<String, Long> countOfBookRecords() {
		
		Map<String, Long> count = new HashMap<String, Long>();
		List<UploaderBookDomain> listofAllBook = uploaderBookRepository.findAll();
		long countNewBook = listofAllBook.stream().filter((book) -> book.getStatus() == 0).count();
		long countOldBook = listofAllBook.stream()
				.filter((book) -> book.getStatus() == 1 || book.getStatus() == 2 || book.getStatus() == 3)
				.count();
		count.put("newBooks", countNewBook);
		count.put("oldBooks", countOldBook);
		return count;
	}

	@Override
	public List<UploaderArticleModel> listNewArticle() {
		List<UploaderArticleDomain> listofAllArticle = uploaderArticleRepository.findAll();
		List<UploaderArticleDomain> listNewArticle = listofAllArticle.stream().filter((article) -> article.getStatus() == 0)
				.collect(Collectors.toList());
		List<UploaderArticleModel> entityList = uploaderArticleMapper.entityList(listNewArticle);
		return entityList;		
	}

	@Override
	public List<UploaderArticleModel> listOldArticle() {		
		List<UploaderArticleDomain> listofAllArticle = uploaderArticleRepository.findAll();
		List<UploaderArticleDomain> listOldArticle = listofAllArticle.stream()
				.filter((article) -> article.getStatus() == 1 || article.getStatus() == 2 || article.getStatus() == 3)
				.collect(Collectors.toList());
		List<UploaderArticleModel> entityList = uploaderArticleMapper.entityList(listOldArticle);
		return entityList;
	}

	@Override
	public Map<String, Long> countOfArticleRecords() {
		Map<String, Long> count = new HashMap<String, Long>();
		List<UploaderArticleDomain> listofAllArticle = uploaderArticleRepository.findAll();
		long countNewArticle = listofAllArticle.stream().filter((article) -> article.getStatus() == 0).count();
		long countOldArticle = listofAllArticle.stream()
				.filter((article) -> article.getStatus() == 1 || article.getStatus() == 2 || article.getStatus() == 3)
				.count();
		count.put("newArticles", countNewArticle);
		count.put("oldArticles", countOldArticle);
		return count;
	}
	}

