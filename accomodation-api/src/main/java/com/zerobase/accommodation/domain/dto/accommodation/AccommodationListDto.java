package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
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
public class AccommodationListDto {
	private Long id;
	private Long sellerId;

	private String name;
	private String addr;
	private Integer price;

	private String pictureUrl;

	public static AccommodationListDto from(Accommodation accommodation) {
		return AccommodationListDto.builder()
			.id(accommodation.getId())
			.sellerId(accommodation.getSellerId())
			.name(accommodation.getAccommodationName())
			.addr(accommodation.getAddr())
			.price(accommodation.getPrice())
			.pictureUrl(accommodation.getPictureUrl())
			.build();
	}

	public static List<AccommodationListDto> fromList(List<Accommodation> accommodationList) {
		return accommodationList.stream()
			.map(AccommodationListDto::from)
			.collect(Collectors.toList());
	}
}
