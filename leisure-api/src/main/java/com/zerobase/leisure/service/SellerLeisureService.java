package com.zerobase.leisure.service;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.form.AddLeisureForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerLeisureService {
	private final LeisureRepository leisureRepository;

	public Leisure AddLeisure(Long sellerId, AddLeisureForm form) {
		Leisure leisure = Leisure.of(sellerId, form);
		leisureRepository.save(leisure);

		return leisure;
	}
}
