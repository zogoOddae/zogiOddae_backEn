package com.zerobase.accomodation.controller.coupon;

import com.zerobase.accomodation.domain.dto.coupon.AccomodationCouponDto;
import com.zerobase.accomodation.domain.form.AddAccomodationCouponForm;
import com.zerobase.accomodation.domain.model.WebResponseData;
import com.zerobase.accomodation.service.coupon.AccomodationCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accomodation/coupon")
@RequiredArgsConstructor
public class AccomodationCouponController {

    private final AccomodationCouponService accomodationCouponService;

    @PostMapping
    public WebResponseData<AccomodationCouponDto> issuedAccomodationCoupon(@RequestBody AddAccomodationCouponForm form) {
        return WebResponseData.ok(
            AccomodationCouponDto.from(accomodationCouponService.issuedAccomodationCoupon(form)));
    }
}
