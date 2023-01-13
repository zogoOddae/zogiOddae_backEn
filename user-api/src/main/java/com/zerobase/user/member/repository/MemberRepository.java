package com.zerobase.user.member.repository;

import com.zerobase.common.type.MemberPlatform;
import com.zerobase.user.member.entity.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByPlatformAndPlatformId(MemberPlatform platform, String platformId);

    boolean existsByMemberId(String memberId);
    Optional<Member> findByMemberIdAndPassword(String MemberId, String password);
    Page<Member> findAll(Pageable pageable);
}
