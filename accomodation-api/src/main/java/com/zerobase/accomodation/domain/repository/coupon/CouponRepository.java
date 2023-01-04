package com.zerobase.accomodation.domain.repository.coupon;

import com.zerobase.accomodation.domain.entity.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
