package com.zerobase.accomodation.controller;


import com.zerobase.accomodation.domain.dto.AccomodationDto;
import com.zerobase.accomodation.domain.form.AddAccomodationForm;
import com.zerobase.accomodation.service.SellerAccomodationService;
import com.zerobase.accomodation.domain.model.WebResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/accomodation/seller")
@RequiredArgsConstructor
public class SellerAccomodationController {

	private final SellerAccomodationService sellerAccomodationService;

	@PostMapping("/register")
	public WebResponseData<AccomodationDto> addLeisure(@RequestParam Long sellerId, @RequestBody AddAccomodationForm form) {
		return WebResponseData.ok(
			AccomodationDto.from(sellerAccomodationService.AddLeisure(sellerId, form)));
	}
}
