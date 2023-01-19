package com.zerobase.user.member.repository;

import com.zerobase.common.type.MemberPlatform;
import com.zerobase.user.member.entity.Member;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Transactional
    Long updateInfo(String username, String newName);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPlatformAndPlatformId(MemberPlatform platform, String platformId);

    Optional<Member> findByUuid(String uuid);
    boolean existsByMemberId(String memberId);
    Optional<Member> findByMemberIdAndPassword(String MemberId, String password);
    Page<Member> findAll(Pageable pageable);

    boolean existsByEmail(String email);

    List<Member> findAll();

    boolean existsByNickName(String nickName);


}


