package com.zerobase.accomodation.domain.repository.leisure;

import com.zerobase.accomodation.domain.entity.leisure.AccomodationReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationReviewRepository extends JpaRepository<AccomodationReview, Long> {

}
