package com.zerobase.leisure.domain.repository.coupon;

import com.zerobase.leisure.domain.entity.coupon.LeisureCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<LeisureCoupon, Long> {

}
