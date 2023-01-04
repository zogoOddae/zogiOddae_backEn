package com.zerobase.accomodation.domain.repository.coupon;

import com.zerobase.accomodation.domain.entity.coupon.CouponGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponGroupRepository extends JpaRepository<CouponGroup, Long> {

}
