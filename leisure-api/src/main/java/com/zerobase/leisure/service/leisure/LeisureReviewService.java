package com.zerobase.leisure.service.leisure;

import com.zerobase.leisure.domain.dto.leisure.LeisureReviewDto;
import com.zerobase.leisure.domain.entity.leisure.LeisureReview;
import com.zerobase.leisure.domain.form.AddLeisureReviewForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureReviewRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureReviewService {
	private final LeisureReviewRepository leisureReviewRepository;

	public LeisureReview addLeisureReview(AddLeisureReviewForm form) {
		LeisureReview leisureReview = LeisureReview.of(form);
		leisureReviewRepository.save(leisureReview);
		return leisureReview;
	}

	public List<LeisureReviewDto> getAllLeisureReview(Long leisureId) {
		return LeisureReviewDto.fromList(leisureReviewRepository.findAllByLeisureId(leisureId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_HAD_REVIEW)));
	}

	public LeisureReview updateLeisureReview(Long reviewId, AddLeisureReviewForm form) {
		LeisureReview leisureReview = leisureReviewRepository.findById(reviewId)
			.orElseThrow(() -> new LeisureException(ErrorCode.REVIEW_NOT_FOUND));

		leisureReview.setDescription(form.getDescription());
		leisureReview.setRating(form.getRating());

		return leisureReviewRepository.save(leisureReview);
	}

	public LeisureReview updateReplyLeisureReview(Long reviewId, String reply) {
		LeisureReview leisureReview = leisureReviewRepository.findById(reviewId)
			.orElseThrow(() -> new LeisureException(ErrorCode.REVIEW_NOT_FOUND));

		leisureReview.setReply(reply);
		return leisureReviewRepository.save(leisureReview);
	}

	public void deleteLeisureReview(Long reviewId) {
		leisureReviewRepository.delete(leisureReviewRepository.findById(reviewId)
			.orElseThrow(() -> new LeisureException(ErrorCode.REVIEW_NOT_FOUND)));
	}
}
