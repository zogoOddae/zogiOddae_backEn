package com.zerobase.accomodation.controller;


import static com.zerobase.accomodation.domain.type.ErrorCode.SAMPLE_ERROR_CODE;
import static com.zerobase.accomodation.domain.type.ErrorCode.SAMPLE_SUCCESS_CODE;

import com.zerobase.accomodation.domain.model.WebResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {
	@GetMapping("/success")
	public WebResponseData<Object> sampleSuccessApi() {
		return WebResponseData.ok(SAMPLE_SUCCESS_CODE.getDescription());
	}

	@GetMapping("/error")
	public WebResponseData<Object> sampleErrorApi() {
		return WebResponseData.error(SAMPLE_ERROR_CODE,SAMPLE_ERROR_CODE.getDescription());
	}
}
