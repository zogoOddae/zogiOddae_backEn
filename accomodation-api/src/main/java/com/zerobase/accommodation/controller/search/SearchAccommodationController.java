package com.zerobase.accommodation.controller.search;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationListDto;
import com.zerobase.accommodation.domain.form.search.SearchAccommodationForm;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.search.SearchAccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation/search")
@RequiredArgsConstructor
public class SearchAccommodationController {

    private final SearchAccommodationService searchAccommodationService;

    @PostMapping
    public WebResponseData<Page<AccommodationListDto>> getAllSearchResult(@RequestBody SearchAccommodationForm form, final Pageable pageable) {
        Page<AccommodationListDto> accommodationDtos = searchAccommodationService.getAllSearchResult(form, pageable);
        return WebResponseData.ok(accommodationDtos);
    }

    @GetMapping("/addr")
    public WebResponseData<Page<AccommodationListDto>> getAllSearchAddr(@RequestParam String addr, final Pageable pageable) {
        Page<AccommodationListDto> accommodationDtos = searchAccommodationService.getAllSearchAddr(addr, pageable);
        return WebResponseData.ok(accommodationDtos);
    }

    @GetMapping("/category")
    public WebResponseData<Page<AccommodationListDto>> getAllSearchCategory(@RequestParam String category, final Pageable pageable) {
        Page<AccommodationListDto> accommodationDtos = searchAccommodationService.getAllSearchCategory(category, pageable);
        return WebResponseData.ok(accommodationDtos);
    }

}
