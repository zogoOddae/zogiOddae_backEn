package com.zerobase.user.member.repository;

import com.zerobase.user.member.entity.MemberLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoginHistoryRepository extends JpaRepository<MemberLoginHistory, Long> {

}
