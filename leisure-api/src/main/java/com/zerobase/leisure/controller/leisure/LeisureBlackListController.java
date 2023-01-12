package com.zerobase.leisure.controller.leisure;

import com.zerobase.leisure.domain.dto.LeisureBlackListDto;
import com.zerobase.leisure.domain.form.AddLeisureBlackListForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.LeisureBlackListService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leisure/blacklist")
@RequiredArgsConstructor
public class LeisureBlackListController {
    private final LeisureBlackListService leisureBlackListService;

    //블랙리스트 등록
    @PostMapping
    public @ResponseBody WebResponseData<LeisureBlackListDto> addLeisureBlackList(@RequestBody AddLeisureBlackListForm form) {
        return WebResponseData.ok(
            LeisureBlackListDto.from(leisureBlackListService.addLeisureBlackList(form)));
    }

    //블랙 리스트 삭제
    @DeleteMapping
    public @ResponseBody WebResponseData<String> deleteLeisureBlackList(@RequestParam Long leisureBlackListId) {
        leisureBlackListService.deleteLeisureBlackList(leisureBlackListId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }

    @GetMapping
    public @ResponseBody WebResponseData<List<LeisureBlackListDto>> getLeisureBlackList(@RequestParam Long leisureId) {
        return WebResponseData.ok(leisureBlackListService.getLeisureBlackList(leisureId));
    }

}
