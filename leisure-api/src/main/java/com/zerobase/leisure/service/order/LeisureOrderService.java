package com.zerobase.leisure.service.order;

import com.zerobase.leisure.domain.form.LeisureOrderForm;
import com.zerobase.leisure.domain.repository.order.LeisureCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureOrderService {

	private LeisureCartRepository leisureCartRepository;
	public void LeisureOrder(Long customerId) {

	}
}
