package com.zerobase.accommodation.controller.coupon;

import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponDto;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationCouponForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.coupon.AccommodationCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation/coupon")
@RequiredArgsConstructor
public class AccommodationCouponController {

    private final AccommodationCouponService accommodationCouponService;

    @PostMapping
    public WebResponseData<AccommodationCouponDto> issuedAccommodationCoupon(@RequestBody AddAccommodationCouponForm form) {
        return WebResponseData.ok(
            AccommodationCouponDto.from(accommodationCouponService.issuedAccommodationCoupon(form)));
    }
}
