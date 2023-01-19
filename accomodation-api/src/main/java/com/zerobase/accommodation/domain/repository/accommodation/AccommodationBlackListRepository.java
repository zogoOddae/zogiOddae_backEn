package com.zerobase.accommodation.domain.repository.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationBlackList;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationBlackListRepository extends JpaRepository<AccommodationBlackList, Long> {

    Optional<AccommodationBlackList> findByCustomerIdAndAccommodationId(Long customerId, Long accommodationId);

    Page<AccommodationBlackList> findAllByAccommodationId(Long accommodationId, Pageable limit);
}
