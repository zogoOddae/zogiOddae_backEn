package com.zerobase.accommodation.controller.accommodation;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationReviewDto;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationReview;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationReviewForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.accommodation.AccommodationReviewService;
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
@RequestMapping("/accommodation/review")
@RequiredArgsConstructor
public class AccommodationReviewController {
    private final AccommodationReviewService accommodationReviewService;

    /*
    사용자가 리뷰를 등록
     */
    @PostMapping
    public WebResponseData<AccommodationReviewDto> addAccommodation(@RequestBody AddAccommodationReviewForm form) {
        return WebResponseData.ok(
            AccommodationReviewDto.from(accommodationReviewService.addAccommodationReview(form)));
    }

    /*
    상품 조회 시 모든 리뷰 조회
     */
    @GetMapping
    public WebResponseData<List<AccommodationReview>> getAccommodation(@RequestParam Long accommodationId) { //상품의 모든 리뷰 조회
        return WebResponseData.ok(accommodationReviewService.getAllAccommodationReview(accommodationId));
    }

    @PutMapping
    public WebResponseData<AccommodationReviewDto> updateAccommodation(@RequestParam Long reviewId, @RequestBody AddAccommodationReviewForm form) {
        return WebResponseData.ok(
            AccommodationReviewDto.from(
                accommodationReviewService.updateAccommodationReview(reviewId,form)));
    }

    /*
    샐러가 답글 달기
   */
    @PutMapping("/reply")
    public WebResponseData<AccommodationReviewDto> updateReplyAccomodation(@RequestParam Long reviewId, @RequestBody AddAccommodationReviewForm form) {
        return WebResponseData.ok(
            AccommodationReviewDto.from(
                accommodationReviewService.updateReplyAccommodationReview(reviewId,form)));
    }

    @DeleteMapping
    public WebResponseData<String> deleteAccommodation(@RequestParam Long reviewId) {
        accommodationReviewService.deleteAccommodationReview(reviewId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }
}
