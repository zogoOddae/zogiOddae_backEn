package com.zerobase.accomodation.controller.accomodation;

import com.zerobase.accomodation.domain.dto.accomodation.AccomodationWishListDto;
import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import com.zerobase.accomodation.domain.entity.accomodation.AccomodationWishList;
import com.zerobase.accomodation.domain.model.WebResponseData;
import com.zerobase.accomodation.service.accomodation.AccomodationWishService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accomodation/wish")
@RequiredArgsConstructor
public class AccomodationWishController {

    private final AccomodationWishService accomodationWishService;

    //찜 버튼 클릭시 작동
    @PostMapping
    public WebResponseData<AccomodationWishListDto> makeAccomodationWish(@RequestParam Long memberId, @RequestParam Long accomodationId) {
        AccomodationWishList accomodationWishList = accomodationWishService.makeAccomodationwish(memberId, accomodationId);
        Accomodation accomodation = accomodationWishService.getAccomodationInfo(accomodationId);
        return WebResponseData.ok(AccomodationWishListDto.from(accomodationWishList,accomodation));
    }

    //찜 목록 리스트
    @GetMapping
    public WebResponseData<List<AccomodationWishListDto>> getAllAccomodationWish(@RequestParam Long memberId) {
        return WebResponseData.ok(accomodationWishService.getAllAccomodationWish(memberId));
    }


    //찜 목록 리스트에서 삭제
    @DeleteMapping
    public WebResponseData<String> deleteAccomodationWish(@RequestParam Long memberId, @RequestParam Long accomodationId) {
        accomodationWishService.deleteAccomodationwish(memberId,accomodationId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }
}

