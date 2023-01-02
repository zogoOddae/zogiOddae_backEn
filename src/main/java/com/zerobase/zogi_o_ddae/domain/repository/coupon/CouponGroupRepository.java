package com.zerobase.zogi_o_ddae.domain.repository.coupon;

import com.zerobase.zogi_o_ddae.domain.entity.coupon.CouponGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponGroupRepository extends JpaRepository<CouponGroup, Long> {

}
