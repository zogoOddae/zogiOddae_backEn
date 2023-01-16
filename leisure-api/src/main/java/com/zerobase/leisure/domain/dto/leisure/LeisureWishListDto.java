package com.zerobase.leisure.domain.dto.leisure;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.leisure.LeisureWishList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeisureWishListDto {
	private Long leisureWishListId;
	private Long leisureId;

	private Long memberId;
	private String leisureName;
	private String addr;
	private Integer price;
	private String pictureUrl;

	public static LeisureWishListDto from(LeisureWishList LeisureWishList, Leisure leisure) {
		return LeisureWishListDto.builder()
			.leisureWishListId(LeisureWishList.getId())
			.leisureId(leisure.getId())
			.memberId(LeisureWishList.getMemberId())
			.leisureName(leisure.getLeisureName())
			.addr(leisure.getAddr())
			.price(leisure.getPrice())
			.pictureUrl(leisure.getPictureUrl())
			.build();
	}
}
