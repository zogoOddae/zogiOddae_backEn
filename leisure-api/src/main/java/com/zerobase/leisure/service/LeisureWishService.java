package com.zerobase.leisure.service;

import com.zerobase.leisure.domain.dto.LeisureDto;
import com.zerobase.leisure.domain.dto.LeisureWishListDto;
import com.zerobase.leisure.domain.entity.leisure.LeisureWishList;
import com.zerobase.leisure.domain.repository.leisure.LeisureRepository;
import com.zerobase.leisure.domain.repository.leisure.LeisureWishListRepository;
import com.zerobase.leisure.domain.type.ErrorCode;
import com.zerobase.leisure.exception.LeisureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeisureWishService {

    private final LeisureWishListRepository leisureWishListRepository;
    private final LeisureRepository leisureRepository;

    public void addLeisureWish(Long memberId, Long leisureId) {
       Optional<LeisureWishList> optionalLeisureWishList =  leisureWishListRepository.findByMemberId(memberId);
       if(optionalLeisureWishList.isPresent()){
           LeisureWishList wishList = optionalLeisureWishList.get();
           List<Long> list = wishList.getLeisureId();
           if (list.contains(leisureId)){
               list.remove(leisureId);
           } else {
               list.add(leisureId);
           }
           wishList.setLeisureId(list);
           leisureWishListRepository.save(wishList);
       } else {
           leisureWishListRepository.save(LeisureWishList.builder()
               .memberId(memberId)
               .leisureId(new ArrayList<>(Arrays.asList(leisureId)))
               .build());
       }
    }

    public void deleteLeisureWish(Long memberId, Long leisureId) {
        LeisureWishList leisureWishList = leisureWishListRepository.findByMemberId(memberId)
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_HAD_WISHLIST));

        List<Long> list = leisureWishList.getLeisureId();
        list.remove(leisureId);
        if (list.isEmpty()) {
            leisureWishListRepository.deleteByMemberId(memberId);
        } else {
            leisureWishList.setLeisureId(list);
            leisureWishListRepository.save(leisureWishList);
        }
    }

    public LeisureWishListDto getLeisureWishList(Long memberId) {
        LeisureWishList leisureWishList = leisureWishListRepository.findByMemberId(memberId)
            .orElseThrow(() -> new LeisureException(ErrorCode.NOT_HAD_WISHLIST));

        return LeisureWishListDto.builder()
            .id(leisureWishList.getId())
            .memberId(memberId)
            .wishList(LeisureDto.fromList(leisureRepository.findAllById(leisureWishList.getLeisureId())))
            .build();
    }
}
