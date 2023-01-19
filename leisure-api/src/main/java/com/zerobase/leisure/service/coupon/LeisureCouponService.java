package com.zerobase.leisure.service.coupon;

import com.zerobase.leisure.domain.entity.coupon.LeisureCoupon;
import com.zerobase.leisure.domain.entity.coupon.LeisureCouponGroup;
import com.zerobase.leisure.domain.form.AddLeisureCouponForm;
import com.zerobase.leisure.domain.repository.coupon.LeisureCouponGroupRepository;
import com.zerobase.leisure.domain.repository.coupon.LeisureCouponRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeisureCouponService {

    private final LeisureCouponRepository leisureCouponRepository;
    private final LeisureCouponGroupRepository leisureCouponGroupRepository;


    public LeisureCoupon issuedLeisureCoupon(Long customerId, Long couponGroupId) {
        Optional<LeisureCoupon> optionalLeisureCoupon =
            leisureCouponRepository.findByCustomerIdAndCouponGroupId(customerId,couponGroupId);

        if(optionalLeisureCoupon.isPresent()){
            throw new LeisureException(ErrorCode.ALREADY_ISSUED_COUPON);
        }

        Optional<LeisureCouponGroup> optionalLeisureCouponGroup =
            leisureCouponGroupRepository.findById(couponGroupId);

        if(!optionalLeisureCouponGroup.isPresent()){
            throw new LeisureException(ErrorCode.NOT_REGISTERED_COUPON_GROUP);
        }

        if(LocalDateTime.now().isAfter(optionalLeisureCouponGroup.get().getEndTime())){
            throw new LeisureException(ErrorCode.EXPIRED_COUPON);
        }

        Long countCoupons = leisureCouponRepository.countByCouponGroupId(couponGroupId);

        if(countCoupons >= optionalLeisureCouponGroup.get().getIssuedCount()){
            throw new LeisureException(ErrorCode.EXCEEDED_COUNT_COUPON);
        }

        return leisureCouponRepository.save(LeisureCoupon.builder()
            .couponGroupId(couponGroupId)
            .usedYN(false)
            .customerId(customerId)
            .endTime(optionalLeisureCouponGroup.get().getEndTime())
            .build());
    }
}
