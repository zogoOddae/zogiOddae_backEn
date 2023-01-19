package com.zerobase.user.member.service;

import com.zerobase.user.member.entity.Member;
import com.zerobase.user.member.entity.Notice;
import java.util.List;

public interface CategoryService  {
    public List<Notice> findAll();
    public List<Notice> findByMember(Member member);

}
