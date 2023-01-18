package com.zerobase.accommodation.controller.accommodation;


import com.zerobase.accommodation.domain.dto.accommodation.AccommodationDayOffDto;
import com.zerobase.accommodation.domain.dto.accommodation.AccommodationDto;
import com.zerobase.accommodation.domain.dto.accommodation.AccommodationListDto;
import com.zerobase.accommodation.domain.form.accommodation.AccommodationDayOffForm;
import com.zerobase.accommodation.domain.form.accommodation.AccommodationForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.accommodation.SellerAccommodationService;
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
@RequestMapping("/accommodation")
@RequiredArgsConstructor
public class SellerAccommodationController {

	private final SellerAccommodationService sellerAccommodationService;

	@PostMapping
	public WebResponseData<AccommodationDto> addAccommodation(@RequestParam Long sellerId, @RequestBody AccommodationForm form) {
		return WebResponseData.ok(
			AccommodationDto.from(sellerAccommodationService.addAccommodation(sellerId, form)));
	}

	@GetMapping
	public WebResponseData<List<AccommodationListDto>> getAccommodation(@RequestParam Long sellerId) { //셀러 상품 전체 조회
		return WebResponseData.ok(sellerAccommodationService.getAllAccommodation(sellerId));
	}

	@GetMapping("/detail")
	public WebResponseData<AccommodationDto> getDetailAccommodation(@RequestParam Long accommodationId, @RequestParam Long sellerId) { //셀러 상품 상세 조회
		return WebResponseData.ok(
			AccommodationDto.from(
				sellerAccommodationService.getDetailAccommodation(accommodationId, sellerId)));
	}

	@PutMapping
	public WebResponseData<AccommodationDto> updateAccommodation(@RequestParam Long accommodationId, @RequestBody AccommodationForm form) { //form에서 기존 정보와 수정 정보를 같이 주어야함
		return WebResponseData.ok(
			AccommodationDto.from(sellerAccommodationService.updateAccommodation(accommodationId, form)));
	}

	@DeleteMapping
	public WebResponseData<String> deleteAccommodation(@RequestParam Long accommodationId, Long sellerId) {
		sellerAccommodationService.deleteAccommodation(accommodationId, sellerId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}

	//휴일 관련
	@PostMapping("/dayOff")
	public WebResponseData<String> addAccommodationDayOff(@RequestParam Long accommodationId, @RequestBody AccommodationDayOffForm form) {
		sellerAccommodationService.addAccommodationDayOff(accommodationId, form);
		return WebResponseData.ok("성공적으로 등록 되었습니다.");
	}

	@DeleteMapping("/dayOff")
	public WebResponseData<String> deleteAccommodationDayOff(@RequestParam Long accommodationDayOffId) {
		sellerAccommodationService.deleteAccommodationDayOff(accommodationDayOffId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}

	@GetMapping("/dayOff")
	public WebResponseData<List<AccommodationDayOffDto>> getAccommodationDayOff(@RequestParam Long accommodationId) {
		return WebResponseData.ok(AccommodationDayOffDto.fromList(sellerAccommodationService.getAccommodationDayOff(accommodationId)));
	}

	@PutMapping("/dayOff")
	public WebResponseData<String> updateAccommodationDayOff(@RequestParam Long accommodationDayOffId,
		@RequestBody AccommodationDayOffForm form) {
		sellerAccommodationService.updateAccommodationDayOff(accommodationDayOffId, form);
		return WebResponseData.ok("성공적으로 수정되었습니다.");
	}

}
