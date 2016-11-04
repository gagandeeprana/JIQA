package com.jiqa.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jiqa.model.Failed;
import com.jiqa.model.Success;

public class HelperUtil {

	public Object getErrorJSONString(String code, String message) throws JsonGenerationException, JsonMappingException, IOException {
		Failed error = new Failed(code, message);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(error);
		return new ResponseEntity<Object>(json, HttpStatus.BAD_REQUEST);
	}
	
	public Object getSuccessJSONString(String code, String message) throws JsonGenerationException, JsonMappingException, IOException {
		Success success = new Success(code, message);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(success);
		return new ResponseEntity<Object>(json, HttpStatus.OK);
	}
	
	public boolean checkValue(String val) {
		if(val != null && !"".equalsIgnoreCase(val) && val.length() > 0 && !"null".equalsIgnoreCase(val)) {
			return true;
		}
		return false;
	}
}
