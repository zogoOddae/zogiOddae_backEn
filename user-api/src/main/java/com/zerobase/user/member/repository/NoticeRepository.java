package com.zerobase.user.member.repository;


import com.zerobase.user.member.entity.Category;
import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.entity.Notice;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{


    List<Notice> findAllByMember(Member member);
        public List<Notice> findByMember(Member member);
        public List<Notice> findByCategory(Category category);

        Page<Notice> findByMember(Member member, Pageable pageable);

    }


