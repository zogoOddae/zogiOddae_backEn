package com.zerobase.accommodation.controller.coupon;

import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponGroupDto;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationCouponGroupForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.coupon.AccommodationCouponGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation/couponGroup")
@RequiredArgsConstructor
public class AccommodationCouponGroupController {

    private final AccommodationCouponGroupService accommodationCouponGroupService;

    @PostMapping
    public WebResponseData<AccommodationCouponGroupDto> addAccommodationCouponGroup(@RequestBody AddAccommodationCouponGroupForm form) {
        return WebResponseData.ok(
            AccommodationCouponGroupDto.from(accommodationCouponGroupService.addAccommodationCouponGroup(form)));
    }

    @PutMapping
    public WebResponseData<AccommodationCouponGroupDto> updateAccommodationCouponGroup(@RequestBody AddAccommodationCouponGroupForm form) {
        return WebResponseData.ok(
            AccommodationCouponGroupDto.from(accommodationCouponGroupService.updateAccommodationCouponGroup(form)));
    }

    @DeleteMapping
    public WebResponseData<String> deleteAccommodationCouponGroup(@RequestParam Long accommodationCouponGroupId) {
        accommodationCouponGroupService.deleteAccommodationCouponGroup(accommodationCouponGroupId);
        return WebResponseData.ok("성공적으로 삭제되었습니다.");
    }
}
