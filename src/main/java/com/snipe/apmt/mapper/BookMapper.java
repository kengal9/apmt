package com.snipe.apmt.mapper;

import com.snipe.apmt.domain.BookDomain;
import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.model.BookModel;
import com.snipe.apmt.model.ProjectModel;

public class BookMapper extends AbstractModelMapper<BookModel, BookDomain> {

	@Override
	public Class<BookModel> entityType() {
			return BookModel.class;
	}

	@Override
	public Class<BookDomain> modelType() {
			return BookDomain.class;
	}

}
