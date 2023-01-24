package com.zerobase.accommodation.domain.dto.payment;

import com.zerobase.accommodation.domain.entity.payment.AccommodationPayment;
import com.zerobase.accommodation.domain.type.PaymentStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationPaymentDto {
    private Long paymentId;
    private Long customerId;
    private Long orderItemId; // 주문 상품 아이디

    private Long id;

    private Integer price;

    private String tid; //결제 고유 번호
    private String paymentToken; //결제 승인 인증 토큰

    private PaymentStatus status;

    private String canceledAt;
    private String nextRedirectURL;

    private String approveURL;


    public static AccommodationPaymentDto from(AccommodationPayment accommodationPayment, String nextRedirectURL, String approveURL) {
        return AccommodationPaymentDto.builder()
            .paymentId(accommodationPayment.getId())
            .customerId(accommodationPayment.getCustomerId())
            .orderItemId(accommodationPayment.getAccommodationOrderItemId())
            .id(accommodationPayment.getAccommodationId())
            .price(accommodationPayment.getPrice())
            .tid(accommodationPayment.getTid())
            .paymentToken(accommodationPayment.getPaymentToken())
            .status(accommodationPayment.getStatus())
            .canceledAt(accommodationPayment.getCanceledAt().toString())
            .nextRedirectURL(nextRedirectURL)
            .approveURL(approveURL)
            .build();
    }

    public static AccommodationPaymentDto from(AccommodationPayment accommodationPayment) {
        return AccommodationPaymentDto.builder()
            .paymentId(accommodationPayment.getId())
            .customerId(accommodationPayment.getCustomerId())
            .orderItemId(accommodationPayment.getAccommodationOrderItemId())
            .id(accommodationPayment.getAccommodationId())
            .price(accommodationPayment.getPrice())
            .tid(accommodationPayment.getTid())
            .paymentToken(accommodationPayment.getPaymentToken())
            .status(accommodationPayment.getStatus())
            .canceledAt(accommodationPayment.getCanceledAt().toString())
            .build();
    }
}
