package com.zerobase.notice.repository;


import com.zerobase.user.member.entity.Category;
import com.zerobase.user.member.entity.Member;
import com.zerobase.notice.entity.Notice;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{

    boolean existsByMemberIdAndNoticeId(Long MemberId, Long NoticeId);
    Notice findNoticeById(Notice noticeId);
    List<Notice> findByMember(Member member);
    List<Notice> findAllByMember(Member member);
    List<Notice> findByCategory(Category category);

    Page<Notice> findByMember(Member member, Pageable pageable);
    Optional<Notice> findOneByParentId(Long id);

    List<Notice> findByMemberId(Optional<Member> memberId);

    Page<Notice> findAll(Pageable pageable);
    Optional<Notice> findByMemberIdAndNoticeId(Long MemberId, Long NoticeId);

    }


