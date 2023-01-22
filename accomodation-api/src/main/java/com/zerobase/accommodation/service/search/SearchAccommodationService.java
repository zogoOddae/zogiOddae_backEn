package com.zerobase.accommodation.service.search;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationListDto;
import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import com.zerobase.accommodation.domain.form.search.SearchAccommodationForm;
import com.zerobase.accommodation.domain.repository.accommodation.AccommodationRepository;
import com.zerobase.accommodation.domain.repository.search.AccommodationReseravtionDayRepository;
import com.zerobase.accommodation.domain.type.ErrorCode;
import com.zerobase.accommodation.exception.AccommodationException;
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
public class SearchAccommodationService {

    private final AccommodationReseravtionDayRepository accommodationReseravtionDayRepository;
    private final AccommodationRepository accommodationRepository;

    public Page<AccommodationListDto> getAllSearchResult(SearchAccommodationForm form, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 15);

        //예약일 정보가 있는 id들만 가져오기
        List<Long> accommodationIds
            = accommodationReseravtionDayRepository.findAllaccommodationId(form.getStartAt(),
            form.getEndAt());
        List<Accommodation> list;

        String adrr = "%"+form.getAddr()+"%";

        if (accommodationIds.size() > 0) {
            list = new ArrayList<>();
            //예약일 정보가 없고, 주소 값에 들어가는 숙박 정보들만 가져오기
            list.add(
                accommodationRepository.findAllByAddr(
                    adrr, form.getPersonnel(), form.getPersonnel(), accommodationIds).orElseThrow(
                    () -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION)));

        } else {
            list = accommodationRepository.findAllByAddrLikeAndMinPersonLessThanEqualAndMaxPersonGreaterThanEqual(
                adrr, form.getPersonnel(),
                form.getPersonnel());
        }

        List<AccommodationListDto> dtoList = new ArrayList<>();

        for (Accommodation accommodation : list) {
            dtoList.add(AccommodationListDto.from(accommodation));
        }

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), dtoList.size());

        return new PageImpl<>(dtoList.subList(start, end), pageRequest, dtoList.size());
    }

    public Page<AccommodationListDto> getAllSearchAddr(String addr, Pageable pageable) {
        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15);

        String s_addr = "%"+addr+"%";

        Page<Accommodation> accommodationList = accommodationRepository.findAllByAddrContaining(addr, limit).orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

        List<AccommodationListDto> dtoList = new ArrayList<>();

        for (Accommodation accommodation : accommodationList) {
            dtoList.add(AccommodationListDto.from(accommodation));
        }
        return new PageImpl<>(dtoList, limit, accommodationList.getTotalElements());
    }

    public Page<AccommodationListDto> getAllSearchCategory(String category, Pageable pageable) {
        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15);

        Page<Accommodation> accommodationList = accommodationRepository.findAllByCategoryContaining(category, limit).orElseThrow(() -> new AccommodationException(ErrorCode.NOT_FOUND_ACCOMMODATION));

        List<AccommodationListDto> dtoList = new ArrayList<>();

        for (Accommodation accommodation : accommodationList) {
            dtoList.add(AccommodationListDto.from(accommodation));
        }
        return new PageImpl<>(dtoList, limit, accommodationList.getTotalElements());
    }
}
