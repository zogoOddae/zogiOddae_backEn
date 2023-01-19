package com.zerobase.leisure.controller.order;

import com.zerobase.leisure.domain.dto.leisure.LeisureOrderItemDto;
import com.zerobase.leisure.domain.form.AddLeisureCartForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.order.LeisureCartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/leisure/cart")
@RequiredArgsConstructor
public class LeisureCartController {
	private final LeisureCartService leisureCartService;

	@PostMapping
	public @ResponseBody WebResponseData<String> addLeisureCart(@RequestParam Long customerId, @RequestBody AddLeisureCartForm form){
		leisureCartService.addLeisureCart(customerId, form);
		return WebResponseData.ok("장바구니에 담았습니다.");
	}

	@DeleteMapping
	public @ResponseBody WebResponseData<String> deleteLeisureCart(@RequestParam Long customerId, Long leisureOrderItemId){
		leisureCartService.deleteLeisureCart(customerId, leisureOrderItemId);
		return WebResponseData.ok("장바구니에서 제거했습니다.");
	}

	@GetMapping
	public @ResponseBody WebResponseData<List<LeisureOrderItemDto>> getLeisureCart(@RequestParam Long customerId){
		return WebResponseData.ok(leisureCartService.getLeisureCart(customerId));
	}
}
