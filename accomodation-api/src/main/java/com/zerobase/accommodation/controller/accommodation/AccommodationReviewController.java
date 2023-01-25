package com.zerobase.accommodation.controller.accommodation;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationReviewDto;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationReview;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationReviewForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.accommodation.AccommodationReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccommodationReviewController {
    private final AccommodationReviewService accommodationReviewService;

    /*
    사용자가 리뷰를 등록
     */
    @PostMapping("/customer/accommodation/review")
    public WebResponseData<String> addAccommodation(@RequestBody AddAccommodationReviewForm form) {
        accommodationReviewService.addAccommodationReview(form);
        return WebResponseData.ok("성공적으로 등록되었습니다.");
    }

    /*
    상품 조회 시 모든 리뷰 조회
     */
    @GetMapping("/customer/accommodation/review")
    public WebResponseData<Page<AccommodationReviewDto>> getAccommodation(@RequestParam Long accommodationId, final Pageable pageable) { //상품의 모든 리뷰 조회
        Page<AccommodationReviewDto> accommodationReviews = accommodationReviewService.getAllAccommodationReview(accommodationId, pageable);
        return WebResponseData.ok(accommodationReviews);
    }

    @PutMapping("/customer/accommodation/review")
    public WebResponseData<String> updateAccommodation(@RequestParam Long accommodationReviewId, @RequestBody AddAccommodationReviewForm form) {
        accommodationReviewService.updateAccommodationReview(accommodationReviewId,form);
        return WebResponseData.ok("성공적으로 수정되었습니다.");
    }

    /*
    샐러가 답글 달기
   */
    @PutMapping("/seller/accommodation/review/reply")
    public WebResponseData<String> updateReplyAccomodation(@RequestParam Long accommodationReviewId, @RequestBody AddAccommodationReviewForm form) {
        accommodationReviewService.updateReplyAccommodationReview(accommodationReviewId,form);
        return WebResponseData.ok("성공적으로 답글이 등록되었습니다.");
    }

    @DeleteMapping("/customer/accommodation/review")
    public WebResponseData<String> deleteAccommodation(@RequestParam Long reviewId) {
        accommodationReviewService.deleteAccommodationReview(reviewId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }
}
