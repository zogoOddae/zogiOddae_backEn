package com.zerobase.leisure.domain.dto.leisure;

import com.zerobase.leisure.domain.entity.leisure.LeisureBlackList;
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
public class LeisureBlackListDto {

    private Long leisureBlackListId;
    private Long leisureId;

    private Long customerId;
    private String description;

    public static LeisureBlackListDto from(LeisureBlackList leisureBlackList) {
        return LeisureBlackListDto.builder()
            .leisureBlackListId(leisureBlackList.getId())
            .leisureId(leisureBlackList.getLeisureId())
            .customerId(leisureBlackList.getCustomerId())
            .description(leisureBlackList.getDescription())
            .build();
    }

    public static List<LeisureBlackListDto> fromList(List<LeisureBlackList> leisureBlackList) {
        return leisureBlackList.stream()
            .map(LeisureBlackListDto::from)
            .collect(Collectors.toList());
    }
}
