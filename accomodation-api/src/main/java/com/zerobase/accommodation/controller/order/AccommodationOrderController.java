package com.zerobase.accommodation.controller.order;

import com.zerobase.accommodation.domain.dto.order.AccommodationOrderCompleteDto;
import com.zerobase.accommodation.domain.dto.order.AccommodationOrderDto;
import com.zerobase.accommodation.domain.dto.order.AccommodationOrderListDto;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.order.AccommodationOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer/accommodation/order")
@RequiredArgsConstructor
public class AccommodationOrderController {

    private final AccommodationOrderService accommodationOrderService;

    @PostMapping
    public @ResponseBody WebResponseData<AccommodationOrderCompleteDto> accommodationOrder(@RequestParam Long accommodationPaymentId, Long customerId) {
//		if (memberDetails==null) {
//			throw new AccommodationException(ErrorCode.NOT_AUTHORIZED);
//		}
        return WebResponseData.ok( accommodationOrderService.accommodationOrder(customerId, accommodationPaymentId));
    }

    @GetMapping("/detail")
    public @ResponseBody WebResponseData<Page<AccommodationOrderListDto>> getAccommodationOrder(@RequestParam Long customerId,
        final Pageable pageable) {
//		if (memberDetails==null) {
//			throw new LeisureException(ErrorCode.NOT_AUTHORIZED);
//		}
        return WebResponseData.ok(accommodationOrderService.getAccommodationOrder(customerId,pageable));
    }
    @GetMapping
    public @ResponseBody WebResponseData<AccommodationOrderDto> getAccommodationOrder(@RequestParam Long orderId) {
//		if (memberDetails==null) {
//			throw new AccommodationException(ErrorCode.NOT_AUTHORIZED);
//		}
        return WebResponseData.ok(accommodationOrderService.getAccommodationOrderDetail(orderId));
    }

}
