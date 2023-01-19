package com.zerobase.leisure.service.payment;

import com.zerobase.leisure.domain.dto.payment.LeisurePaymentDto;
import com.zerobase.leisure.domain.entity.order.LeisureOrderItem;
import com.zerobase.leisure.domain.entity.order.LeisurePayment;
import com.zerobase.leisure.domain.form.payment.LeisurePaymentForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.repository.order.LeisureOrderItemRepository;
import com.zerobase.leisure.domain.repository.order.LeisurePaymentRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.domain.type.PaymentStatus;
import com.zerobase.leisure.exception.LeisureException;
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
public class LeisurePaymentService {

    private final LeisurePaymentRepository leisurePaymentRepository;
    private final LeisureOrderItemRepository leisureOrderItemRepository;
    private final LeisureRepository leisureRepository;

    private static final String AUTHORIZATION = "KakaoAK 5d569ea19c6b8b53c9342d4d65a394e6"; //카카오페이 api 키
    private static final String CONTENTTYPE = "application/x-www-form-urlencoded;charset=utf-8";

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AUTHORIZATION);
        headers.add("Content-type", CONTENTTYPE);

        return headers;
    }

    public LeisurePaymentDto getPaymentReady(LeisurePaymentForm form) {
        //결제 준비
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready"; //카카오 APi URL

        //상품 주문에서 상품 주문관련 데이터 가져오기
        LeisureOrderItem leisureOrderItem = leisureOrderItemRepository.findById(form.getLeisureOrderItemId())
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_ORDER_ITEM));

        double vat_amount = form.getPrice() * 0.1;

        String parameter = "cid=TC0ONETIME" // 가맹점 코드 - 테스트용으로 고정
            + "&partner_order_id=" + form.getLeisureOrderItemId()// 가맹점 주문번호를 상품주문 ID로 사용
            + "&partner_user_id=" + form.getCustomerId() // 가맹점 회원 id
            + "&item_name=" + leisureRepository.findById(leisureOrderItem.getLeisureId())
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE)).getLeisureName()// 상품명
            + "&quantity=1" // 상품 수량
            + "&total_amount=" + form.getPrice().toString() // 총 금액
            + "&vat_amount=" + vat_amount  //부가세
            + "&tax_free_amount = 0" // 상품 비과세 금액
            + "&approval_url=http://localhost:8080/accommodation/payment/kakaopay/approve" // 결제 성공 시
            + "&fail_url=http://localhost:8080/accommodation/payment/kakaopay/fail" // 결제 실패 시
            + "&cancel_url=http://localhost:8080/accommodation/payment/kakaopay/cancel"; // 결제 취소 시

        Map<String, String> map = restTemplate.postForObject(url, new HttpEntity<>(parameter, getHeaders()), Map.class);

        LeisurePayment accommodationPayment = LeisurePayment.builder()
            .price(form.getPrice())
            .customerId(form.getCustomerId())
            .leisureOrderItemId(form.getLeisureOrderItemId())
            .leisureId(form.getLeisureId())
            .status(PaymentStatus.PAYMENT_WAIT)
            .tid(map.get("tid"))
            .build();

        log.info(map.toString());

        leisurePaymentRepository.save(accommodationPayment);


        return LeisurePaymentDto.from(accommodationPayment, map.get("next_redirect_pc_url"));
    }

    public LeisurePayment paymentSuccess(String pgtoken, LeisurePaymentForm form) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";

        String parameter = "cid=TC0ONETIME"
            + "&tid=" + form.getTid()
            + "&partner_order_id=" + form.getLeisureOrderItemId()
            + "&partner_user_id=" + form.getCustomerId()
            + "&pg_token=" + pgtoken;

        Map<String, String> map = restTemplate.postForObject(url, new HttpEntity<>(parameter, getHeaders()), Map.class);

        log.info(map.toString());

        LeisurePayment payment = leisurePaymentRepository.findByTid(form.getPaymentToken())
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_PAYMENT));

        payment.setStatus(PaymentStatus.PAID);
        payment.setPaymentToken(pgtoken);

        return leisurePaymentRepository.save(payment);
    }

    public LeisurePayment paymentCancel(LeisurePaymentForm form) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/cancel";

        String parameter = "cid=TC0ONETIME"
            + "&tid=" + form.getTid()
            + "&cancel_amount=" + form.getPrice()
            + "&cancel_tax_free_amount=0"
            + "&pg_token=" + form.getPaymentToken();

        Map<String, String> map = restTemplate.postForObject(url, new HttpEntity<>(parameter, getHeaders()), Map.class);

        log.info(map.toString());

        LeisurePayment payment = leisurePaymentRepository.findByTid(form.getPaymentToken())
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_PAYMENT));

        payment.setStatus(PaymentStatus.CANCELED);

        return leisurePaymentRepository.save(payment);
    }
}
