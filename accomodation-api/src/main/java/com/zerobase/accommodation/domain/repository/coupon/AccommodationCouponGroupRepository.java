package com.zerobase.accommodation.domain.repository.coupon;

import com.zerobase.accommodation.domain.entity.coupon.AccommodationCouponGroup;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationCouponGroupRepository extends JpaRepository<AccommodationCouponGroup, Long> {

    Page<AccommodationCouponGroup> findAllByEndTimeIsAfter(LocalDateTime now, Pageable limit);
}
