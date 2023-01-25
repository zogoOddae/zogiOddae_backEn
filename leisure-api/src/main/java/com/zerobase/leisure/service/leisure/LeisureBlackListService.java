package com.zerobase.leisure.service.leisure;

import com.zerobase.leisure.domain.dto.leisure.LeisureBlackListDto;
import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.leisure.LeisureBlackList;
import com.zerobase.leisure.domain.form.AddLeisureBlackListForm;
import com.zerobase.leisure.domain.repository.common.LeisureBlackListRepository;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureBlackListService {

    private final LeisureBlackListRepository leisureBlackListRepository;
    private final LeisureRepository leisureRepository;

    public LeisureBlackList addLeisureBlackList(AddLeisureBlackListForm form) {
        Optional<LeisureBlackList> leisureBlackListOptional =
            leisureBlackListRepository.findByCustomerIdAndLeisureId(form.getCustomerId(),
                form.getProductId());
        if (leisureBlackListOptional.isPresent()) {
            throw new LeisureException(ErrorCode.ALREADY_DENIED_USER);
        }

        LeisureBlackList leisureBlackList = LeisureBlackList.builder()
            .leisureId(form.getProductId())
            .customerId(form.getCustomerId())
            .description(form.getDescription())
            .build();

        leisureBlackListRepository.save(leisureBlackList);
        return leisureBlackList;
    }

    public void deleteLeisureBlackList(Long leisureBackListId) {
        LeisureBlackList leisureBlackList = leisureBlackListRepository.findById(leisureBackListId)
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_USER));

        leisureRepository.findById(leisureBlackList.getLeisureId())
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

        leisureBlackListRepository.deleteById(leisureBackListId);
    }

    public Page<LeisureBlackListDto> getLeisureBlackList(Long leisureId, Pageable pageable) {
        leisureRepository.findById(leisureId).orElseThrow(()->new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

        Pageable limit = PageRequest.of(pageable.getPageNumber(), 15, Sort.by("customerId"));

        Page<LeisureBlackList> leisureBlackLists = leisureBlackListRepository.findAllByLeisureId(leisureId,limit);
        List<LeisureBlackListDto> leisureBlackListDtoList = new ArrayList<>();

        for(LeisureBlackList accommodationBlackList : leisureBlackLists ){
            leisureBlackListDtoList.add(LeisureBlackListDto.from(accommodationBlackList));
        }
        return new PageImpl<>(leisureBlackListDtoList, limit, leisureBlackLists.getTotalElements());
    }
}
