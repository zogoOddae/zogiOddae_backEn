package com.zerobase.accommodation.controller.coupon;

import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponDto;
import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponGroupDto;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationCouponForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.coupon.AccommodationCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //내 쿠폰 목록
    @GetMapping
    public WebResponseData<Page<AccommodationCouponDto>> getAccommodationAllCoupon(@RequestParam Long customerId, final Pageable pageable) {
        Page<AccommodationCouponDto> accommodationCouponDtos = accommodationCouponService.getAccommodationAllCoupon(customerId,pageable);
        return WebResponseData.ok(accommodationCouponDtos);
    }
}
