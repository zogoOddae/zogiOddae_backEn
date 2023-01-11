package com.zerobase.leisure.domain.repository.leisure;

import com.zerobase.leisure.domain.entity.leisure.LeisureReview;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureReviewRepository extends JpaRepository<LeisureReview, Long> {
	Optional<List<LeisureReview>> findAllByLeisureId(Long leisureId);
}
