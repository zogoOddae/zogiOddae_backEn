package com.zerobase.user.member.service;

import com.zerobase.user.member.entity.Category;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.entity.Notice;
import java.awt.print.Pageable;
import java.util.List;

public interface NoticeService {

    public Long notice(Long memberId, Long categoryId, String subject, String description);
    public Long updateNotice(Long noticeId, Long categoryId, String subject, String description);
    public Notice findOne(Long noticeId);
    public List<Notice> findAll();
    public List<Notice> findByMember(Member member);
    public List<Notice> findByCategory(Category category);
    public void deleteNotice(Long noticeId);

}
