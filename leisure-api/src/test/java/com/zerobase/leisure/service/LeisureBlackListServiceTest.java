package com.zerobase.leisure.service;

import static org.junit.jupiter.api.Assertions.*;

import com.zerobase.leisure.domain.dto.leisure.LeisureBlackListDto;
import com.zerobase.leisure.domain.entity.leisure.LeisureBlackList;
import com.zerobase.leisure.domain.form.AddLeisureBlackListForm;
import com.zerobase.leisure.domain.repository.common.LeisureBlackListRepository;
import com.zerobase.leisure.exception.LeisureException;
import com.zerobase.leisure.service.leisure.LeisureBlackListService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
class LeisureBlackListServiceTest {
	@Autowired
	private LeisureBlackListService leisureBlackListService;
	@Autowired
	private LeisureBlackListRepository leisureBlackListRepository;

	@Test
	@DisplayName("블랙리스트 등록")
	void addLeisureBlackListTest() {
	    //given
		LeisureBlackList leisureBlackList = leisureBlackListService.addLeisureBlackList(addLeisureBlackListForm());
	    //when
	    //then
		assertEquals(leisureBlackList.getLeisureId(), 1L);
		assertEquals(leisureBlackList.getCustomerId(), 1L);
		assertEquals(leisureBlackList.getDescription(), "당신은 재미가 없어요.");

		assertThrows(LeisureException.class, () ->{
			leisureBlackListService.addLeisureBlackList(addLeisureBlackListForm());
		});
	}

	@Test
	@DisplayName("블랙리스트 삭제")
	void deleteLeisureBlackListTest() {
	    //given
		LeisureBlackList leisureBlackList = leisureBlackListService.addLeisureBlackList(addLeisureBlackListForm());
		//when
		leisureBlackListService.deleteLeisureBlackList(leisureBlackList.getId());
		Optional<LeisureBlackList> optionalLeisureBlackList = leisureBlackListRepository.findById(leisureBlackList.getId());
	    //then
		assertFalse(optionalLeisureBlackList.isPresent());
	}

	@Test
	@DisplayName("시설 블랙리스트 가져오기")
	void getLeisureBlackListTest() {
	    //given
		leisureBlackListService.addLeisureBlackList(AddLeisureBlackListForm.builder()
			.leisureId(1L)
			.customerId(1L)
			.description("당신은 재미가 없어요.")
			.build());
		leisureBlackListService.addLeisureBlackList(AddLeisureBlackListForm.builder()
			.leisureId(1L)
			.customerId(2L)
			.description("당신은 재미가 있어요.")
			.build());
		leisureBlackListService.addLeisureBlackList(AddLeisureBlackListForm.builder()
			.leisureId(1L)
			.customerId(3L)
			.description("당신은 재미가 하나도 없어요.")
			.build());
	    //when
		List<LeisureBlackListDto> leisureBlackListDtoList = leisureBlackListService.getLeisureBlackList(1L);
	    //then
		assertEquals(leisureBlackListDtoList.get(0).getCustomerId(), 1L);
		assertEquals(leisureBlackListDtoList.get(1).getCustomerId(), 2L);
		assertEquals(leisureBlackListDtoList.get(2).getCustomerId(), 3L);
		assertEquals(leisureBlackListDtoList.get(0).getDescription(), "당신은 재미가 없어요.");
		assertEquals(leisureBlackListDtoList.get(1).getDescription(), "당신은 재미가 있어요.");
		assertEquals(leisureBlackListDtoList.get(2).getDescription(), "당신은 재미가 하나도 없어요.");
	}

	private AddLeisureBlackListForm addLeisureBlackListForm(){
		return AddLeisureBlackListForm.builder()
			.leisureId(1L)
			.customerId(1L)
			.description("당신은 재미가 없어요.")
			.build();
	}
}