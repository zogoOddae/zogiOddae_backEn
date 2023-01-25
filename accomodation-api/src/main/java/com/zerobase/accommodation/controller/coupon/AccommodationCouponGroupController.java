package com.zerobase.accommodation.controller.coupon;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationBlackListDto;
import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponGroupDto;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationCouponGroupForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.coupon.AccommodationCouponGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccommodationCouponGroupController {

    private final AccommodationCouponGroupService accommodationCouponGroupService;

    @PostMapping("/admin/accommodation/couponGroup")
    public WebResponseData<String> addAccommodationCouponGroup(@RequestBody AddAccommodationCouponGroupForm form) {
        accommodationCouponGroupService.addAccommodationCouponGroup(form);
        return WebResponseData.ok("성공적으로 등록되었습니다.");
    }

    @GetMapping("/accommodation/couponGroup")
    public WebResponseData<Page<AccommodationCouponGroupDto>> getAccommodationAllCouponGroup(final Pageable pageable) {
        Page<AccommodationCouponGroupDto> accommodationCouponGroupDtos = accommodationCouponGroupService.getAllAccommodationCouponGroup(pageable);
        return WebResponseData.ok(accommodationCouponGroupDtos);
    }

    @PutMapping("/admin/accommodation/couponGroup")
    public WebResponseData<String> updateAccommodationCouponGroup(@RequestBody AddAccommodationCouponGroupForm form) {
        accommodationCouponGroupService.updateAccommodationCouponGroup(form);
        return WebResponseData.ok("성공적으로 수정되었습니다.");
    }

    @DeleteMapping("/admin/accommodation/couponGroup")
    public WebResponseData<String> deleteAccommodationCouponGroup(@RequestParam Long accommodationCouponGroupId) {
        accommodationCouponGroupService.deleteAccommodationCouponGroup(accommodationCouponGroupId);
        return WebResponseData.ok("성공적으로 삭제되었습니다.");
    }
}
