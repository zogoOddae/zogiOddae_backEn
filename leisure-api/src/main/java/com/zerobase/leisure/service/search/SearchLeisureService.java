package com.zerobase.leisure.service.search;

import com.zerobase.leisure.domain.dto.leisure.LeisureListDto;
import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.form.SearchLeisureForm;
import com.zerobase.leisure.domain.model.LeisureIds;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.repository.leisure.LeisureReservationDayRepository;
import com.zerobase.leisure.domain.type.Category;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.time.LocalDateTime;
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
		String[] stList = form.getStartAt().toString().split("-");
		String[] endList = form.getEndAt().toString().split("-");
		List<Long> leisureIds
			= LeisureIds.leisureIds(leisureReservationDayRepository.findAllLeisureId(
			LocalDateTime.of(Integer.parseInt(stList[0]),Integer.parseInt(stList[1]),Integer.parseInt(stList[2]),0,0),
			LocalDateTime.of(Integer.parseInt(endList[0]),Integer.parseInt(endList[1]),Integer.parseInt(endList[2]),0,0)) );
		List<Leisure> list;

		String adrr = "%"+form.getAddr()+"%";

		if (leisureIds.isEmpty()) {
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

		Page<Leisure> accommodationList = leisureRepository.findAllByCategory(
				Category.valueOf(category), limit)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

		List<LeisureListDto> dtoList = new ArrayList<>();

		for (Leisure leisure : accommodationList) {
			dtoList.add(LeisureListDto.from(leisure));
		}
		return new PageImpl<>(dtoList, limit, accommodationList.getTotalElements());
	}
}
