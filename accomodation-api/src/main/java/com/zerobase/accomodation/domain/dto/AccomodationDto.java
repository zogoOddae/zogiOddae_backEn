package com.zerobase.accomodation.domain.dto;

import com.zerobase.accomodation.domain.entity.leisure.Accomodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationDto {
	private Long id;
	private Long sellerId;

	private String name;
	private String addr;
	private Integer price;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;

	public static AccomodationDto from(Accomodation accomodation) {
		return AccomodationDto.builder()
			.id(accomodation.getId())
			.sellerId(accomodation.getSellerId())
			.name(accomodation.getName())
			.addr(accomodation.getAddr())
			.price(accomodation.getPrice())
			.maxPerson(accomodation.getMaxPerson())
			.minPerson(accomodation.getMinPerson())
			.lat(accomodation.getLat())
			.lon(accomodation.getLon())
			.build();
	}
}
