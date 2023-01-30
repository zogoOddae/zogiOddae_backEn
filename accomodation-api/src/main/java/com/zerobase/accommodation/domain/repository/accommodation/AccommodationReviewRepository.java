package com.zerobase.accommodation.domain.repository.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationReview;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationReviewRepository extends JpaRepository<AccommodationReview, Long> {

    boolean existsByCustomerIdAndAccommodationId(Long customerId, Long accommodationId);


    Page<AccommodationReview> findAllByAccommodationId(Long accommodationId, Pageable limit);
}
 