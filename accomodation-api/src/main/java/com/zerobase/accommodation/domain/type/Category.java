package com.zerobase.accommodation.domain.type;

import com.zerobase.accommodation.exception.AccommodationException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Category {
	MOUNTAIN("MOUNTAIN"),
	SEA("SEA"),
	VALLEY("VALLEY"),
	HOTEL("HOTEL");

	private final String desc;

	Category(String desc) {
		this.desc = desc;
	}

	public static Category from(String desc){
		return Arrays.stream(Category.values())
			.filter(v -> v.getDesc().equals(desc))
			.findAny().orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_CATEGORY));
	}
}
