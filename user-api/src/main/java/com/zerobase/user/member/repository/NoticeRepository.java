package com.zerobase.user.member.repository;


import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.entity.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {


    List<Notice> findAllByMember(Member member);
}
