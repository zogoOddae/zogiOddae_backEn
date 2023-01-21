package com.zerobase.leisure.service.order;

import com.zerobase.leisure.domain.dto.leisure.LeisureOrderItemDto;
import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.order.LeisureCart;
import com.zerobase.leisure.domain.entity.order.LeisureOrder;
import com.zerobase.leisure.domain.entity.order.LeisureOrderItem;
import com.zerobase.leisure.domain.form.LeisureOrderForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.repository.order.LeisureCartRepository;
import com.zerobase.leisure.domain.repository.order.LeisureOrderItemRepository;
import com.zerobase.leisure.domain.repository.order.LeisureOrderRepository;
import com.zerobase.leisure.domain.repository.order.LeisurePaymentRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.domain.type.OrderStatus;
import com.zerobase.leisure.domain.type.PaymentStatus;
import com.zerobase.leisure.exception.LeisureException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureOrderService {

	private final LeisureCartRepository leisureCartRepository;
	private final LeisureOrderItemRepository leisureOrderItemRepository;
	private final LeisureOrderRepository leisureOrderRepository;
	private final LeisurePaymentRepository leisurePaymentRepository;
	private final LeisureRepository leisureRepository;

	@Transactional
	public void LeisureOrder(Long customerId, Long leisurePaymentId) {
		if (leisureOrderRepository.findByCustomerIdAndLeisurePaymentId(customerId, leisurePaymentId)
			.isPresent()) {
			throw new LeisureException(ErrorCode.ALREADY_ORDERED_PAYMENT);
		}
//		if (leisurePaymentRepository.findById(leisurePaymentId)
//			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_PAYMENT))
//			.getStatus() != PaymentStatus.PAID) {
//			throw new LeisureException(ErrorCode.NOT_PAYMENT_ORDER);
//		}

		LeisureOrder leisureOrder = leisureOrderRepository.save(LeisureOrder.builder()
			.customerId(customerId)
			.leisurePaymentId(leisurePaymentId)
			.totalPrice(leisurePaymentRepository.findById(leisurePaymentId)
				.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_PAYMENT))
				.getPrice())
			.orderStatus(OrderStatus.PAYMENT)
			.build());

		System.out.println(customerId);
		LeisureCart leisureCart = leisureCartRepository.findByCustomerId(customerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_CART));

		List<LeisureOrderItem> leisureOrderItemList = leisureOrderItemRepository
			.findAllByLeisureCart_CustomerId(customerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_ORDER_ITEM));

		for (LeisureOrderItem leisureOrderItem : leisureOrderItemList) {
			leisureOrderItem.setLeisureCart(null);
			leisureOrderItem.setReservationId(
				UUID.randomUUID().toString().replace("-", "").substring(0,15));
			leisureOrderItem.setLeisureOrderId(leisureOrder.getId());

			leisureOrderItemRepository.save(leisureOrderItem);
		}

		leisureCart.setTotalPrice(0);
		leisureCartRepository.save(leisureCart);
	}

	public Page<LeisureOrderItemDto> getLeisureOrder(Long customerId, Pageable pageable) {
		Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("id"));

		List<Long> leisureOrderIds = ids(leisureOrderRepository.findByCustomerId(customerId));

		Page<LeisureOrderItem> leisureOrderItems = leisureOrderItemRepository.findAllByLeisureOrderIdIn(leisureOrderIds,limit);

		List<LeisureOrderItemDto> leisureOrderItemDtoList = new ArrayList<>();

		List<Long> leisureIds = leisureIds(leisureOrderItems.toList());

		for (int i=0; i<leisureOrderItems.toList().size(); i++) {
			leisureOrderItemDtoList.add(LeisureOrderItemDto.from(leisureOrderItems.toList().get(i),
				leisureRepository.findById(leisureIds.get(i)).orElseThrow(()->new LeisureException(ErrorCode.NOT_FOUND_LEISURE))));
		}

		return new PageImpl<>(leisureOrderItemDtoList, limit, leisureOrderItems.getTotalElements());
	}

	private List<Long> ids(List<LeisureOrder> leisureOrderList) {
		List<Long> list = new ArrayList<>();
		for (LeisureOrder leisureOrder : leisureOrderList) {
			list.add(leisureOrder.getId());
		}
		return list;
	}
	private List<Long> leisureIds(List<LeisureOrderItem> leisureOrderList) {
		List<Long> list = new ArrayList<>();
		for (LeisureOrderItem leisureOrder : leisureOrderList) {
			list.add(leisureOrder.getLeisureId());
		}
		return list;
	}
}
