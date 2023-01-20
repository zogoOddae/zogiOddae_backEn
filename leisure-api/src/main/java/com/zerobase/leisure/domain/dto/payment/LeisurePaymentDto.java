package com.zerobase.leisure.domain.dto.payment;

import com.zerobase.leisure.domain.entity.order.LeisurePayment;
import com.zerobase.leisure.domain.type.PaymentStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisurePaymentDto {
    private Long leisurePaymentId;
    private Long customerId;
    private Long leisureOrderItemId; // 주문 상품 아이디

    private Long leisureId;

    private Integer price;

    private String tid; //결제 고유 번호
    private String paymentToken; //결제 승인 인증 토큰

    private PaymentStatus status;

    private LocalDateTime canceledAt;
    private String nextRedirectURL;

    private String approveURL;

    public static LeisurePaymentDto from(LeisurePayment leisurePayment, String nextRedirectURL, String approveURL) {
        return LeisurePaymentDto.builder()
            .leisurePaymentId(leisurePayment.getId())
            .customerId(leisurePayment.getCustomerId())
            .leisureOrderItemId(leisurePayment.getLeisureOrderItemId())
            .leisureId(leisurePayment.getLeisureId())
            .price(leisurePayment.getPrice())
            .tid(leisurePayment.getTid())
            .paymentToken(leisurePayment.getPaymentToken())
            .status(leisurePayment.getStatus())
            .canceledAt(leisurePayment.getCanceledAt())
            .nextRedirectURL(nextRedirectURL)
            .approveURL(approveURL)
            .build();
    }

    public static LeisurePaymentDto from(LeisurePayment leisurePayment) {
        return LeisurePaymentDto.builder()
            .leisurePaymentId(leisurePayment.getId())
            .customerId(leisurePayment.getCustomerId())
            .leisureOrderItemId(leisurePayment.getLeisureOrderItemId())
            .leisureId(leisurePayment.getLeisureId())
            .price(leisurePayment.getPrice())
            .tid(leisurePayment.getTid())
            .paymentToken(leisurePayment.getPaymentToken())
            .status(leisurePayment.getStatus())
            .canceledAt(leisurePayment.getCanceledAt())
            .build();
    }
}
