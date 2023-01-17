package com.zerobase.accommodation.service.coupon;

import com.zerobase.accommodation.domain.entity.coupon.AccommodationCoupon;
import com.zerobase.accommodation.domain.entity.coupon.AccommodationCouponGroup;
import com.zerobase.accommodation.domain.form.AddAccommodationCouponForm;
import com.zerobase.accommodation.domain.repository.coupon.AccommodationCouponGroupRepository;
import com.zerobase.accommodation.domain.repository.coupon.AcoommodationCouponRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationCouponService {

    private final AcoommodationCouponRepository accommodationCouponRepository;
    private final AccommodationCouponGroupRepository accommodationCouponGroupRepository;


    public AccommodationCoupon issuedAccommodationCoupon(AddAccommodationCouponForm form) {
        Optional<AccommodationCoupon> optionalAccommodationCoupon =
            accommodationCouponRepository.findByCustomerIdAndCouponGroupId(form.getCustomerId(),form.getCouponGroupId());

        if(optionalAccommodationCoupon.isPresent()){
            throw new AccommodationException(ErrorCode.ALREADY_ISSUSED_COUPON);
        }

        Optional<AccommodationCouponGroup> optionalAccommodationCouponGroup =
            accommodationCouponGroupRepository.findById(form.getCouponGroupId());

        if(!optionalAccommodationCouponGroup.isPresent()){
            throw new AccommodationException(ErrorCode.NOT_REGISTERED_COUPON_GROUP);
        }

        if(LocalDateTime.now().isAfter(optionalAccommodationCouponGroup.get().getEndTime())){
            throw new AccommodationException(ErrorCode.EXPIRED_COUPON);
        }

        Long countCoupons = accommodationCouponRepository.countByCouponGroupId(form.getCouponGroupId());

        if(countCoupons >= optionalAccommodationCouponGroup.get().getIssusedcount()){
            throw new AccommodationException(ErrorCode.EXCEEDED_COUNT_COUPON);
        }

        return accommodationCouponRepository.save(AccommodationCoupon.builder()
            .couponGroupId(form.getCouponGroupId())
            .usedYN(false)
            .customerId(form.getCustomerId())
            .endTime(optionalAccommodationCouponGroup.get().getEndTime())
            .build());
    }
}
