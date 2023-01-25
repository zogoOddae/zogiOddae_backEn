package com.zerobase.leisure.service.coupon;

import com.zerobase.leisure.domain.dto.coupon.LeisureCouponGroupDto;
import com.zerobase.leisure.domain.entity.coupon.LeisureCouponGroup;
import com.zerobase.leisure.domain.form.AddLeisureCouponGroupForm;
import com.zerobase.leisure.domain.repository.coupon.LeisureCouponGroupRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureCouponGroupService {

    private final LeisureCouponGroupRepository leisureCouponGroupRepository;

    public LeisureCouponGroup addLeisureCouponGroup(AddLeisureCouponGroupForm form) {
        return leisureCouponGroupRepository.save(LeisureCouponGroup.of(form));
    }

    public LeisureCouponGroup updateLeisureCouponGroup(AddLeisureCouponGroupForm form) {
        LeisureCouponGroup leisureCouponGroup = findCouponGroup(form.getCouponGroupId());

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

    public Page<LeisureCouponGroupDto> getAllLeisureCouponGroup(Pageable pageable) {
        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("id"));

        Page<LeisureCouponGroup> leisureCouponGroups = leisureCouponGroupRepository.findAllByEndTimeIsAfter(
            LocalDate.now(), limit);

        List<LeisureCouponGroupDto> leisureCouponGroupDtos = new ArrayList<>();

        for(LeisureCouponGroup leisureCouponGroup : leisureCouponGroups){
            leisureCouponGroupDtos.add(LeisureCouponGroupDto.from(leisureCouponGroup));
        }

        return new PageImpl<>(leisureCouponGroupDtos, limit, leisureCouponGroups.getTotalElements());

    }
}
