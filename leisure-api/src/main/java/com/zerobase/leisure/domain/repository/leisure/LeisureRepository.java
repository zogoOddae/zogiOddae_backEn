package com.zerobase.leisure.domain.repository.leisure;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureRepository extends JpaRepository<Leisure, Long> {
	Optional<List<Leisure>> findAllBySellerId(Long sellerId);
	Optional<Leisure> getFirstByIdAndSellerId(Long id, Long sellerId);
}
