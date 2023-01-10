package com.zerobase.accomodation.service.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import com.zerobase.accomodation.domain.form.AddAccomodationForm;
import com.zerobase.accomodation.domain.repository.accomodation.AccomodationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAccomodationService {
	private final AccomodationRepository accomodationRepository;

	public Accomodation addAccomodation(Long sellerId, AddAccomodationForm form) {
		Accomodation accomodation = Accomodation.of(sellerId, form);
		accomodationRepository.save(accomodation);

		return accomodation;
	}

	public List<Accomodation> getAllAccomodation(Long sellerId) {
		return accomodationRepository.findAllBySellerId(sellerId)
			.orElseThrow(() -> new RuntimeException("등록된 숙박시설이 없습니다."));
	}

	public Accomodation updateAccomodation(Long sellerId, AddAccomodationForm form) {
		Accomodation accomodation = accomodationRepository.getFirstBySellerId(sellerId)
			.orElseThrow(() -> new RuntimeException("등록된 숙박시설이 없습니다."));

		accomodation.setAccomodationName(form.getAccomodationName());
		accomodation.setSellerId(sellerId);
		accomodation.setAddr(form.getAddr());
		accomodation.setDescription(form.getDescription());
		accomodation.setPrice(form.getPrice());
		accomodation.setPictureUrl(form.getPictureUrl());
		accomodation.setMaxPerson(form.getMaxPerson());
		accomodation.setMinPerson(form.getMinPerson());
		accomodation.setLat(form.getLat());
		accomodation.setLon(form.getLon());
		//맞나 모르겠네요
		accomodation.setAccomodationBlackList(accomodation.getAccomodationBlackList());


		return accomodation;
	}

	public Accomodation getDetailAccomodation(Long accomodationId, Long sellerId) {
		return accomodationRepository.getFirstByIdAndSellerId(accomodationId,sellerId)
			.orElseThrow(() -> new RuntimeException("등록된 숙박시설이 없습니다."));
	}

	public void deleteAccomodation(Long accomodationId, Long sellerId) {
		accomodationRepository.getFirstByIdAndSellerId(accomodationId,sellerId).orElseThrow(() -> new RuntimeException("등록된 숙박시설이 없습니다."));

		accomodationRepository.deleteById(accomodationId);
	}
}
