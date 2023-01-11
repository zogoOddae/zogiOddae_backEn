package com.zerobase.accomodation.domain.repository.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.Accomodation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long> {

    Optional<List<Accomodation>> findAllBySellerId(Long sellerId);

    Optional<Accomodation> getFirstBySellerId(Long sellerId);

    Optional<Accomodation> getFirstByIdAndSellerId(Long accomodationId, Long sellerId);
}
