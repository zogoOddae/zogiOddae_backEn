package com.zerobase.accomodation.domain.repository.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationReview;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationReviewRepository extends JpaRepository<AccomodationReview, Long> {

    boolean existsByCustomerIdAndAccomodationId(Long customerId, Long accomodationId);

    Optional<AccomodationReview> findByCustomerIdAndAccomodationId(Long customerId, Long accomodationId);

    List<AccomodationReview> findAllByAccomodationId(Long accomodationId);
}
 