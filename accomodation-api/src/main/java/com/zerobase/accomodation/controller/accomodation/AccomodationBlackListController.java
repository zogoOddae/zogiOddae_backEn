package com.zerobase.accomodation.controller.accomodation;

import com.zerobase.accomodation.domain.dto.accomodation.AccomodationBlackListDto;
import com.zerobase.accomodation.domain.form.AddAccomodationBlackListForm;
import com.zerobase.accomodation.domain.model.WebResponseData;
import com.zerobase.accomodation.service.accomodation.AccomodationBlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accomodation/blacklist")
@RequiredArgsConstructor
public class AccomodationBlackListController {
    private final AccomodationBlackListService accomodationBlackListService;

    //블랙리스트 등록
    @PostMapping
    public WebResponseData<AccomodationBlackListDto> addAccomodationBlackList(@RequestBody AddAccomodationBlackListForm form) {
        return WebResponseData.ok(
            AccomodationBlackListDto.from(accomodationBlackListService.addAccomodationBlackList(form)));
    }

    //블랙 리스트 삭제
    @DeleteMapping
    public WebResponseData<String> deleteAccomodationBlackList(@RequestParam Long accomodationBlackListId) {
        accomodationBlackListService.deleteAccomodationBlackList(accomodationBlackListId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }

}
