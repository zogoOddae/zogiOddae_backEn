package com.zerobase.zogi_o_ddae.domain.repository.common;

import com.zerobase.zogi_o_ddae.domain.entity.common.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
