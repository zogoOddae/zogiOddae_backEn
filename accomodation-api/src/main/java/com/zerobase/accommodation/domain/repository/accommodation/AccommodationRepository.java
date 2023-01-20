package com.zerobase.accommodation.domain.repository.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Optional<Page<Accommodation>> findAllBySellerId(Long sellerId);
    Optional<Accommodation> findByIdAndSellerId(Long accommodationId, Long sellerId);
}
