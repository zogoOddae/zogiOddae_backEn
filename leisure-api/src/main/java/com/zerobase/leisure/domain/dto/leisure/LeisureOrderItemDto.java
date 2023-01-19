package com.zerobase.leisure.domain.dto.leisure;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.order.LeisureOrderItem;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisureOrderItemDto {
	private Long id;
	private Long sellerId;

	private String leisureName;
	private String addr;
	private Integer price;

	private String pictureUrl;

	private Integer persons;

	private LocalDateTime startAt;
	private LocalDateTime endAt;

	private double lat;
	private double lon;

	public static LeisureOrderItemDto from(LeisureOrderItem leisureOrderItem, Leisure leisure) {
		return LeisureOrderItemDto.builder()
			.id(leisureOrderItem.getId())
			.sellerId(leisure.getSellerId())
			.leisureName(leisure.getLeisureName())
			.addr(leisure.getAddr())
			.price(leisure.getPrice())
			.persons(leisureOrderItem.getPersons())
			.startAt(leisureOrderItem.getStartAt())
			.endAt(leisureOrderItem.getEndAt())
			.lat(leisure.getLat())
			.lon(leisure.getLon())
			.build();
	}
}
