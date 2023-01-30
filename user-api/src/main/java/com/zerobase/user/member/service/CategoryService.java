package com.zerobase.user.member.service;

import com.zerobase.user.member.entity.Category;
import com.zerobase.user.member.entity.Member;
import com.zerobase.notice.entity.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryService extends JpaRepository<Category, Long> {
    public List<Category> findAll();
    public List<Notice> findByMember(Member member);

}
