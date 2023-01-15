package com.zerobase.accommodation.domain.repository.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationDayOff;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationDayOffRepository extends JpaRepository<AccommodationDayOff, Long> {

  Optional<AccommodationDayOff> findByAccommodationId(Long accommodationId);
}
