package com.zerobase.accommodation.service.payment;

import com.zerobase.accommodation.domain.dto.payment.AccommodationPaymentDto;
import com.zerobase.accommodation.domain.entity.order.AccommodationOrderItem;
import com.zerobase.accommodation.domain.entity.payment.AccommodationPayment;
import com.zerobase.accommodation.domain.form.payment.AccommodationPaymentForm;
import com.zerobase.accommodation.domain.repository.order.AccommodationOrderItemRepository;
import com.zerobase.accommodation.domain.repository.payment.AccommodationPaymentRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.domain.type.PaymentStatus;
import com.zerobase.accommodation.exception.AccommodationException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationPaymentService {

    private final AccommodationPaymentRepository accommodationPaymentRepository;
    private final AccommodationOrderItemRepository accommodationOrderItemRepository;

    private static final String AUTHORIZATION = "KakaoAK 5d569ea19c6b8b53c9342d4d65a394e6"; //카카오페이 api 키
    private static final String CONTENTTYPE = "application/x-www-form-urlencoded;charset=utf-8";

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AUTHORIZATION);
        headers.add("Content-type", CONTENTTYPE);

        return headers;
    }

    public AccommodationPaymentDto getPaymentReady(AccommodationPaymentForm form) {
        //결제 준비
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready"; //카카오 APi URL

        //상품 주문에서 상품 주문관련 데이터 가져오기
        AccommodationOrderItem accommodationOrderItem = accommodationOrderItemRepository.findById(form.getAccommodationOrderItemId())
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION_ORDER_ITEM));

        double vat_ammount = form.getPrice() * 0.1;

        String parameter = "cid=TC0ONETIME" // 가맹점 코드 - 테스트용으로 고정
            + "&partner_order_id=" + form.getAccommodationOrderItemId()// 가맹점 주문번호를 상품주문 ID로 사용
            + "&partner_user_id=" + form.getCustomerId() // 가맹점 회원 id
            + "&item_name=" + accommodationOrderItem.getAccommodationName() // 상품명
            + "&quantity=1" // 상품 수량
            + "&total_amount=" + form.getPrice().toString() // 총 금액
            + "&vat_amount=" + vat_ammount  //부가세
            + "&tax_free_amount = 0" // 상품 비과세 금액
            + "&approval_url=http://localhost:8080/accommodation/payment/kakaopay/approve" // 결제 성공 시
            + "&fail_url=http://localhost:8080/accommodation/payment/kakaopay/fail" // 결제 실패 시
            + "&cancel_url=http://localhost:8080/accommodation/payment/kakaopay/cancel"; // 결제 취소 시

        Map<String, String> map = restTemplate.postForObject(url, new HttpEntity<>(parameter, getHeaders()), Map.class);

        AccommodationPayment accommodationPayment = AccommodationPayment.builder()
            .price(form.getPrice())
            .customerId(form.getCustomerId())
            .accommodationOrderItemId(form.getAccommodationOrderItemId())
            .accommodationId(form.getAccommodationId())
            .status(PaymentStatus.PAYMENT_WAIT)
            .tid(map.get("tid"))
            .build();

        log.info(map.toString());

        accommodationPaymentRepository.save(accommodationPayment);


        return AccommodationPaymentDto.from(accommodationPayment, map.get("next_redirect_pc_url"));
    }

    public AccommodationPayment paymentSuccess(String pgtoken, AccommodationPaymentForm form) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";

        String parameter = "cid=TC0ONETIME"
            + "&tid=" + form.getTid()
            + "&partner_order_id=" + form.getAccommodationOrderItemId()
            + "&partner_user_id=" + form.getCustomerId()
            + "&pg_token=" + pgtoken;

        Map<String, String> map = restTemplate.postForObject(url, new HttpEntity<>(parameter, getHeaders()), Map.class);

        log.info(map.toString());

        AccommodationPayment payment = accommodationPaymentRepository.findByTid(form.getPaymentToken())
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_PAYMENT));

        payment.setStatus(PaymentStatus.PAID);
        payment.setPaymentToken(pgtoken);

        return accommodationPaymentRepository.save(payment);
    }

    public AccommodationPayment paymentCancel(AccommodationPaymentForm form) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/cancel";

        String parameter = "cid=TC0ONETIME"
            + "&tid=" + form.getTid()
            + "&cancel_amount=" + form.getPrice()
            + "&cancel_tax_free_amount=0"
            + "&pg_token=" + form.getPaymentToken();

        Map<String, String> map = restTemplate.postForObject(url, new HttpEntity<>(parameter, getHeaders()), Map.class);

        log.info(map.toString());

        AccommodationPayment payment = accommodationPaymentRepository.findByTid(form.getPaymentToken())
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_PAYMENT));

        payment.setStatus(PaymentStatus.CANCELED);

        return accommodationPaymentRepository.save(payment);
    }
}
