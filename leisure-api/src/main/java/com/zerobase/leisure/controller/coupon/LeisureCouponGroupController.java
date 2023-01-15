package com.zerobase.leisure.controller.coupon;

import com.zerobase.leisure.domain.dto.coupon.LeisureCouponGroupDto;
import com.zerobase.leisure.domain.form.AddLeisureCouponGroupForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.coupon.LeisureCouponGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leisure/couponGroup")
@RequiredArgsConstructor
public class LeisureCouponGroupController {

    private final LeisureCouponGroupService leisureCouponGroupService;

    @PostMapping
    public WebResponseData<LeisureCouponGroupDto> addLeisureCouponGroup(@RequestBody AddLeisureCouponGroupForm form) {
        return WebResponseData.ok(
            LeisureCouponGroupDto.from(leisureCouponGroupService.addLeisureCouponGroup(form)));
    }

    @PutMapping
    public WebResponseData<LeisureCouponGroupDto> updateLeisureCouponGroup(@RequestBody AddLeisureCouponGroupForm form) {
        return WebResponseData.ok(
            LeisureCouponGroupDto.from(leisureCouponGroupService.updateLeisureCouponGroup(form)));
    }

    @DeleteMapping
    public WebResponseData<String> deleteLeisureCouponGroup(@RequestParam Long LeisureCouponGroupId) {
        leisureCouponGroupService.deleteLeisureCouponGroup(LeisureCouponGroupId);

        return WebResponseData.ok("성공적으로 삭제되었습니다.");
    }
}
