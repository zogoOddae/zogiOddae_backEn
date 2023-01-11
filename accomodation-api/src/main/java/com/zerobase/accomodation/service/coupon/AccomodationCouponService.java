package com.zerobase.accomodation.service.coupon;

import com.zerobase.accomodation.domain.entity.coupon.AccomodationCoupon;
import com.zerobase.accomodation.domain.entity.coupon.AccomodationCouponGroup;
import com.zerobase.accomodation.domain.form.AddAccomodationCouponForm;
import com.zerobase.accomodation.domain.repository.coupon.AccomodationCouponGroupRepository;
import com.zerobase.accomodation.domain.repository.coupon.AcoomodationCouponRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccomodationCouponService {

    private final AcoomodationCouponRepository accomodationCouponRepository;
    private final AccomodationCouponGroupRepository accomodationCouponGroupRepository;


    public AccomodationCoupon issuedAccomodationCoupon(AddAccomodationCouponForm form) {
        Optional<AccomodationCoupon> optionalAccomodationCoupon =
            accomodationCouponRepository.findByCustomerIdAndCouponGroupId(form.getCustomerId(),form.getCouponGroupId());

        if(optionalAccomodationCoupon.isPresent()){
            throw new RuntimeException("이미 발급된 쿠폰입니다.");
        }

        Optional<AccomodationCouponGroup> optionalAccomodationCouponGroup =
            accomodationCouponGroupRepository.findById(form.getCouponGroupId());

        if(!optionalAccomodationCouponGroup.isPresent()){
            throw new RuntimeException("발급 받을 수 없는 쿠폰입니다.");
        }
        if(!LocalDateTime.now().isBefore(optionalAccomodationCouponGroup.get().getEndTime())){
            throw new RuntimeException("만료된 쿠폰입니다.");
        }

        Long countCoupons = accomodationCouponRepository.countByCouponGroupId(form.getCouponGroupId());

        if(countCoupons >= optionalAccomodationCouponGroup.get().getIssusedcount()){
            throw new RuntimeException("발급 받을 수 없는 쿠폰입니다.");
        }

        return AccomodationCoupon.builder()
            .couponGroupId(form.getCouponGroupId())
            .usedYN(false)
            .customerId(form.getCustomerId())
            .endTime(optionalAccomodationCouponGroup.get().getEndTime())
            .build();
    }
}
