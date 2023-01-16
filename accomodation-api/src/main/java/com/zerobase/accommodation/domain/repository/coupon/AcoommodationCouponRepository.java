package com.zerobase.accommodation.domain.repository.coupon;

import com.zerobase.accommodation.domain.entity.coupon.AccommodationCoupon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcoommodationCouponRepository extends JpaRepository<AccommodationCoupon, Long> {

    Optional<AccommodationCoupon> findByCustomerIdAndCouponGroupId(Long customerId, Long couponGroupId);

    Long countByCouponGroupId(Long couponGroupId);
}
