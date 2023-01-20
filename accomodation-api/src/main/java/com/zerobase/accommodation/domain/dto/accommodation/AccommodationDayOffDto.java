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
    private Long id;
    private Long accommodationId;

    private String year;
    private LocalDate startDate;
    private LocalDate endDate;

    public static AccommodationDayOffDto from(AccommodationDayOff accommodationDayOff) {
        return AccommodationDayOffDto.builder()
            .id(accommodationDayOff.getId())
            .accommodationId(accommodationDayOff.getAccommodationId())
            .year(accommodationDayOff.getDayOffYear())
            .startDate(accommodationDayOff.getStartDate())
            .endDate(accommodationDayOff.getEndDate())
            .build();
    }

    public static List<AccommodationDayOffDto> fromList(List<AccommodationDayOff> accommodationDayOff) {
        return accommodationDayOff.stream()
            .map(AccommodationDayOffDto::from)
            .collect(Collectors.toList());
    }
}
