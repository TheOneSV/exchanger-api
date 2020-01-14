package com.yotaku.exchanger.exchangerapi.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).setSerializationInclusion(Include.NON_NULL);

	public static String toJson(Object object) throws Exception {
		return objectMapper.writeValueAsString(object);
	}

	public static <T> T fromJson(String jsonString, Class<T> targetClass) throws Exception {
		return objectMapper.readValue(jsonString, targetClass);
	}

}
