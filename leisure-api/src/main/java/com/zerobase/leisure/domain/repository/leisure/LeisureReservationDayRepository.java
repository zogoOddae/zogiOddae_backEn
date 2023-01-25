package com.zerobase.leisure.domain.repository.leisure;

import com.zerobase.leisure.domain.entity.leisure.LeisureReservationDay;
import io.lettuce.core.dynamic.annotation.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureReservationDayRepository extends JpaRepository<LeisureReservationDay, Long> {
	Optional<LeisureReservationDay> findByLeisureIdAndStartAtBetween(Long leisureId, LocalDateTime startAt, LocalDateTime endAt);
	Optional<LeisureReservationDay> findByLeisureIdAndEndAtBetween(Long leisureId, LocalDateTime startAt, LocalDateTime endAt);

	@Query("SELECT distinct leisureId From LeisureReservationDay "
		+ "Where startAt between :startAt and :endAt or endAt between :startAt and :endAt")
	List<Long> findAllLeisureId(@Param("startAt") LocalDate startAt,@Param("endAt") LocalDate endAt);
}
