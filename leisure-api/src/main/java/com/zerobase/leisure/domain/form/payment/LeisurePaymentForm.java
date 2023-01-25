package com.zerobase.leisure.domain.form.payment;

import com.zerobase.leisure.domain.type.PaymentStatus;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class LeisurePaymentForm {
    private Long orderItemId; // 주문 상품 아이디

    private Long productId;

    private Integer price;

    private String tid; //결제 고유 번호
    private String paymentToken; //결제 승인 인증 토큰

    private PaymentStatus status;

    private LocalDateTime canceledAt;
}
