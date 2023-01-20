package com.zerobase.accommodation.controller.accommodation;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationBlackListDto;
import com.zerobase.accommodation.domain.form.accommodation.AddAccommodationBlackListForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.accommodation.AccommodationBlackListService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation/blacklist")
@RequiredArgsConstructor
public class AccommodationBlackListController {
    private final AccommodationBlackListService accommodationBlackListService;

    //블랙리스트 등록
    @PostMapping
    public WebResponseData<String> addAccommodationBlackList(@RequestBody AddAccommodationBlackListForm form) {
        accommodationBlackListService.addAccommodationBlackList(form);
        return WebResponseData.ok("성공적으로 등록 되었습니다.");
    }

    @GetMapping
    public WebResponseData<Page<AccommodationBlackListDto>> getAccommodationBlackList(@RequestParam Long accommodationId, final Pageable pageable) {
        Page<AccommodationBlackListDto> accommodationBlackListDtos = accommodationBlackListService.getAllAccommodationBlackList(accommodationId, pageable);
        return WebResponseData.ok(accommodationBlackListDtos);
    }

    //블랙 리스트 삭제
    @DeleteMapping
    public WebResponseData<String> deleteAccommodationBlackList(@RequestParam Long accommodationBlackListId) {
        accommodationBlackListService.deleteAccommodationBlackList(accommodationBlackListId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }

}
