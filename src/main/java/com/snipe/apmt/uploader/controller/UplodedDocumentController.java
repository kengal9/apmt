package com.snipe.apmt.uploader.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.uploader.service.UplodedDocumentService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class UplodedDocumentController {

	@Autowired
	UplodedDocumentService uplodedDocumentService;
	
	
//	medeiaTpe should "image" or "video"
	@GetMapping(value = "/projectDoc/{projectId}/{mediaType}")
	public ResponseEntity<GenericRes> getProjectImageViedoById(@PathVariable("projectId") long projectId, @PathVariable("mediaType") String mediaType) throws Exception {
		return prepareSuccessResponse(uplodedDocumentService.getProjectImageById(projectId, mediaType));
	}
	
}
