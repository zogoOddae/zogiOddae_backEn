package com.zerobase.accommodation.domain.repository.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Optional<List<Accommodation>> findAllBySellerId(Long sellerId);
    Optional<Accommodation> getFirstByIdAndSellerId(Long accommodationId, Long sellerId);
}
