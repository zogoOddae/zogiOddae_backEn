package com.zerobase.leisure.domain.dto.leisure;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.order.LeisureOrderItem;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisureOrderItemDto {
	private Long leisureOrderItemId;
	private Long sellerId;

	private String leisureName;
	private String addr;
	private Integer price;

	private String pictureUrl;

	private Integer persons;

	private LocalDateTime startAt;
	private LocalDateTime endAt;

	public static LeisureOrderItemDto from(LeisureOrderItem leisureOrderItem, Leisure leisure) {
		return LeisureOrderItemDto.builder()
			.leisureOrderItemId(leisureOrderItem.getId())
			.sellerId(leisure.getSellerId())
			.leisureName(leisure.getLeisureName())
			.addr(leisure.getAddr())
			.price(leisure.getPrice())
			.persons(leisureOrderItem.getPersons())
			.startAt(leisureOrderItem.getStartAt())
			.endAt(leisureOrderItem.getEndAt())
			.build();
	}
}
