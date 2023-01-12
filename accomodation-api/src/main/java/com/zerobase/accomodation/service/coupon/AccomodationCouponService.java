package com.zerobase.accomodation.service.coupon;

import com.zerobase.accomodation.domain.dto.coupon.AccomodationCouponDto;
import com.zerobase.accomodation.domain.entity.coupon.AccomodationCoupon;
import com.zerobase.accomodation.domain.entity.coupon.AccomodationCouponGroup;
import com.zerobase.accomodation.domain.form.AddAccomodationCouponForm;
import com.zerobase.accomodation.domain.repository.coupon.AccomodationCouponGroupRepository;
import com.zerobase.accomodation.domain.repository.coupon.AcoomodationCouponRepository;
import com.zerobase.accomodation.domain.type.ErrorCode;
import com.zerobase.accomodation.exception.AccomodationException;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccomodationCouponService {

    private final AcoomodationCouponRepository accomodationCouponRepository;
    private final AccomodationCouponGroupRepository accomodationCouponGroupRepository;


    public AccomodationCoupon issuedAccomodationCoupon(AddAccomodationCouponForm form) {
        Optional<AccomodationCoupon> optionalAccomodationCoupon =
            accomodationCouponRepository.findByCustomerIdAndCouponGroupId(form.getCustomerId(),form.getCouponGroupId());

        if(optionalAccomodationCoupon.isPresent()){
            throw new AccomodationException(ErrorCode.ALREADY_ISSUSED_COUPON);
        }

        Optional<AccomodationCouponGroup> optionalAccomodationCouponGroup =
            accomodationCouponGroupRepository.findById(form.getCouponGroupId());

        if(!optionalAccomodationCouponGroup.isPresent()){
            throw new AccomodationException(ErrorCode.NOT_REGISTERED_COUPONGROUP);
        }

        if(LocalDateTime.now().isAfter(optionalAccomodationCouponGroup.get().getEndTime())){
            throw new AccomodationException(ErrorCode.EXPIRED_COUPON);
        }

        Long countCoupons = accomodationCouponRepository.countByCouponGroupId(form.getCouponGroupId());

        if(countCoupons >= optionalAccomodationCouponGroup.get().getIssusedcount()){
            throw new AccomodationException(ErrorCode.EXCEEDED_COUNT_COUPON);
        }

        return accomodationCouponRepository.save(AccomodationCoupon.builder()
            .couponGroupId(form.getCouponGroupId())
            .usedYN(false)
            .customerId(form.getCustomerId())
            .endTime(optionalAccomodationCouponGroup.get().getEndTime())
            .build());
    }
}
