package com.zerobase.accomodation.domain.repository.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationBlackList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationBlackListRepository extends JpaRepository<AccomodationBlackList, Long> {

    Optional<AccomodationBlackList> findByCustomerIdAndAccomodationId(Long customerId, Long accomodationId);
}
