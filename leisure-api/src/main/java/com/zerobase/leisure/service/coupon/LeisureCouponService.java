package com.zerobase.leisure.service.coupon;

import com.zerobase.leisure.domain.dto.coupon.LeisureCouponDto;
import com.zerobase.leisure.domain.entity.coupon.LeisureCoupon;
import com.zerobase.leisure.domain.entity.coupon.LeisureCouponGroup;
import com.zerobase.leisure.domain.repository.coupon.LeisureCouponGroupRepository;
import com.zerobase.leisure.domain.repository.coupon.LeisureCouponRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
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

        if(LocalDate.now().isAfter(optionalLeisureCouponGroup.get().getEndTime())){
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

    public Page<LeisureCouponDto> getLeisureAllCoupon(Long customerId, Pageable pageable) {
        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("id"));

        Page<LeisureCoupon> leisureCouponPage
            = leisureCouponRepository.findAllByCustomerIdAndUsedYNFalse(customerId,limit);

        List<LeisureCouponDto> leisureCouponDtos = new ArrayList<>();

        for(LeisureCoupon leisureCoupon : leisureCouponPage){
            leisureCouponDtos.add(LeisureCouponDto.from(leisureCoupon));
        }

        return new PageImpl<>(leisureCouponDtos, limit, leisureCouponPage.getTotalElements());
    }
}
