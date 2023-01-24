package com.zerobase.accommodation.domain.repository.search;

import com.zerobase.accommodation.domain.entity.accommodation.AccommodationReservationDay;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationReservationDayRepository extends JpaRepository<AccommodationReservationDay, Long> {
    @Query("SELECT distinct accommodationId From AccommodationReservationDay "
        + "Where startAt between :p_startAt and :p_endAt or endAt between :p_startAt and :p_endAt")
    List<Long> findAllaccommodationId(@Param("p_startAt") LocalDate startAt,@Param("p_endAt") LocalDate endAt);

    
}
