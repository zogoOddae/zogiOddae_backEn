package com.zerobase.accommodation.service.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationReview;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationReviewForm;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationReviewRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationReviewService {
    private final AccommodationReviewRepository accommodationReviewRepository;

    public AccommodationReview addAccommodationReview(AddAccommodationReviewForm form) {
        if(accommodationReviewRepository
            .existsByCustomerIdAndAccommodationId(form.getCustomerId(),form.getAccommodationId())){
            throw new AccommodationException(ErrorCode.ALREADY_REGISTERED_REVIEW);
        }

        return AccommodationReview.builder()
            .customerId(form.getCustomerId())
            .accommodationId(form.getAccommodationId())
            .rating(form.getRating())
            .description(form.getDescription())
            .build();
    }

    public AccommodationReview updateAccommodationReview(Long reviewId,
        AddAccommodationReviewForm form) {
        //삭제되지 않은 댓글인지도 확인
        AccommodationReview accommodationReview = getRiewInfo(reviewId);

        if(accommodationReview.getCustomerId() != form.getCustomerId()){
            throw new AccommodationException(ErrorCode.NOT_MY_REVIEW);
        }

        accommodationReview.setAccommodationId(form.getAccommodationId());
        accommodationReview.setCustomerId(form.getCustomerId());
        accommodationReview.setDescription(form.getDescription());
        accommodationReview.setRating(form.getRating());
        return accommodationReviewRepository.save(accommodationReview);
    }

    public void deleteAccommodationReview(Long reviewId) {
        getRiewInfo(reviewId);

        accommodationReviewRepository.deleteById(reviewId);
    }

    public AccommodationReview updateReplyAccommodationReview(Long reviewId, AddAccommodationReviewForm form) {
        AccommodationReview accommodationReview = getRiewInfo(reviewId);

        accommodationReview.setAccommodationId(form.getAccommodationId());
        accommodationReview.setCustomerId(form.getCustomerId());
        accommodationReview.setDescription(form.getDescription());
        accommodationReview.setRating(form.getRating());
        accommodationReview.setReply(form.getReply());
        accommodationReview.setSellerId(form.getSellerId());

        return accommodationReviewRepository.save(accommodationReview);
    }

    private AccommodationReview getRiewInfo(Long reviewId){
        return accommodationReviewRepository.findById(reviewId).orElseThrow(() -> new AccommodationException(ErrorCode.NOT_HAD_REVIEW));
    }

    public List<AccommodationReview> getAllAccommodationReview(Long accomodationId) {
        return accommodationReviewRepository.findAllByAccommodationId(accomodationId);
    }
}
