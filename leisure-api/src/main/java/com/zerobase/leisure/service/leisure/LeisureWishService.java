package com.zerobase.leisure.service.leisure;

import com.zerobase.leisure.domain.dto.leisure.LeisureWishListDto;
import com.zerobase.leisure.domain.entity.leisure.Leisure;
import com.zerobase.leisure.domain.entity.leisure.LeisureWishList;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.repository.leisure.LeisureWishListRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureWishService {

	private final LeisureWishListRepository leisureWishListRepository;
	private final LeisureRepository leisureRepository;

	public LeisureWishList addLeisureWish(Long memberId, Long leisureId) {
		leisureRepository.findById(leisureId)
			.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

		return leisureWishListRepository.save(LeisureWishList.builder()
			.memberId(memberId)
			.leisureId(leisureId)
			.build());
	}

	public void deleteLeisureWish(Long memberId, Long leisureId) {
		leisureWishListRepository.deleteByMemberIdAndLeisureId(memberId, leisureId);
	}

	public List<LeisureWishListDto> getLeisureWishList(Long memberId) {
		Optional<List<LeisureWishList>> optionalLeisureWishList = leisureWishListRepository.findAllByMemberId(
			memberId);
		List<LeisureWishListDto> leisureWishListDtoList = new ArrayList<>();
		if (!optionalLeisureWishList.isPresent()) {
			return leisureWishListDtoList;
		}

		for (LeisureWishList leisureWishList : optionalLeisureWishList.get()) {
			Leisure leisure = leisureRepository.findById(leisureWishList.getLeisureId())
				.orElseThrow(() -> new LeisureException(ErrorCode.NOT_FOUND_LEISURE));

			leisureWishListDtoList.add(
				LeisureWishListDto.builder()
					.leisureWishListId(leisureWishList.getId())
					.leisureId(leisureWishList.getLeisureId())
					.memberId(memberId)
					.leisureName(leisure.getLeisureName())
					.addr(leisure.getAddr())
					.price(leisure.getPrice())
					.pictureUrl(leisure.getPictureUrl())
					.build()
			);
		}
		return leisureWishListDtoList;
	}
}
