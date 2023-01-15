package com.zerobase.leisure.controller.coupon;

import com.zerobase.leisure.domain.dto.coupon.LeisureCouponDto;
import com.zerobase.leisure.domain.form.AddLeisureCouponForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.coupon.LeisureCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leisure/coupon")
@RequiredArgsConstructor
public class LeisureCouponController {

    private final LeisureCouponService leisureCouponService;

    @PostMapping
    public WebResponseData<LeisureCouponDto> issuedLeisureCoupon(@RequestBody AddLeisureCouponForm form) {
        return WebResponseData.ok(
            LeisureCouponDto.from(leisureCouponService.issuedLeisureCoupon(form)));
    }
}
