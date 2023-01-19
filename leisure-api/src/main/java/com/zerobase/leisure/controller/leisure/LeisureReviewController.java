package com.zerobase.leisure.controller.leisure;


import com.zerobase.leisure.domain.dto.leisure.LeisureReviewDto;
import com.zerobase.leisure.domain.form.AddLeisureReviewForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.leisure.LeisureReviewService;
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
@RequestMapping("/leisure/review")
@RequiredArgsConstructor
public class LeisureReviewController {

	private final LeisureReviewService leisureReviewService;

	@PostMapping
	public @ResponseBody WebResponseData<String> addLeisureReview(@RequestBody AddLeisureReviewForm form) {
		leisureReviewService.addLeisureReview(form);
		return WebResponseData.ok("리뷰를 성공적으로 등록했습니다.");
	}

	/*
	상품 조회 시 모든 리뷰 조회
	 */
	@GetMapping
	public @ResponseBody WebResponseData<List<LeisureReviewDto>> getLeisure(@RequestParam Long leisureId) {
		return WebResponseData.ok(leisureReviewService.getAllLeisureReview(leisureId));
	}

	@PutMapping
	public @ResponseBody WebResponseData<String> updateLeisureReview(@RequestParam Long leisureReviewId, @RequestBody AddLeisureReviewForm form) {
		leisureReviewService.updateLeisureReview(leisureReviewId,form);
		return WebResponseData.ok("리뷰를 수정했습니다.");
	}

	/*
	샐러가 답글 달기
   */
	@PutMapping("/reply")
	public @ResponseBody WebResponseData<String> updateReplyLeisure(@RequestParam Long leisureReviewId, @RequestBody String reply) {
		leisureReviewService.updateReplyLeisureReview(leisureReviewId,reply);
		return WebResponseData.ok("리뷰에 답글을 남겼습니다.");
	}

	@DeleteMapping
	public @ResponseBody WebResponseData<String> deleteLeisureReview(@RequestParam Long leisureReviewId) {
		leisureReviewService.deleteLeisureReview(leisureReviewId);
		return WebResponseData.ok("성공적으로 삭제 되었습니다.");
	}

}
