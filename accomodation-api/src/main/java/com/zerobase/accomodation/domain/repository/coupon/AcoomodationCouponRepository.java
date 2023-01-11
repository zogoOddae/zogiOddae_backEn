package com.zerobase.accomodation.domain.repository.coupon;

import com.zerobase.accomodation.domain.entity.coupon.AccomodationCoupon;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcoomodationCouponRepository extends JpaRepository<AccomodationCoupon, Long> {

    Optional<AccomodationCoupon> findByCustomerIdAndCouponGroupId(Long customerId, Long couponGroupId);

    Long countByCouponGroupId(Long couponGroupId);
}
