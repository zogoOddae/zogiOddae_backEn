package com.zerobase.accommodation.service.coupon;

import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponDto;
import com.zerobase.accommodation.domain.entity.coupon.AccommodationCoupon;
import com.zerobase.accommodation.domain.entity.coupon.AccommodationCouponGroup;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationCouponForm;
import com.zerobase.accommodation.domain.repository.coupon.AccommodationCouponGroupRepository;
import com.zerobase.accommodation.domain.repository.coupon.AccommodationCouponRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationCouponService {

    private final AccommodationCouponRepository accommodationCouponRepository;
    private final AccommodationCouponGroupRepository accommodationCouponGroupRepository;


    public AccommodationCoupon issuedAccommodationCoupon(Long customerId, Long couponGroupId) {
        Optional<AccommodationCoupon> optionalAccommodationCoupon =
            accommodationCouponRepository.findByCustomerIdAndCouponGroupId(customerId,couponGroupId);

        if(optionalAccommodationCoupon.isPresent()){
            throw new AccommodationException(ErrorCode.ALREADY_ISSUSED_COUPON);
        }

        Optional<AccommodationCouponGroup> optionalAccommodationCouponGroup =
            accommodationCouponGroupRepository.findById(couponGroupId);

        if(!optionalAccommodationCouponGroup.isPresent()){
            throw new AccommodationException(ErrorCode.NOT_REGISTERED_COUPON_GROUP);
        }

        if(LocalDate.now().isAfter(optionalAccommodationCouponGroup.get().getEndTime())){
            throw new AccommodationException(ErrorCode.EXPIRED_COUPON);
        }

        Long countCoupons = accommodationCouponRepository.countByCouponGroupId(couponGroupId);

        if(countCoupons >= optionalAccommodationCouponGroup.get().getIssusedcount()){
            throw new AccommodationException(ErrorCode.EXCEEDED_COUNT_COUPON);
        }

        return accommodationCouponRepository.save(AccommodationCoupon.builder()
            .couponGroupId(couponGroupId)
            .usedYN(false)
            .customerId(customerId)
            .endTime(optionalAccommodationCouponGroup.get().getEndTime())
            .build());
    }

    public List<AccommodationCouponDto> getAccommodationAllCoupon(Long customerId) {
        List<AccommodationCoupon> accommodationCouponList = accommodationCouponRepository.findByCustomerIdAndUsedYN(
            customerId, false).orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_COUPON));

        List<AccommodationCouponDto> accommodationCouponDtoList = new ArrayList<>();

        for (AccommodationCoupon i : accommodationCouponList) {
            accommodationCouponDtoList.add(AccommodationCouponDto.from(i, accommodationCouponGroupRepository.findById(i.getCouponGroupId()).get().getSalePrice()));
        }
        return accommodationCouponDtoList;
    }
}
