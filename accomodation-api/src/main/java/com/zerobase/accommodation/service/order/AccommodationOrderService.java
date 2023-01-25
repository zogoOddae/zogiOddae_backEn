package com.zerobase.accommodation.service.order;

import com.zerobase.accommodation.domain.dto.order.AccommodationOrderCompleteDto;
import com.zerobase.accommodation.domain.dto.order.AccommodationOrderDto;
import com.zerobase.accommodation.domain.dto.order.AccommodationOrderItemListDto;
import com.zerobase.accommodation.domain.dto.order.AccommodationOrderListDto;
import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.entity.accommodation.AccommodationReservationDay;
import com.zerobase.accommodation.domain.entity.coupon.AccommodationCoupon;
import com.zerobase.accommodation.domain.entity.order.AccommodationCart;
import com.zerobase.accommodation.domain.entity.order.AccommodationOrder;
import com.zerobase.accommodation.domain.entity.order.AccommodationOrderItem;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import com.zerobase.accommodation.domain.repository.coupon.AccommodationCouponRepository;
import com.zerobase.accommodation.domain.repository.order.AccommodationCartRepository;
import com.zerobase.accommodation.domain.repository.order.AccommodationOrderItemRepository;
import com.zerobase.accommodation.domain.repository.order.AccommodationOrderRepository;
import com.zerobase.accommodation.domain.repository.payment.AccommodationPaymentRepository;
import com.zerobase.accommodation.domain.repository.search.AccommodationReservationDayRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.domain.type.OrderStatus;
import com.zerobase.accommodation.exception.AccommodationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class AccommodationOrderService {

    private final AccommodationCartRepository accommodationCartRepository;
    private final AccommodationOrderItemRepository accommodationOrderItemRepository;
    private final AccommodationOrderRepository accommodationOrderRepository;
    private final AccommodationPaymentRepository accommodationPaymentRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationCouponRepository accommodationCouponRepository;

    private final AccommodationReservationDayRepository accommodationReservationDayRepository;

    @Transactional
    public AccommodationOrderCompleteDto accommodationOrder(Long customerId,
        Long accommodationPaymentId) {
        if (accommodationOrderRepository.findByCustomerIdAndAccommodationPaymentId(customerId,
                accommodationPaymentId)
            .isPresent()) {
            throw new AccommodationException(ErrorCode.ALREADY_ORDERED_PAYMENT);
        }
//		if (leisurePaymentRepository.findById(leisurePaymentId)
//			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_PAYMENT))
//			.getStatus() != PaymentStatus.PAID) {
//			throw new LeisureException(ErrorCode.NOT_PAYMENT_ORDER);
//		}
        AccommodationOrder accommodationOrder = accommodationOrderRepository.save(
            AccommodationOrder.builder()
                .customerId(customerId)
                .accommodationPaymentId(accommodationPaymentId)
                .totalPrice(accommodationPaymentRepository.findById(accommodationPaymentId)
                    .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_PAYMENT))
                    .getPrice())
                .orderStatus(OrderStatus.PAYMENT)
                .reservationId(UUID.randomUUID().toString().replace("-", "").substring(0, 15))
                .build());

        AccommodationCart accommodationCart = accommodationCartRepository.findByCustomerId(
                customerId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_CART));

        List<AccommodationOrderItem> accommodationOrderItemList = accommodationOrderItemRepository
            .findAllByAccommodationCart_CustomerId(customerId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ORDER_ITEM));

        for (AccommodationOrderItem accommodationOrderItem : accommodationOrderItemList) {
            accommodationOrderItem.setAccommodationCart(null);
            accommodationOrderItem.setAccommodationOrderId(accommodationOrder.getId());
            if (accommodationOrderItem.getCouponId() != null) {
                AccommodationCoupon accommodationCoupon = accommodationCouponRepository.findById(
                        accommodationOrderItem.getCouponId())
                    .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_COUPON));
                accommodationCoupon.setUsedYN(true);
                accommodationCoupon.setUsedTime(LocalDateTime.now());
                accommodationCouponRepository.save(accommodationCoupon);
            }

            accommodationReservationDayRepository.save(AccommodationReservationDay.builder()
                .accommodationId(accommodationOrderItem.getAccommodationId())
                .reservationYear(accommodationOrderItem.getStartAt()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                    .substring(0, 4))
                .startAt(accommodationOrderItem.getStartAt())
                .endAt(accommodationOrderItem.getEndAt())
                .build());
            accommodationOrderItemRepository.save(accommodationOrderItem);
        }

        accommodationCart.setTotalPrice(0);
        accommodationCartRepository.save(accommodationCart);

        return AccommodationOrderCompleteDto.builder()
            .reservationId(accommodationOrder.getReservationId())
            .price(accommodationOrder.getTotalPrice())
            .build();
    }

    public Page<AccommodationOrderListDto> getAccommodationOrder(Long customerId,
        Pageable pageable) {
        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("id"));

        Page<AccommodationOrder> accommodationOrderItems = accommodationOrderRepository.findByCustomerId(
            customerId, limit);

        List<AccommodationOrderListDto> accommodationOrderItemDtoList = new ArrayList<>();
        for (int i = 0; i < accommodationOrderItems.toList().size(); i++) {
            AccommodationOrder accommodationOrder = accommodationOrderItems.toList().get(i);
            List<AccommodationOrderItem> accommodationOrderItemList = accommodationOrderItemRepository.findByAccommodationOrderId(
                    accommodationOrder.getId())
                .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ORDER_ITEM));

            accommodationOrderItemDtoList.add(AccommodationOrderListDto.from(accommodationOrder,
                accommodationOrderItemList.get(0), accommodationRepository.findById(
                    accommodationOrderItemList.get(0).getAccommodationId()).get(),
                accommodationOrderItemList.size()));
        }
        return new PageImpl<>(accommodationOrderItemDtoList, limit,
            accommodationOrderItems.getTotalElements());
    }

    public AccommodationOrderDto getAccommodationOrderDetail(Long orderId) {

        AccommodationOrder accommodationOrder = accommodationOrderRepository.findById(orderId)
            .orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ORDER));

        List<AccommodationOrderItem> accommodationOrderItems = accommodationOrderItemRepository.findAllByAccommodationOrderId(
            orderId);

        List<AccommodationOrderItemListDto> accommodationOrderItemDtoList = new ArrayList<>();

        List<Long> accommodationIds = accommodationIds(accommodationOrderItems);

        List<Accommodation> accommodationList = accommodationRepository.findAllById(accommodationIds);

        for (int i = 0; i < accommodationOrderItems.size(); i++) {
            int index = whereAccommodation(accommodationList, accommodationIds.get(i));
            if (index == -1) {
                continue;
            }

            accommodationOrderItemDtoList.add(AccommodationOrderItemListDto.from(accommodationOrderItems.get(i),
                accommodationList.get(index)));
        }

        return AccommodationOrderDto.from(accommodationOrder, accommodationOrderItemDtoList);
    }


    private List<Long> ids(List<AccommodationOrder> accommodationOrderList) {
        List<Long> list = new ArrayList<>();
        for (AccommodationOrder accommodationOrder : accommodationOrderList) {
            list.add(accommodationOrder.getId());
        }
        return list;
    }

    private int whereAccommodation(List<Accommodation> accommodationList, Long id) {
        for (int i = 0; i < accommodationList.size(); i++) {
            if (accommodationList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private List<Long> accommodationIds(List<AccommodationOrderItem> accommodationOrderList) {
        List<Long> list = new ArrayList<>();
        for (AccommodationOrderItem accommodationOrder : accommodationOrderList) {
            list.add(accommodationOrder.getAccommodationId());
        }
        return list;
    }


}
