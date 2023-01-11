package com.zerobase.leisure.service;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.form.AddLeisureForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.util.List;
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

	public List<Leisure> getAllLeisure(Long sellerId) {
		return leisureRepository.findAllBySellerId(sellerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_HAVE_LEISURE));
	}

	public Leisure getDetailLeisure(Long leisureId, Long sellerId) {
		return leisureRepository.getFirstByIdAndSellerId(leisureId, sellerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));
	}

	public Leisure updateLeisure(Long leisureId, AddLeisureForm form) {
		Leisure leisure = leisureRepository.findById(leisureId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

		leisure.setLeisureName(form.getName());
		leisure.setAddr(form.getAddr());
		leisure.setDescription(form.getDescription());
		leisure.setPrice(form.getPrice());
		leisure.setPictureUrl(form.getPictureUrl());
		leisure.setMaxPerson(form.getMaxPerson());
		leisure.setMinPerson(form.getMinPerson());
		leisure.setLat(form.getLat());
		leisure.setLon(form.getLon());

		leisureRepository.save(leisure);
		return leisure;
	}

	public void deleteLeisure(Long leisureId, Long sellerId) {
		leisureRepository.delete(leisureRepository.getFirstByIdAndSellerId(leisureId,sellerId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE)));
	}
}
