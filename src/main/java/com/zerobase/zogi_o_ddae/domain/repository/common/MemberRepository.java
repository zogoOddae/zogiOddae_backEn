package com.zerobase.zogi_o_ddae.domain.repository.common;

import com.zerobase.zogi_o_ddae.domain.entity.common.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
