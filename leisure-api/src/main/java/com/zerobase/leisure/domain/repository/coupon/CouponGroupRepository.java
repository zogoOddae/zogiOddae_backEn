package com.zerobase.leisure.domain.repository.coupon;

import com.zerobase.leisure.domain.entity.coupon.CouponGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponGroupRepository extends JpaRepository<CouponGroup, Long> {

}
