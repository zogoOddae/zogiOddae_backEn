package com.zerobase.accomodation.service.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import com.zerobase.accomodation.domain.entity.common.AccomodationBlackList;
import com.zerobase.accomodation.domain.form.AddAccomodationBlackListForm;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationBlackListRepository;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccomodationBlackListService {

    private final AccomodationBlackListRepository accomodationBlackListRepository;
    private final AccomodationRepository accomodationRepository;

    public AccomodationBlackList addAccomodationBlackList(AddAccomodationBlackListForm form) {
        Optional<AccomodationBlackList> accomodationBlackListOptional =
            accomodationBlackListRepository.findByCustomerIdAndAccomodationId(form.getCustomerId(),
                form.getAccomodationId());
        if (accomodationBlackListOptional.isPresent()) {
            throw new RuntimeException("이미 등록된 회원입니다.");
        }

        //숙박 정보 가져오기
        Accomodation accomodation =
        accomodationRepository.findById(form.getAccomodationId()).orElseThrow(() -> new RuntimeException("등록된 숙박 시설이 아닙니다."));

        if(accomodation.getAccomodationBlackList().contains(form.getCustomerId())){
            throw new RuntimeException("이미 등록된 회원입니다.");
        }

        AccomodationBlackList accomodationBlackList = AccomodationBlackList.builder()
            .accomodationId(form.getAccomodationId())
            .customerId(form.getCustomerId())
            .description(form.getDescription())
            .build();

        accomodation.getAccomodationBlackList().add(accomodationBlackList.getCustomerId());

        accomodationBlackListRepository.save(accomodationBlackList);
        accomodationRepository.save(accomodation);
        return accomodationBlackList;
    }

    public void deleteAccomodationBlackList(Long accomodationBlackListId) {
        AccomodationBlackList accomodationBlackList = accomodationBlackListRepository.findById(accomodationBlackListId)
            .orElseThrow(() -> new RuntimeException("이미 삭제된 블랙리스트 회원입니다."));

        Accomodation accomodation = accomodationRepository.findById(accomodationBlackList.getAccomodationId())
            .orElseThrow(() -> new RuntimeException("존재하지 않는 숙박시설 입니다."));

        accomodation.getAccomodationBlackList().remove(accomodationBlackList.getCustomerId());
        accomodationRepository.save(accomodation);

        accomodationBlackListRepository.deleteById(accomodationBlackListId);
    }
}
