package com.zerobase.accommodation.service.coupon;

import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponGroupDto;
import com.zerobase.accommodation.domain.entity.coupon.AccommodationCouponGroup;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationCouponGroupForm;
import com.zerobase.accommodation.domain.repository.coupon.AccommodationCouponGroupRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import java.time.LocalDateTime;
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
public class AccommodationCouponGroupService {

    private final AccommodationCouponGroupRepository accommodationCouponGroupRepository;

    public AccommodationCouponGroup addAccommodationCouponGroup(AddAccommodationCouponGroupForm form) {
        return  accommodationCouponGroupRepository.save(AccommodationCouponGroup.of(form));
    }

    public AccommodationCouponGroup updateAccommodationCouponGroup(
        AddAccommodationCouponGroupForm form) {
        AccommodationCouponGroup accommodationCouponGroup = findCouponGroup(form.getAccommodationCouponGroupId());

        accommodationCouponGroup.setId(form.getAccommodationCouponGroupId());
        accommodationCouponGroup.setCouponTarget(form.getCouponTarget());
        accommodationCouponGroup.setSalePrice(form.getSalePrice());
        accommodationCouponGroup.setIssusedcount(form.getIssuedCount());
        accommodationCouponGroup.setEndTime(form.getEndTime());

        return accommodationCouponGroupRepository.save(accommodationCouponGroup);
    }

    public void deleteAccommodationCouponGroup(Long accommodationCouponGroupId) {
        findCouponGroup(accommodationCouponGroupId);

        accommodationCouponGroupRepository.deleteById(accommodationCouponGroupId);
    }

    private AccommodationCouponGroup findCouponGroup(Long accommodationCouponGroupId){
        return accommodationCouponGroupRepository.findById(accommodationCouponGroupId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_REGISTERED_COUPON_GROUP));
    }

    public Page<AccommodationCouponGroupDto> getAllAccommodationCouponGroup(Pageable pageable) {
        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("id"));

        Page<AccommodationCouponGroup> accommodationCouponGroups = accommodationCouponGroupRepository.findAllByEndTimeIsAfter(LocalDateTime.now(), limit);

        List<AccommodationCouponGroupDto> accommodationCouponGroupDtos = new ArrayList<>();

        for(AccommodationCouponGroup accommodationCouponGroup : accommodationCouponGroups){
            accommodationCouponGroupDtos.add(AccommodationCouponGroupDto.from(accommodationCouponGroup));
        }

        return new PageImpl<>(accommodationCouponGroupDtos, limit, accommodationCouponGroups.getTotalElements());


    }
}
