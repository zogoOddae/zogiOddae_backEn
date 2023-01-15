package com.zerobase.leisure.domain.repository.leisure;

import com.zerobase.leisure.domain.entity.leisure.LeisureDayOff;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureDayOffRepository extends JpaRepository<LeisureDayOff, Long> {
	Optional<LeisureDayOff> findByLeisureId(Long LeisureId);
	Optional<List<LeisureDayOff>> findAllByLeisureId(Long leisureId);
}
