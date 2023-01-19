package com.zerobase.user.member.service.Impl;

import com.zerobase.user.member.entity.Category;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.entity.Notice;
import com.zerobase.user.member.repository.CategoryRepository;
import com.zerobase.user.member.repository.MemberRepository;
import com.zerobase.user.member.repository.NoticeRepository;
import com.zerobase.user.member.service.NoticeService;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository, MemberRepository memberRepository,
            CategoryRepository categoryRepository) {
        this.noticeRepository = noticeRepository;
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Long notice(Long memberId, Long categoryId, String subject, String description) {
        Member member = memberRepository.getOne(memberId);
        Category category = categoryRepository.getOne(categoryId);
        Notice notice = new Notice();
        notice.createNotice(member, category,subject,description);
        noticeRepository.save(notice);

        return notice.getId();
    }

    @Override
    @Transactional
    public Long updateNotice(Long noticeId, Long categoryId, String subject, String description) {
        Notice notice = noticeRepository.getOne(noticeId);
        Category category = categoryRepository.getOne(categoryId);
        notice.setCategory(category);
        notice.setSubject(subject);
        notice.setDescription(description);
        notice.setUpdateDate(LocalDateTime.now());

        return notice.getId();
    }

    @Override

    public Notice findOne(Long noticeId) {
        return noticeRepository.getOne(noticeId);
    }

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public List<Notice> findByMember(Member member) {
        return noticeRepository.findByMember(member);
    }

    @Override
    public List<Notice> findByCategory(Category category) {
        return noticeRepository.findByCategory(category);
    }
    @Override
    @Transactional
    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

//    @Override
//    @Transactional
//    public void deleteNotice(Long noticeId) {
//        Notice notice = noticeRepository.getOne(noticeId);
//        notice.getMember().getNotice()
//                .removeIf(targetNotice -> targetNotice.equals(notice));
//        notice.getNotices().getNotice()
//                .removeIf(targetNotice -> targetNotice.equals(notice));
//        noticeRepository.deleteById(noticeId);
//    }
    // @Override
    public Page<Notice> getNoticeListByMember(Member member, Pageable pageable) {
        int notice = (pageable.getNumberOfPages() == 0) ? 0 : (pageable.getNumberOfPages() -1);
        //int notice = (pageable.getNoticeNumber() == 0) ? 0 : (pageable.getNoticeNumber() -1);
        pageable = (Pageable) PageRequest.of(notice, 10, Sort.by("id").descending());
        return noticeRepository.findByMember(member, pageable);
    }


}
