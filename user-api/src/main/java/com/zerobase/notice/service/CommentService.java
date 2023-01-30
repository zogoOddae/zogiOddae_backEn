package com.zerobase.notice.service;

import com.zerobase.user.member.entity.Member;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentService extends JpaRepository<Comment, Long> {

    List<Comment> findAll();
    Optional<Object> findListByMember(Member member, Pageable pageable);
}
