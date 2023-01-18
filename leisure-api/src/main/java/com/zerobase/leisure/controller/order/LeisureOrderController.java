package com.zerobase.leisure.controller.order;

import com.zerobase.leisure.domain.form.LeisureOrderForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.order.LeisureOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/leisure/order")
@RequiredArgsConstructor
public class LeisureOrderController {

	private final LeisureOrderService leisureOrderService;
	@PostMapping
	public WebResponseData<String> LeisureOrder(@RequestParam Long customerId, @RequestBody LeisureOrderForm form) {
		leisureOrderService.LeisureOrder(customerId, form);
		return WebResponseData.ok("예약에 성공하였습니다.");
	}
}
