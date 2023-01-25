package com.zerobase.leisure.service.search;

import com.zerobase.leisure.domain.dto.leisure.LeisureListDto;
import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.form.SearchLeisureForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.repository.leisure.LeisureReservationDayRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchLeisureService {

	private final LeisureReservationDayRepository leisureReservationDayRepository;
	private final LeisureRepository leisureRepository;

	public Page<LeisureListDto> getAllSearchResult(SearchLeisureForm form, Pageable pageable) {
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 15);

		//예약일 정보가 있는 id들만 가져오기
		List<Long> leisureIds
			= leisureReservationDayRepository.findAllLeisureId(form.getStartAt(),
			form.getEndAt());
		List<Leisure> list;

		String adrr = "%"+form.getAddr()+"%";

		if (leisureIds.size() > 0) {
			list = new ArrayList<>();
			//예약일 정보가 없고, 주소 값에 들어가는 숙박 정보들만 가져오기
			list.add(
				leisureRepository.findAllByAddr(
					adrr, form.getPersonnel(), form.getPersonnel(), leisureIds).orElseThrow(
					() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE)));

		} else {
			list = leisureRepository.findAllByAddrLikeAndMinPersonLessThanEqualAndMaxPersonGreaterThanEqual(
				adrr, form.getPersonnel(),
				form.getPersonnel());
		}

		List<LeisureListDto> dtoList = new ArrayList<>();

		for (Leisure leisure : list) {
			dtoList.add(LeisureListDto.from(leisure));
		}

		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), dtoList.size());

		return new PageImpl<>(dtoList.subList(start, end), pageRequest, dtoList.size());
	}

	public Page<LeisureListDto> getAllSearchAddr(String addr, Pageable pageable) {
		Pageable limit = PageRequest.of(pageable.getPageNumber(), 15);

		String s_addr = "%"+addr+"%";

		Page<Leisure> accommodationList = leisureRepository.findAllByAddrContaining(addr, limit)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

		List<LeisureListDto> dtoList = new ArrayList<>();

		for (Leisure leisure : accommodationList) {
			dtoList.add(LeisureListDto.from(leisure));
		}
		return new PageImpl<>(dtoList, limit, accommodationList.getTotalElements());
	}

	public Page<LeisureListDto> getAllSearchCategory(String category, Pageable pageable) {
		Pageable limit = PageRequest.of(pageable.getPageNumber(), 15);

		Page<Leisure> accommodationList = leisureRepository.findAllByCategoryContaining(category, limit)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

		List<LeisureListDto> dtoList = new ArrayList<>();

		for (Leisure leisure : accommodationList) {
			dtoList.add(LeisureListDto.from(leisure));
		}
		return new PageImpl<>(dtoList, limit, accommodationList.getTotalElements());
	}
}
