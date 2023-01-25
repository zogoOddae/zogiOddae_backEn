package com.zerobase.accommodation.controller.accommodation;

import com.zerobase.accommodation.domain.dto.accommodation.AccommodationListDto;
import com.zerobase.accommodation.domain.dto.accommodation.CustomerAccommodationDto;
import com.zerobase.accommodation.domain.model.WebResponseData;
import com.zerobase.accommodation.service.accommodation.CustomerAccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer/accommodation")
@RequiredArgsConstructor
public class CustomerAccommodationController {
    private final CustomerAccommodationService customerAccommodationService;

    @GetMapping
    public @ResponseBody WebResponseData<Page<AccommodationListDto>> getAccommodation(@RequestParam Long sellerId, final
    Pageable pageable) { //소비자 상품 전체 조회
        Page<AccommodationListDto> accommodationDtos = customerAccommodationService.getAllaccommodation(sellerId, pageable);
        return WebResponseData.ok(accommodationDtos);
    }

    @GetMapping("/detail")
    public @ResponseBody WebResponseData<CustomerAccommodationDto> getDetailAccommodation(@RequestParam Long accommodationId) {
        return WebResponseData.ok(
            CustomerAccommodationDto.from(customerAccommodationService.getDetailAccommodation(accommodationId)));
    }
}
