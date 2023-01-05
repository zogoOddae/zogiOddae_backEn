package com.zerobase.accomodation.service;

import com.zerobase.accomodation.domain.entity.leisure.Accomodation;
import com.zerobase.accomodation.domain.form.AddAccomodationForm;
import com.zerobase.accomodation.domain.repository.leisure.AccomodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAccomodationService {
	private final AccomodationRepository accomodationRepository;

	public Accomodation AddLeisure(Long sellerId, AddAccomodationForm form) {
		Accomodation accomodation = Accomodation.of(sellerId, form);
		accomodationRepository.save(accomodation);

		return accomodation;
	}
}
