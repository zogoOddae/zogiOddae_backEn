package com.zerobase.accomodation.domain.repository.common;

import com.zerobase.accomodation.domain.entity.common.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
