package com.zerobase.leisure.domain.repository.coupon;

import com.zerobase.leisure.domain.entity.coupon.LeisureCouponGroup;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureCouponGroupRepository extends JpaRepository<LeisureCouponGroup, Long> {

	Page<LeisureCouponGroup> findAllByEndTimeIsAfter(LocalDate now, Pageable limit);
}
