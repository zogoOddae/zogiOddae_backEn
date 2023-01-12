package com.zerobase.accomodation.service.coupon;

import com.zerobase.accomodation.domain.entity.coupon.AccomodationCouponGroup;
import com.zerobase.accomodation.domain.form.AddAccomodationCouponGroupForm;
import com.zerobase.accomodation.domain.repository.coupon.AccomodationCouponGroupRepository;
import com.zerobase.accomodation.domain.type.ErrorCode;
import com.zerobase.accomodation.exception.AccomodationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccomodationCouponGroupService {

    private final AccomodationCouponGroupRepository accomodationCouponGroupRepository;

    public AccomodationCouponGroup addAccomodationCouponGroup(AddAccomodationCouponGroupForm form) {
        return  accomodationCouponGroupRepository.save(AccomodationCouponGroup.of(form));
    }

    public AccomodationCouponGroup updateAccomodationCouponGroup(AddAccomodationCouponGroupForm form) {
        AccomodationCouponGroup accomodationCouponGroup = findCouponGroup(form.getAccomodationCouponGroupid());

        accomodationCouponGroup.setId(form.getAccomodationCouponGroupid());
        accomodationCouponGroup.setCouponTarget(form.getCouponTarget());
        accomodationCouponGroup.setSalePrice(form.getSalePrice());
        accomodationCouponGroup.setIssusedcount(form.getIssusedcount());
        accomodationCouponGroup.setEndTime(form.getEndTime());

        return accomodationCouponGroupRepository.save(accomodationCouponGroup);
    }

    public void deleteAccomodationCouponGroup(Long accomodationCouponGroupId) {
        findCouponGroup(accomodationCouponGroupId);

        accomodationCouponGroupRepository.deleteById(accomodationCouponGroupId);
    }

    private AccomodationCouponGroup findCouponGroup(Long accomodationCouponGroupId){
        return accomodationCouponGroupRepository.findById(accomodationCouponGroupId)
            .orElseThrow(() -> new AccomodationException(ErrorCode.NOT_REGISTERED_COUPONGROUP));
    }

}
