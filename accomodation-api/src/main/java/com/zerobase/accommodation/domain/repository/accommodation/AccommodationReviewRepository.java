package com.zerobase.accommodation.domain.repository.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationReviewRepository extends JpaRepository<AccommodationReview, Long> {

    boolean existsByCustomerIdAndAccommodationId(Long customerId, Long accommodationId);

    List<AccommodationReview> findAllByAccommodationId(Long accommodationId);
}
 