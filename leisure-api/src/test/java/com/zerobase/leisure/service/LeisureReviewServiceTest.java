package com.zerobase.leisure.service;

import static org.junit.jupiter.api.Assertions.*;

import com.zerobase.leisure.service.leisure.LeisureReviewService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
@RequiredArgsConstructor
class LeisureReviewServiceTest {
	@Autowired
	private LeisureReviewService leisureReviewService;


}