package com.zerobase.notice.service;


import com.zerobase.notice.repository.NoticeRepository;
import com.zerobase.user.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserNoticeService {
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

}
