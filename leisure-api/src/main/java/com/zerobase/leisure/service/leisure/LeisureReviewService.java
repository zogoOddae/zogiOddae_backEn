package com.zerobase.leisure.service.leisure;

import com.zerobase.leisure.domain.dto.leisure.LeisureReviewDto;
import com.zerobase.leisure.domain.entity.leisure.LeisureReview;
import com.zerobase.leisure.domain.form.AddLeisureReviewForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureReviewRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public Page<LeisureReviewDto> getAllLeisureReview(Long leisureId, Pageable pageable) {
		Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("id"));
		Page<LeisureReview> leisureReviews = leisureReviewRepository.findAllByLeisureId(leisureId, limit);

		List<LeisureReviewDto> leisureReviewDtos = new ArrayList<>();

		for (LeisureReview leisureReview : leisureReviews) {
			leisureReviewDtos.add(LeisureReviewDto.from(leisureReview));
		}

		return new PageImpl<>(leisureReviewDtos, limit, leisureReviews.getTotalElements());
	}

	public LeisureReview updateLeisureReview(Long reviewId, AddLeisureReviewForm form) {
		LeisureReview leisureReview = leisureReviewRepository.findById(reviewId)
			.orElseThrow(() -> new LeisureException(ErrorCode.REVIEW_NOT_FOUND));

		leisureReview.setDescription(form.getDescription());
		leisureReview.setRating(form.getRating());

		return leisureReviewRepository.save(leisureReview);
	}

	public LeisureReview updateReplyLeisureReview(Long reviewId, AddLeisureReviewForm form) {
		LeisureReview leisureReview = leisureReviewRepository.findById(reviewId)
			.orElseThrow(() -> new LeisureException(ErrorCode.REVIEW_NOT_FOUND));

		leisureReview.setReply(form.getReply());
		return leisureReviewRepository.save(leisureReview);
	}

	public void deleteLeisureReview(Long reviewId) {
		leisureReviewRepository.delete(leisureReviewRepository.findById(reviewId)
			.orElseThrow(() -> new LeisureException(ErrorCode.REVIEW_NOT_FOUND)));
	}
}
