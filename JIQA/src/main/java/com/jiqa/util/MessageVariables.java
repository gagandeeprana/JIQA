package com.jiqa.util;

import org.springframework.beans.factory.annotation.Value;

public class MessageVariables {

	@Value("${request_invalid_code}")
	public String requestInvalidCode;
	
	@Value("${request_invalid_message}")
	public String requestInvalidMessage;
	
	@Value("${category_added_code}")
	public String categoryAddedCode;
	
	@Value("${category_added_message}")
	public String categoryAddedMessage;
	
	@Value("${status_not_valid_code}")
	public String statusNotValidCode;
	
	@Value("${status_not_valid_message}")
	public String statusNotValidMessage;
	
	@Value("${category_title_not_valid_code}")
	public String categoryTitleNotValidCode;
	
	@Value("${category_title_not_valid_message}")
	public String categoryTitleNotValidMessage;
	
	@Value("${category_id_invalid_code}")
	public String categoryIdInvalidCode;
	
	@Value("${category_id_invalid_message}")
	public String categoryIdInvalidMessage;
	
	@Value("${failed_to_add_category_code}")
	public String failedToAddCategoryCode;
	
	@Value("${failed_to_add_category_message}")
	public String failedToAddCategoryMessage;

	@Value("${category_deleted_code}")
	public String categoryDeletedCode;
	
	@Value("${category_deleted_message}")
	public String categoryDeletedMessage;

	@Value("${failed_to_delete_category_code}")
	public String failedToDeleteCategoryCode;
	
	@Value("${failed_to_delete_category_message}")
	public String failedToDeleteCategoryMessage;

	@Value("${no_such_category_exists_code}")
	public String noSuchCategoryExistsCode;
	
	@Value("${no_such_category_exists_message}")
	public String noSuchCategoryExistsMessage;

	@Value("${category_updated_code}")
	public String categoryUpdatedCode;
	
	@Value("${category_updated_message}")
	public String categoryUpdatedMessage;
}
