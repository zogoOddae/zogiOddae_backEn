package com.zerobase.accommodation.domain.dto.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationDayOff;
import java.time.LocalDate;
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

public class AccommodationDayOffDto {
    private Long dayOffId;
    private Long id;

    private String year;
    private LocalDate startAt;
    private LocalDate endAt;

    public static AccommodationDayOffDto from(AccommodationDayOff accommodationDayOff) {
        return AccommodationDayOffDto.builder()
            .dayOffId(accommodationDayOff.getId())
            .id(accommodationDayOff.getAccommodationId())
            .year(accommodationDayOff.getDayOffYear())
            .startAt(accommodationDayOff.getStartAt())
            .endAt(accommodationDayOff.getEndAt())
            .build();
    }

    public static List<AccommodationDayOffDto> fromList(List<AccommodationDayOff> accommodationDayOff) {
        return accommodationDayOff.stream()
            .map(AccommodationDayOffDto::from)
            .collect(Collectors.toList());
    }
}
