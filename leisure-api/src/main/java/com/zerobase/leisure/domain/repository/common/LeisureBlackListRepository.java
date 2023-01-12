package com.zerobase.leisure.domain.repository.common;

import com.zerobase.leisure.domain.entity.leisure.LeisureBlackList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureBlackListRepository extends JpaRepository<LeisureBlackList, Long> {
	Optional<LeisureBlackList> findByLeisureId(Long leisureId);

	Optional<LeisureBlackList> findByCustomerIdAndLeisureId(Long customerId, Long leisureId);

	Optional<List<LeisureBlackList>> findAllByLeisureId(Long leisureId);
}
