package com.zerobase.accomodation.domain.dto.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationListDto {
	private Long id;
	private Long sellerId;

	private String accomodationName;
	private String addr;
	private Integer price;

	public static AccomodationListDto from(Accomodation accomodation) {
		return AccomodationListDto.builder()
			.id(accomodation.getId())
			.sellerId(accomodation.getSellerId())
			.accomodationName(accomodation.getAccomodationName())
			.addr(accomodation.getAddr())
			.price(accomodation.getPrice())
			.build();
	}
}
