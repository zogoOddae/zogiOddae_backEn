package com.zerobase.accomodation.controller.accomodation;


import com.zerobase.accomodation.domain.dto.AccomodationDto;
import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import com.zerobase.accomodation.domain.form.AddAccomodationForm;
import com.zerobase.accomodation.domain.model.WebResponseData;
import com.zerobase.accomodation.service.accomodation.SellerAccomodationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accomodation")
@RequiredArgsConstructor
public class SellerAccomodationController {

	private final SellerAccomodationService sellerAccomodationService;

	@PostMapping("/register")
	public WebResponseData<AccomodationDto> addAccomodation(@RequestParam Long sellerId, @RequestBody AddAccomodationForm form) {
		return WebResponseData.ok(
			AccomodationDto.from(sellerAccomodationService.addAccomodation(sellerId, form)));
	}

	@GetMapping
	public WebResponseData<List<Accomodation>> getAccomodation(@RequestParam Long sellerId) { //셀러 상품 전체 조회
		return WebResponseData.ok(sellerAccomodationService.getAllAccomodation(sellerId));
	}

	@GetMapping("/detail")
	public WebResponseData<AccomodationDto> getDetailAccomodation(@RequestParam Long accomodationId, @RequestParam Long sellerId) { //셀러 상품 상세 조회
		return WebResponseData.ok(
			AccomodationDto.from(sellerAccomodationService.getDetailAccomodation(accomodationId, sellerId)));
	}

	@PutMapping
	public WebResponseData<AccomodationDto> updateAccomodation(@RequestParam Long accomodationId, @RequestParam Long sellerId, @RequestBody AddAccomodationForm form) { //form에서 기존 정보와 수정 정보를 같이 주어야함
		return WebResponseData.ok(
			AccomodationDto.from(sellerAccomodationService.updateAccomodation(sellerId, form)));
	}

	@DeleteMapping
	public WebResponseData<String> deleteAccomodation(@RequestParam Long accomodationId, Long sellerId) {
		sellerAccomodationService.deleteAccomodation(accomodationId, sellerId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}



}
