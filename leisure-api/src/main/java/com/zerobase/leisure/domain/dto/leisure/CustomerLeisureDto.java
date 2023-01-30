package com.zerobase.leisure.domain.dto.leisure;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLeisureDto {
	private Long id;
	private Long sellerId;

	private String name;
	private String addr;
	private Integer price;
	private String description;

	private String pictureUrl;

	private Integer minPerson;
	private Integer maxPerson;

	private String startAt;
	private String endAt;

	private double lat;
	private double lon;

	public static CustomerLeisureDto from(Leisure leisure, String startAt, String endAt) {
		return CustomerLeisureDto.builder()
			.id(leisure.getId())
			.sellerId(leisure.getSellerId())
			.name(leisure.getLeisureName())
			.addr(leisure.getAddr())
			.pictureUrl(leisure.getPictureUrl())
			.price(leisure.getPrice())
			.maxPerson(leisure.getMaxPerson())
			.minPerson(leisure.getMinPerson())
			.description(leisure.getDescription())
			.startAt(startAt)
			.endAt(endAt)
			.lat(leisure.getLat())
			.lon(leisure.getLon())
			.build();
	}
}
