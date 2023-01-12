package com.zerobase.accomodation.service.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import com.zerobase.accomodation.domain.entity.accomodation.AccomodationBlackList;
import com.zerobase.accomodation.domain.form.AddAccomodationBlackListForm;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationBlackListRepository;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationRepository;
import com.zerobase.accomodation.domain.type.ErrorCode;
import com.zerobase.accomodation.exception.AccomodationException;
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
            throw new AccomodationException(ErrorCode.ALREADY_REGISTERED_BLACKLIST);
        }

        //숙박 정보 가져오기
        Accomodation accomodation =
        accomodationRepository.findById(form.getAccomodationId()).orElseThrow(() -> new AccomodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

        if(accomodation.getAccomodationBlackList().contains(form.getCustomerId())){
            throw new AccomodationException(ErrorCode.ALREADY_REGISTERED_BLACKLIST);
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
            .orElseThrow(() -> new AccomodationException(ErrorCode.ALREADY_DELETED_BLACKLIST));

        Accomodation accomodation = accomodationRepository.findById(accomodationBlackList.getAccomodationId())
            .orElseThrow(() -> new AccomodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

        accomodation.getAccomodationBlackList().remove(accomodationBlackList.getCustomerId());
        accomodationRepository.save(accomodation);

        accomodationBlackListRepository.deleteById(accomodationBlackListId);
    }
}
