package com.zerobase.accomodation.domain.repository.coupon;

import com.zerobase.accomodation.domain.entity.coupon.AccomodationCouponGroup;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationCouponGroupRepository extends JpaRepository<AccomodationCouponGroup, Long> {

}
