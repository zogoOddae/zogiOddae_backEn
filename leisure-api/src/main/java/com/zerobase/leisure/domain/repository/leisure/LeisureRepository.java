package com.zerobase.leisure.domain.repository.leisure;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureRepository extends JpaRepository<Leisure, Long> {
	Page<Leisure> findAllBySellerId(Long sellerId, Pageable limit);
	Optional<Leisure> getFirstByIdAndSellerId(Long id, Long sellerId);
}
