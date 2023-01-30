package com.zerobase.user.member.repository;

import com.zerobase.user.member.entity.Category;
import com.zerobase.user.member.entity.Member;
import com.zerobase.notice.entity.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String categoryName);

    public Long notice(Long memberId, Long categoryId, String subject, String description);
    public Long updateCategory(Long noticeId, Long categoryId, String subject, String description);
    public Notice findOne(Long categoryId);
    public List<Category> findAll();
    public List<Category> findByMember(Member member);
    public List<Category> findByNotice(Notice notice);
    public void deleteCategory(Long categoryId);


}
