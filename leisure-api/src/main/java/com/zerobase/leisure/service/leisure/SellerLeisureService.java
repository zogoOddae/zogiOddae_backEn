package com.zerobase.leisure.service.leisure;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.leisure.LeisureDayOff;
import com.zerobase.leisure.domain.form.LeisureDayOffForm;
import com.zerobase.leisure.domain.form.LeisureForm;
import com.zerobase.leisure.domain.repository.leisure.LeisureDayOffRepository;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerLeisureService {
	private final LeisureRepository leisureRepository;
	private final LeisureDayOffRepository leisureDayOffRepository;

	public Leisure AddLeisure(Long sellerId, LeisureForm form) {
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

	public Leisure updateLeisure(Long leisureId, LeisureForm form) {
		Leisure leisure = leisureRepository.findById(leisureId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

		leisure.setLeisureName(form.getLeisureName());
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

	public LeisureDayOff addLeisureDayOff(Long leisureId, LeisureDayOffForm form) {
		String dayOffStart = form.getStartDay().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		return leisureDayOffRepository.save(LeisureDayOff.builder()
			.leisureId(leisureId)
			.year(dayOffStart.substring(0, 4))
			.startDate(form.getStartDay())
			.endDate(form.getEndDay())
			.build());
	}

	public void deleteLeisureDayOff(Long leisureDayOffId) {
		leisureDayOffRepository.deleteById(leisureDayOffId);
	}

	public List<LeisureDayOff> getLeisureDayOff(Long leisureId) {
		return leisureDayOffRepository.findAllByLeisureId(leisureId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_HAD_DAY_OFF));
	}

	public void updateLeisureDayOff(Long leisureDayOffId, LeisureDayOffForm form) {
		LeisureDayOff leisureDayOff = leisureDayOffRepository.findById(leisureDayOffId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_DAY_OFF));

		String dayOffStart = form.getStartDay().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		leisureDayOff.setYear(dayOffStart.substring(0,4));
		leisureDayOff.setStartDate(form.getStartDay());
		leisureDayOff.setEndDate(form.getEndDay());

		leisureDayOffRepository.save(leisureDayOff);
	}
}
