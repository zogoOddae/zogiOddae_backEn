package com.zerobase.accomodation.service.accomodation;

import com.zerobase.accomodation.domain.dto.accomodation.AccomodationListDto;
import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import com.zerobase.accomodation.domain.form.AccomodationForm;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationRepository;
import com.zerobase.accomodation.domain.type.ErrorCode;
import com.zerobase.accomodation.exception.AccomodationException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAccomodationService {
	private final AccomodationRepository accomodationRepository;

	public Accomodation addAccomodation(Long sellerId, AccomodationForm form) {
		Accomodation accomodation = Accomodation.of(sellerId, form);
		accomodationRepository.save(accomodation);

		return accomodation;
	}

	public List<AccomodationListDto> getAllAccomodation(Long sellerId) {
		 List<Accomodation> accomodationList = accomodationRepository.findAllBySellerId(sellerId)
			.orElseThrow(() -> new AccomodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

		List<AccomodationListDto> dtoList = new ArrayList<>();

		for(Accomodation accomodation : accomodationList){
			dtoList.add(AccomodationListDto.from(accomodation));
		}
		return dtoList;
	}

	public Accomodation updateAccomodation(Long accomodationId, AccomodationForm form) {
		Accomodation accomodation = accomodationRepository.findById(accomodationId)
			.orElseThrow(() -> new AccomodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

		accomodation.setAccomodationName(form.getAccomodationName());
		accomodation.setSellerId(accomodation.getSellerId());
		accomodation.setAddr(form.getAddr());
		accomodation.setDescription(form.getDescription());
		accomodation.setPrice(form.getPrice());
		accomodation.setPictureUrl(form.getPictureUrl());
		accomodation.setMaxPerson(form.getMaxPerson());
		accomodation.setMinPerson(form.getMinPerson());
		accomodation.setLat(form.getLat());
		accomodation.setLon(form.getLon());

		accomodation.setAccomodationBlackList(accomodation.getAccomodationBlackList());

		return accomodation;
	}

	public Accomodation getDetailAccomodation(Long accomodationId, Long sellerId) {
		return accomodationRepository.getFirstByIdAndSellerId(accomodationId,sellerId)
			.orElseThrow(() -> new AccomodationException(ErrorCode.NOT_FOUND_ACCOMODATION));
	}

	public void deleteAccomodation(Long accomodationId, Long sellerId) {
		accomodationRepository.getFirstByIdAndSellerId(accomodationId,sellerId).orElseThrow(() -> new AccomodationException(ErrorCode.NOT_FOUND_ACCOMODATION));

		accomodationRepository.deleteById(accomodationId);
	}
}
