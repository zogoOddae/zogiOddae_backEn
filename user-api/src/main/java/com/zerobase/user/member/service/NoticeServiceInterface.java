package com.zerobase.user.member.service;

import com.zerobase.user.member.entity.Category;
import com.zerobase.user.member.entity.Member;
import com.zerobase.notice.entity.Notice;
import java.util.List;

public interface NoticeServiceInterface {

    public Long notice(Long memberId, Long categoryId, String subject, String description);
    public Long updateNotice(Long noticeId, Long categoryId, String subject, String description);
    public Notice findOne(Long noticeId);
    public List<Notice> findAll();
    public List<Notice> findByMember(Member member);
    public List<Notice> findByCategory(Category category);
    public void deleteNotice(Long noticeId);

}
