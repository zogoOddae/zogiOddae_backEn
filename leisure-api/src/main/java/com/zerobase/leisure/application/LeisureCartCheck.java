package com.zerobase.leisure.application;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.order.LeisureCart;
import com.zerobase.leisure.domain.entity.order.LeisureOrderItem;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.repository.order.LeisureCartRepository;
import com.zerobase.leisure.domain.repository.order.LeisureOrderItemRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LeisureCartCheck {

	private final LeisureRepository leisureRepository;
	private final LeisureOrderItemRepository leisureOrderItemRepository;

	@Transactional
	public void cartCheck(LeisureCart leisureCart, LeisureOrderItem leisureOrderItem) {
		Optional<Leisure> optionalLeisure = leisureRepository.findById(leisureOrderItem.getLeisureId());

		if (!optionalLeisure.isPresent()) {
			leisureCart.setTotalPrice(leisureCart.getTotalPrice()-leisureOrderItem.getPrice());
			leisureOrderItemRepository.deleteById(leisureOrderItem.getId());
			return;
		}

		Leisure leisure = optionalLeisure.get();
		if (!leisure.getPrice().equals(leisureOrderItem.getPrice()+leisureOrderItem.getSalePrice())) {
			leisureCart.setTotalPrice(leisureCart.getTotalPrice()-leisureOrderItem.getPrice()
			+leisure.getPrice()-leisureOrderItem.getSalePrice());
		}
	}
}
