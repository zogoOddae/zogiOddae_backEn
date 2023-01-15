package com.zerobase.leisure.controller.leisure;

import com.zerobase.leisure.domain.dto.leisure.LeisureWishListDto;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.leisure.LeisureWishService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leisure/wish")
@RequiredArgsConstructor
public class LeisureWishController {

    private final LeisureWishService leisureWishService;

    @PostMapping
    public @ResponseBody WebResponseData<Object> addLeisureWish(@RequestParam Long memberId, @RequestParam Long leisureId) {
        leisureWishService.addLeisureWish(memberId, leisureId);
        return WebResponseData.ok("위시리스트에 성공적으로 등록했습니다.");
    }

    @DeleteMapping
    public @ResponseBody WebResponseData<String> deleteLeisureWish(@RequestParam Long memberId, @RequestParam Long leisureId) {
        leisureWishService.deleteLeisureWish(memberId, leisureId);
        return WebResponseData.ok("성공적으로 삭제 되었습니다.");
    }

    @GetMapping
    public @ResponseBody WebResponseData<List<LeisureWishListDto>> getLeisureWishList(@RequestParam Long memberId) {
        return WebResponseData.ok(leisureWishService.getLeisureWishList(memberId));
    }
}

