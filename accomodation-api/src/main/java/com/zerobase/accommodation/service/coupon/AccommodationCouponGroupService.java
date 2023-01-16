package com.zerobase.accommodation.service.coupon;

import com.zerobase.accommodation.domain.entity.coupon.AccommodationCouponGroup;
import com.zerobase.accommodation.domain.form.AddAccommodationCouponGroupForm;
import com.zerobase.accommodation.domain.repository.coupon.AccommodationCouponGroupRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
import lombok.RequiredArgsConstructor;
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
        AccommodationCouponGroup accommodationCouponGroup = findCouponGroup(form.getAccommodationCouponGroupid());

        accommodationCouponGroup.setId(form.getAccommodationCouponGroupid());
        accommodationCouponGroup.setCouponTarget(form.getCouponTarget());
        accommodationCouponGroup.setSalePrice(form.getSalePrice());
        accommodationCouponGroup.setIssusedcount(form.getIssusedcount());
        accommodationCouponGroup.setEndTime(form.getEndTime());

        return accommodationCouponGroupRepository.save(accommodationCouponGroup);
    }

    public void deleteAccommodationCouponGroup(Long accommodationCouponGroupId) {
        findCouponGroup(accommodationCouponGroupId);

        accommodationCouponGroupRepository.deleteById(accommodationCouponGroupId);
    }

    private AccommodationCouponGroup findCouponGroup(Long accommodationCouponGroupId){
        return accommodationCouponGroupRepository.findById(accommodationCouponGroupId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_REGISTERED_COUPONGROUP));
    }

}
