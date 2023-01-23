package com.zerobase.leisure.domain.repository.leisure;

import com.zerobase.leisure.domain.entity.leisure.LeisureReservationDay;
import io.lettuce.core.dynamic.annotation.Param;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureReservationDayRepository extends JpaRepository<LeisureReservationDay, Long> {
	Optional<LeisureReservationDay> findByLeisureIdAndStartAtBetween(Long leisureId, LocalDateTime startAt, LocalDateTime endAt);
	Optional<LeisureReservationDay> findByLeisureIdAndEndAtBetween(Long leisureId, LocalDateTime startAt, LocalDateTime endAt);

	List<Long> findAllLeisureId(@Param("p_startAt") LocalDate startAt,@Param("p_endAt") LocalDate endAt);
}
