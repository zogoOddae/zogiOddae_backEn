package com.zerobase.accomodation.service.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationReview;
import com.zerobase.accomodation.domain.form.AddAccomodationReviewForm;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationReviewRepository;
import com.zerobase.accomodation.domain.type.ErrorCode;
import com.zerobase.accomodation.exception.AccomodationException;
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
            throw new AccomodationException(ErrorCode.ALREADY_REGISTERED_REVIEW);
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
            throw new AccomodationException(ErrorCode.NOT_MY_REVIEW);
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
        return accomodationReviewRepository.findById(reviewId).orElseThrow(() -> new AccomodationException(ErrorCode.NOT_HAD_REVIEW));
    }

    public List<AccomodationReview> getAllAccomodationReview(Long accomodationId) {
        return accomodationReviewRepository.findAllByAccomodationId(accomodationId);
    }
}
