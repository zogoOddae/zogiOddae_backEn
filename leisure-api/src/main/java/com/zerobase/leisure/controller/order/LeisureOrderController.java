package com.zerobase.leisure.controller.order;

import com.zerobase.common.auth.MemberDetails;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import com.zerobase.leisure.service.order.LeisureOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leisure/order")
@RequiredArgsConstructor
public class LeisureOrderController {

	private final LeisureOrderService leisureOrderService;
	@PostMapping
	public WebResponseData<String> LeisureOrder(@AuthenticationPrincipal MemberDetails memberDetails) {
		if (memberDetails==null) {
			throw new LeisureException(ErrorCode.NOT_AUTHORIZED);
		}
		leisureOrderService.LeisureOrder(memberDetails.getId());
		return WebResponseData.ok("예약에 성공하였습니다.");
	}
}
