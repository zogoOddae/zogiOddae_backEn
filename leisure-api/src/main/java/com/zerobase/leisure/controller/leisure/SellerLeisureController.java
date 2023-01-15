package com.zerobase.leisure.controller.leisure;


import com.zerobase.leisure.domain.dto.leisure.LeisureDayOffDto;
import com.zerobase.leisure.domain.dto.leisure.LeisureDto;
import com.zerobase.leisure.domain.entity.leisure.LeisureDayOff;
import com.zerobase.leisure.domain.form.LeisureDayOffForm;
import com.zerobase.leisure.domain.form.LeisureForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.leisure.SellerLeisureService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seller/leisure")
@RequiredArgsConstructor
public class SellerLeisureController {

	private final SellerLeisureService sellerLeisureService;
	@PostMapping
	public @ResponseBody WebResponseData<LeisureDto> addLeisure(@RequestParam Long sellerId,
											@RequestBody LeisureForm form) {
		return WebResponseData.ok(LeisureDto.from(sellerLeisureService.AddLeisure(sellerId, form)));
	}

	@GetMapping
	public @ResponseBody WebResponseData<List<LeisureDto>> getLeisure(@RequestParam Long sellerId) { //셀러 상품 전체 조회
		return WebResponseData.ok(LeisureDto.fromList(sellerLeisureService.getAllLeisure(sellerId)));
	}

	@GetMapping("/detail")
	public @ResponseBody WebResponseData<LeisureDto> getDetailLeisure(@RequestParam Long leisureId, @RequestParam Long sellerId) {
		return WebResponseData.ok(
			LeisureDto.from(sellerLeisureService.getDetailLeisure(leisureId, sellerId)));
	}

	@PutMapping
	public @ResponseBody WebResponseData<LeisureDto> updateLeisure(@RequestParam Long leisureId, @RequestBody LeisureForm form) {
		return WebResponseData.ok(
			LeisureDto.from(sellerLeisureService.updateLeisure(leisureId, form)));
	}

	@DeleteMapping
	public @ResponseBody WebResponseData<String> deleteLeisure(@RequestParam Long leisureId, Long sellerId) {
		sellerLeisureService.deleteLeisure(leisureId, sellerId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}


	//휴일 관련
	@PostMapping("/dayOff")
	public @ResponseBody WebResponseData<String> addLeisureDayOff(@RequestParam Long leisureId, @RequestBody LeisureDayOffForm form) {
		sellerLeisureService.addLeisureDayOff(leisureId, form);
		return WebResponseData.ok("성공적으로 등록 되었습니다.");
	}

	@DeleteMapping("/dayOff")
	public @ResponseBody WebResponseData<String> deleteLeisureDayOff(@RequestParam Long leisureDayOffId) {
		sellerLeisureService.deleteLeisureDayOff(leisureDayOffId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}

	@GetMapping("/dayOff")
	public @ResponseBody WebResponseData<List<LeisureDayOffDto>> getLeisureDayOff(@RequestParam Long leisureId) {
		return WebResponseData.ok(LeisureDayOffDto.fromList(sellerLeisureService.getLeisureDayOff(leisureId)));
	}

	@PutMapping("/dayOff")
	public @ResponseBody WebResponseData<String> updateLeisureDayOff(@RequestParam Long leisureDayOffId,
		@RequestBody LeisureDayOffForm form) {
		sellerLeisureService.updateLeisureDayOff(leisureDayOffId, form);
		return WebResponseData.ok("성공적으로 수정되었습니다.");
	}
}
