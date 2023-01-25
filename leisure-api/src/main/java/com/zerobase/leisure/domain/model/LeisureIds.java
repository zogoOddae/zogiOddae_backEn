package com.zerobase.leisure.domain.model;

import java.util.ArrayList;
import java.util.List;

public interface LeisureIds {
	Long getLeisureId();

	static List<Long> leisureIds(List<LeisureIds> leisureIds) {
		List<Long> ids = new ArrayList<>();
		for (LeisureIds i : leisureIds) {
 			ids.add(i.getLeisureId());
		}
		return ids;
	}
}
