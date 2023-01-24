package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDto {
	private Long id;
	private Long sellerId;

	private String name;
	private String addr;
	private Integer price;

	private Integer minPerson;
	private Integer maxPerson;

	private String checkIn;
	private String checkOut;

	private Category category;

	private double lat;
	private double lon;

	public static AccommodationDto from(Accommodation accommodation) {
		return AccommodationDto.builder()
			.id(accommodation.getId())
			.sellerId(accommodation.getSellerId())
			.name(accommodation.getAccommodationName())
			.addr(accommodation.getAddr())
			.price(accommodation.getPrice())
			.maxPerson(accommodation.getMaxPerson())
			.minPerson(accommodation.getMinPerson())
			.checkIn(accommodation.getCheckIn())
			.checkOut(accommodation.getCheckOut())
			.category(accommodation.getCategory())
			.lat(accommodation.getLat())
			.lon(accommodation.getLon())
			.build();
	}
}
