package com.zerobase.accommodation.controller.coupon;

import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponDto;
import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponGroupDto;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationCouponForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.coupon.AccommodationCouponService;
import java.util.List;
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
@RequestMapping("/customer/accommodation/coupon")
@RequiredArgsConstructor
public class AccommodationCouponController {

    private final AccommodationCouponService accommodationCouponService;


    @PostMapping
    public WebResponseData<String> issuedAccommodationCoupon(@RequestParam Long customerId, Long couponGroupId) {
        accommodationCouponService.issuedAccommodationCoupon(customerId, couponGroupId);
        return WebResponseData.ok("쿠폰을 발급했습니다.");
    }

    @GetMapping
    public WebResponseData<List<AccommodationCouponDto>> getAccommodationAllCoupon(@RequestParam Long customerId) {
        return WebResponseData.ok( accommodationCouponService.getAccommodationAllCoupon(customerId));
    }
}
