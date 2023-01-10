package com.zerobase.accomodation.controller.accomodation;

import com.zerobase.accomodation.domain.dto.AccomodationReviewDto;
import com.zerobase.accomodation.domain.entity.accomodation.AccomodationReview;
import com.zerobase.accomodation.domain.form.AddAccomodationReviewForm;
import com.zerobase.accomodation.domain.model.WebResponseData;
import com.zerobase.accomodation.service.accomodation.AccomodationReviewService;
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
@RequestMapping("/accomodation/review")
@RequiredArgsConstructor
public class AccomodationReviewController {
    private final AccomodationReviewService accomodationReviewService;

    /*
    사용자가 리뷰를 등록
     */
    @PostMapping("/register")
    public WebResponseData<AccomodationReviewDto> addAccomodation(@RequestBody AddAccomodationReviewForm form) {
        return WebResponseData.ok(
            AccomodationReviewDto.from(accomodationReviewService.addAccomodationReview(form)));
    }

    /*
    상품 조회 시 모든 리뷰 조회
     */
    @GetMapping
    public WebResponseData<List<AccomodationReview>> getAccomodation(@RequestParam Long accomodationId) { //상품의 모든 리뷰 조회
        return WebResponseData.ok(accomodationReviewService.getAllAccomodationReview(accomodationId));
    }

    @PutMapping
    public WebResponseData<AccomodationReviewDto> updateAccomodation(@RequestParam Long reviewId, @RequestBody AddAccomodationReviewForm form) {
        return WebResponseData.ok(
            AccomodationReviewDto.from(accomodationReviewService.updateAccomodationReview(reviewId,form)));
    }

    /*
    샐러가 답글 달기
   */
    @PutMapping("/reply")
    public WebResponseData<AccomodationReviewDto> updateReplyAccomodation(@RequestParam Long reviewId, @RequestBody AddAccomodationReviewForm form) {
        return WebResponseData.ok(
            AccomodationReviewDto.from(accomodationReviewService.updateReplyAccomodationReview(reviewId,form)));
    }

    @DeleteMapping
    public WebResponseData<String> deleteAccomodation(@RequestParam Long reviewId) {
        accomodationReviewService.deleteAccomodationReview(reviewId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }
}
