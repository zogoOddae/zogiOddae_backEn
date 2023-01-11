package com.zerobase.leisure.controller.leisure;


import com.zerobase.leisure.domain.dto.LeisureDto;
import com.zerobase.leisure.domain.form.AddLeisureForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.SellerLeisureService;
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

@Controller
@RequestMapping("/seller/leisure")
@RequiredArgsConstructor
public class SellerLeisureController {

	private final SellerLeisureService sellerLeisureService;
	@PostMapping
	public WebResponseData<LeisureDto> addLeisure(@RequestParam Long sellerId,
											@RequestBody AddLeisureForm form) {
		return WebResponseData.ok(LeisureDto.from(sellerLeisureService.AddLeisure(sellerId, form)));
	}

	@GetMapping
	public WebResponseData<List<LeisureDto>> getLeisure(@RequestParam Long sellerId) { //셀러 상품 전체 조회
		return WebResponseData.ok(LeisureDto.fromList(sellerLeisureService.getAllLeisure(sellerId)));
	}

	@GetMapping("/detail")
	public WebResponseData<LeisureDto> getDetailLeisure(@RequestParam Long LeisureId, @RequestParam Long sellerId) {
		return WebResponseData.ok(
			LeisureDto.from(sellerLeisureService.getDetailLeisure(LeisureId, sellerId)));
	}

	@PutMapping
	public WebResponseData<LeisureDto> updateLeisure(@RequestParam Long LeisureId, @RequestBody AddLeisureForm form) {
		return WebResponseData.ok(
			LeisureDto.from(sellerLeisureService.updateLeisure(LeisureId, form)));
	}

	@DeleteMapping
	public WebResponseData<String> deleteLeisure(@RequestParam Long LeisureId, Long sellerId) {
		sellerLeisureService.deleteLeisure(LeisureId, sellerId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}

}
