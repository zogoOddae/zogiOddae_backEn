package com.zerobase.leisure.controller.leisure;


import com.zerobase.leisure.domain.dto.leisure.LeisureDto;
import com.zerobase.leisure.domain.form.AddLeisureForm;
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
											@RequestBody AddLeisureForm form) {
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
	public @ResponseBody WebResponseData<LeisureDto> updateLeisure(@RequestParam Long leisureId, @RequestBody AddLeisureForm form) {
		return WebResponseData.ok(
			LeisureDto.from(sellerLeisureService.updateLeisure(leisureId, form)));
	}

	@DeleteMapping
	public @ResponseBody WebResponseData<String> deleteLeisure(@RequestParam Long leisureId, Long sellerId) {
		sellerLeisureService.deleteLeisure(leisureId, sellerId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}

}
