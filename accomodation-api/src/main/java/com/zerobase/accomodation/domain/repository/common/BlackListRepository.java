package com.zerobase.accomodation.domain.repository.common;

import com.zerobase.accomodation.domain.entity.common.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {

}
