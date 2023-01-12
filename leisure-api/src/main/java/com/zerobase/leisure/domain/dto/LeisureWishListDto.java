package com.zerobase.leisure.domain.dto;

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
public class LeisureWishListDto {
	private Long id;
	private Long memberId;
	private List<LeisureDto> wishList = new ArrayList<>();
}
