package com.zerobase.accomodation.controller.coupon;

import com.zerobase.accomodation.domain.dto.coupon.AccomodationCouponGroupDto;
import com.zerobase.accomodation.domain.form.AddAccomodationCouponGroupForm;
import com.zerobase.accomodation.domain.model.WebResponseData;
import com.zerobase.accomodation.service.coupon.AccomodationCouponGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accomodation/couponGroup")
@RequiredArgsConstructor
public class AccomodationCouponGroupController {

    private final AccomodationCouponGroupService accomodationCouponGroupService;

    @PostMapping
    public WebResponseData<AccomodationCouponGroupDto> addAccomodationCouponGroup(@RequestBody AddAccomodationCouponGroupForm form) {
        return WebResponseData.ok(
            AccomodationCouponGroupDto.from(accomodationCouponGroupService.addAccomodationCouponGroup(form)));
    }

    @PutMapping
    public WebResponseData<AccomodationCouponGroupDto> updateAccomodationCouponGroup(@RequestBody AddAccomodationCouponGroupForm form) {
        return WebResponseData.ok(
            AccomodationCouponGroupDto.from(accomodationCouponGroupService.updateAccomodationCouponGroup(form)));
    }

    @DeleteMapping
    public WebResponseData<String> deleteAccomodationCouponGroup(@RequestParam Long accomodationCouponGroupId) {
        accomodationCouponGroupService.deleteAccomodationCouponGroup(accomodationCouponGroupId);

        return WebResponseData.ok("성공적으로 삭제되었습니다.");
    }
}
