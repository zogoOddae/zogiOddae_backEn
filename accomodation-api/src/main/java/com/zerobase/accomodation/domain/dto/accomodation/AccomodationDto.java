package com.zerobase.accomodation.domain.dto.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import java.util.ArrayList;
import java.util.List;
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

	private String accomodationName;
	private String addr;
	private Integer price;

	private List<Long> AccomodationBlackList = new ArrayList<>();

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;

	public static AccomodationDto from(Accomodation accomodation) {
		return AccomodationDto.builder()
			.id(accomodation.getId())
			.sellerId(accomodation.getSellerId())
			.accomodationName(accomodation.getAccomodationName())
			.AccomodationBlackList(accomodation.getAccomodationBlackList())
			.addr(accomodation.getAddr())
			.price(accomodation.getPrice())
			.maxPerson(accomodation.getMaxPerson())
			.minPerson(accomodation.getMinPerson())
			.lat(accomodation.getLat())
			.lon(accomodation.getLon())
			.build();
	}
}
