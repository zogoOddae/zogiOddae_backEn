package com.zerobase.accommodation.domain.repository.coupon;

import com.zerobase.accommodation.domain.entity.coupon.AccommodationCouponGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationCouponGroupRepository extends JpaRepository<AccommodationCouponGroup, Long> {

}
