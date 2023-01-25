package com.zerobase.member.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerobase.common.type.MemberPlatform;
import com.zerobase.member.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPlatformAndPlatformId(MemberPlatform platform, String platformId);
}
