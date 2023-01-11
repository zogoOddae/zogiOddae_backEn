package com.zerobase.accomodation.service.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationReview;
import com.zerobase.accomodation.domain.form.AddAccomodationReviewForm;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccomodationReviewService {
    private final AccomodationReviewRepository accomodationReviewRepository;

    public AccomodationReview addAccomodationReview(AddAccomodationReviewForm form) {
        if(accomodationReviewRepository
            .existsByCustomerIdAndAccomodationId(form.getCustomerId(),form.getAccomodationId())){
            throw new RuntimeException("이미 등록된 리뷰가 있습니다.");
        }

        return AccomodationReview.builder()
            .customerId(form.getCustomerId())
            .accomodationId(form.getAccomodationId())
            .rating(form.getRating())
            .description(form.getDescription())
            .build();
    }

    public AccomodationReview updateAccomodationReview(Long reviewId,AddAccomodationReviewForm form) {
        //삭제되지 않은 댓글인지도 확인
        AccomodationReview accomodationReview = getRiewInfo(reviewId);

        if(accomodationReview.getCustomerId() != form.getCustomerId()){
            throw new RuntimeException("내가 작성한 댓글이 아닙니다.");
        }

        accomodationReview.setAccomodationId(form.getAccomodationId());
        accomodationReview.setCustomerId(form.getCustomerId());
        accomodationReview.setDescription(form.getDescription());
        accomodationReview.setRating(form.getRating());
        return accomodationReviewRepository.save(accomodationReview);
    }

    public void deleteAccomodationReview(Long reviewId) {
        getRiewInfo(reviewId);

        accomodationReviewRepository.deleteById(reviewId);
    }

    public AccomodationReview updateReplyAccomodationReview(Long reviewId, AddAccomodationReviewForm form) {
        AccomodationReview accomodationReview = getRiewInfo(reviewId);

        accomodationReview.setAccomodationId(form.getAccomodationId());
        accomodationReview.setCustomerId(form.getCustomerId());
        accomodationReview.setDescription(form.getDescription());
        accomodationReview.setRating(form.getRating());
        accomodationReview.setReply(form.getReply());
        accomodationReview.setSellerId(form.getSellerId());

        return accomodationReviewRepository.save(accomodationReview);
    }

    private AccomodationReview getRiewInfo(Long reviewId){
        return accomodationReviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("작성한 댓글이 없습니다."));
    }

    public List<AccomodationReview> getAllAccomodationReview(Long accomodationId) {
        return accomodationReviewRepository.findAllByAccomodationId(accomodationId);
    }
}
