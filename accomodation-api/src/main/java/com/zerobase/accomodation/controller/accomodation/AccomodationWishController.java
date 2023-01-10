package com.zerobase.accomodation.controller.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationWishList;
import com.zerobase.accomodation.domain.model.WebResponseData;
import com.zerobase.accomodation.service.accomodation.AccomodationWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accomodation/wish")
@RequiredArgsConstructor
public class AccomodationWishController {

    private final AccomodationWishService accomodationWishService;

    //리턴시 방식?을 얘기해봐야할것 같아요. 그냥 클릭하면 등록되고 체크 되어있는걸 다시 클릭하면 삭제되는 식으로 생각해서 로직을 짰는데
    // 음.. 그러면 굳이 메세지 리턴을 안해도 될것 같기도 해서 관련해서 한번 말해보면 될것 같습니다!

    @GetMapping
    public WebResponseData<AccomodationWishList> addAccomodationWish(@RequestParam Long memberId, @RequestParam Long accomodationId) {
        return WebResponseData.ok(accomodationWishService.addAccomodationwish(memberId, accomodationId));
    }



    @DeleteMapping
    public WebResponseData<String> deleteAccomodationWish(@RequestParam Long memberId, @RequestParam Long accomodationId) {
        accomodationWishService.deleteAccomodationwish(memberId,accomodationId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }
}

