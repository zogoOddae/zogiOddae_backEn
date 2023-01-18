package com.zerobase.leisure.service.order;

import com.zerobase.leisure.application.LeisureCartCheck;
import com.zerobase.leisure.domain.dto.leisure.LeisureOrderItemDto;
import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.order.LeisureCart;
import com.zerobase.leisure.domain.entity.order.LeisureOrderItem;
import com.zerobase.leisure.domain.form.AddLeisureCartForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.repository.order.LeisureCartRepository;
import com.zerobase.leisure.domain.repository.order.LeisureOrderItemRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureCartService {

	private final LeisureRepository leisureRepository;
	private final LeisureCartRepository leisureCartRepository;
	private final LeisureOrderItemRepository leisureOrderItemRepository;
	private final LeisureCartCheck leisureCartCheck;

	@Transactional
	public void addLeisureCart(Long customerId, AddLeisureCartForm form) {
		if (leisureOrderItemRepository.findByLeisureCart_CustomerIdAndLeisureId(customerId,
			form.getLeisureId()).isPresent()) {
			throw new LeisureException(ErrorCode.ALREADY_IN_CART);
		}
		Leisure leisure = leisureRepository.findById(form.getLeisureId())
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

		if (leisure.getMinPerson() > form.getPersons()
			|| form.getPersons() > leisure.getMaxPerson()) {
			throw new LeisureException(ErrorCode.TOO_MANY_PERSON);
		}

		Optional<LeisureCart> optionalLeisureCart = leisureCartRepository.findByCustomerId(
			customerId);

		LeisureCart leisureCart;
		if (optionalLeisureCart.isPresent()) {
			leisureCart = optionalLeisureCart.get();
			leisureCart.setTotalPrice(leisureCart.getTotalPrice() + leisure.getPrice());
			leisureCartRepository.save(leisureCart);
		} else {
			leisureCart = leisureCartRepository.save(LeisureCart.builder()
				.customerId(customerId)
				.totalPrice(leisure.getPrice())
				.build());
		}
		leisureOrderItemRepository.save(
			LeisureOrderItem.of(leisure.getSellerId(), leisure.getPrice(), leisureCart, form));
	}

	@Transactional
	public void deleteLeisureCart(Long customerId, Long leisureOrderItemId) {
		LeisureCart leisureCart = leisureCartRepository.findByCustomerId(customerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_CART));
		leisureCart.setTotalPrice(leisureCart.getTotalPrice() - leisureOrderItemRepository.findById(
				leisureOrderItemId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_ORDER_ITEM))
			.getPrice());

		leisureOrderItemRepository.deleteByIdAndLeisureCart_CustomerId(leisureOrderItemId,
			customerId);
	}

	@Transactional
	public List<LeisureOrderItemDto> getLeisureCart(Long customerId) {

		LeisureCart leisureCart = leisureCartRepository.findByCustomerId(customerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_CART));

		List<LeisureOrderItem> leisureOrderItemList = leisureOrderItemRepository
			.findAllByLeisureCart_CustomerId(customerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_HAD_ORDER_ITEM));

		for (LeisureOrderItem i : leisureOrderItemList) {
			leisureCartCheck.cartCheck(leisureCart, i);
		}

		leisureOrderItemList = leisureOrderItemRepository.findAllByLeisureCart_CustomerId(customerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_HAD_ORDER_ITEM));

		List<Leisure> leisureList = leisureRepository.findAllById(leisureIds(leisureOrderItemList));

		List<LeisureOrderItemDto> list = new ArrayList<>();
		for (int i=0; i<leisureList.size(); i++) {
			list.add(LeisureOrderItemDto.from(leisureOrderItemList.get(i),leisureList.get(i)));
		}

		return list;
	}

	private List<Long> leisureIds(List<LeisureOrderItem> leisureOrderItemList) {
		List<Long> list = new ArrayList<>();
		for (LeisureOrderItem i : leisureOrderItemList) {
			list.add(i.getLeisureId());
		}
		return list;
	}
}
