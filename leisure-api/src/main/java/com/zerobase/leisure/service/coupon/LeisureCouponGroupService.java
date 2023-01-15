package com.zerobase.leisure.service.coupon;

import com.zerobase.leisure.domain.entity.coupon.LeisureCouponGroup;
import com.zerobase.leisure.domain.form.AddLeisureCouponGroupForm;
import com.zerobase.leisure.domain.repository.coupon.LeisureCouponGroupRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureCouponGroupService {

    private final LeisureCouponGroupRepository leisureCouponGroupRepository;

    public LeisureCouponGroup addLeisureCouponGroup(AddLeisureCouponGroupForm form) {
        return leisureCouponGroupRepository.save(LeisureCouponGroup.of(form));
    }

    public LeisureCouponGroup updateLeisureCouponGroup(AddLeisureCouponGroupForm form) {
        LeisureCouponGroup leisureCouponGroup = findCouponGroup(form.getLeisureCouponGroupId());

        leisureCouponGroup.setId(form.getLeisureCouponGroupId());
        leisureCouponGroup.setCouponTarget(form.getCouponTarget());
        leisureCouponGroup.setSalePrice(form.getSalePrice());
        leisureCouponGroup.setIssuedCount(form.getIssuedCount());
        leisureCouponGroup.setEndTime(form.getEndTime());

        return leisureCouponGroupRepository.save(leisureCouponGroup);
    }

    public void deleteLeisureCouponGroup(Long LeisureCouponGroupId) {
        findCouponGroup(LeisureCouponGroupId);

        leisureCouponGroupRepository.deleteById(LeisureCouponGroupId);
    }

    private LeisureCouponGroup findCouponGroup(Long LeisureCouponGroupId){
        return leisureCouponGroupRepository.findById(LeisureCouponGroupId)
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_REGISTERED_COUPON_GROUP));
    }

}
