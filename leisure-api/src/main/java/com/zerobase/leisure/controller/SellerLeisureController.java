package com.zerobase.leisure.controller;


import com.zerobase.leisure.domain.dto.LeisureDto;
import com.zerobase.leisure.domain.form.AddLeisureForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.SellerLeisureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/seller/leisure")
@RequiredArgsConstructor
public class SellerLeisureController {

	private final SellerLeisureService sellerLeisureService;
	@PostMapping("/register")
	public WebResponseData<LeisureDto> addLeisure(@RequestParam Long sellerId,
											@RequestBody AddLeisureForm form) {

		// token값으로 sellerId 받아올것
		// 지금은 임시 코딩
		// 요런 식으로??? Long slellerId = UserVo.getUser(token).getId();

		return WebResponseData.ok(LeisureDto.from(sellerLeisureService.AddLeisure(sellerId, form)));
	}
}
