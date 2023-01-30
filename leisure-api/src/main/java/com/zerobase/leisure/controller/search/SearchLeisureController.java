package com.zerobase.leisure.controller.search;

import com.zerobase.leisure.domain.dto.leisure.LeisureListDto;
import com.zerobase.leisure.domain.form.SearchLeisureForm;
import com.zerobase.leisure.domain.model.WebResponseData;
import com.zerobase.leisure.service.search.SearchLeisureService;
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
@RequestMapping("/leisure/search")
@RequiredArgsConstructor
public class SearchLeisureController {

	private final SearchLeisureService searchLeisureService;

	@PostMapping
	public WebResponseData<Page<LeisureListDto>> getAllSearchResult(@RequestBody SearchLeisureForm form, final Pageable pageable) {
		Page<LeisureListDto> leisureListDtoPage = searchLeisureService.getAllSearchResult(form, pageable);
		return WebResponseData.ok(leisureListDtoPage);
	}

	@GetMapping("/addr")
	public WebResponseData<Page<LeisureListDto>> getAllSearchAddr(@RequestParam String addr, final Pageable pageable) {
		Page<LeisureListDto> leisureListDtoPage = searchLeisureService.getAllSearchAddr(addr, pageable);
		return WebResponseData.ok(leisureListDtoPage);
	}

	@GetMapping("/category")
	public WebResponseData<Page<LeisureListDto>> getAllSearchCategory(@RequestParam String category, final Pageable pageable) {
		Page<LeisureListDto> leisureListDtoPage = searchLeisureService.getAllSearchCategory(category, pageable);
		return WebResponseData.ok(leisureListDtoPage);
	}
}
