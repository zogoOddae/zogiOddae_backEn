package com.zerobase.accommodation.controller.payment;

import com.zerobase.accommodation.domain.dto.coupon.AccommodationCouponDto;
import com.zerobase.accommodation.domain.dto.payment.AccommodationPaymentDto;
import com.zerobase.accommodation.domain.form.payment.AccommodationPaymentForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.payment.AccommodationPaymentService;
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
@RequestMapping("/customer/accommodation/payment")
@RequiredArgsConstructor
public class AccommodationPaymentController {
    //카카오API는 결제 준비 > 결제 요청 - 결제 준비에서 리턴된 URL로 카카오페이와 연결 > 카카오페이(폰)에서 결제 후 결제 승인 이 순서로 진행됩니다.

    private final AccommodationPaymentService accommodationPaymentService;

    @PostMapping("/kakaopay")
    public WebResponseData<AccommodationPaymentDto> kakaopayReady(@RequestBody AccommodationPaymentForm form) {
        return WebResponseData.ok(accommodationPaymentService.getPaymentReady(form));
    }


    @GetMapping("/kakaopay/approve")
    public WebResponseData<AccommodationPaymentDto> kakaopayApprove( @RequestParam Long accommodationPaymentId, @RequestParam(value = "pg_token") String pgtoken) {
        return WebResponseData.ok(
            AccommodationPaymentDto.from(accommodationPaymentService.paymentSuccess(pgtoken, accommodationPaymentId)));
    }

    @GetMapping("/kakaopay/cancel")
    public WebResponseData<AccommodationPaymentDto> kakaopayCancel(@RequestParam Long accommodationPaymentId ) {
        return WebResponseData.ok(
            AccommodationPaymentDto.from(accommodationPaymentService.paymentCancel(accommodationPaymentId)));
    }

    @GetMapping("/kakaopay/fail")
    public WebResponseData<String> kakaopayFail() {
        return WebResponseData.ok("결제 요청에 실패했습니다. 다시 시도해주세요.");
    }


    //내 결제 내역
    @GetMapping
    public WebResponseData<Page<AccommodationPaymentDto>> getAccommodationPayment(@RequestParam Long customerId, final Pageable pageable) {
        Page<AccommodationPaymentDto> accommodationPaymentDtos = accommodationPaymentService.getAccommodationPayment(customerId,pageable);
        return WebResponseData.ok(accommodationPaymentDtos);
    }
}
