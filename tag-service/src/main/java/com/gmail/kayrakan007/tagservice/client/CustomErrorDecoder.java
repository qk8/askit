package com.gmail.kayrakan007.tagservice.client;

import com.gmail.kayrakan007.commons.exception.PostNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder errorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {

		if (methodKey.startsWith("PostClient")) {
			switch (response.status()) {
			case 404:
				throw new PostNotFoundException();
			}
		}

		return errorDecoder.decode(methodKey, response);
	}
}