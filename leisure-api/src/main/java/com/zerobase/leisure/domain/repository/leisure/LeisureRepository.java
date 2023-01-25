package com.zerobase.leisure.domain.repository.leisure;

import com.zerobase.leisure.domain.entity.leisure.Leisure;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureRepository extends JpaRepository<Leisure, Long> {
	Page<Leisure> findAllBySellerId(Long sellerId, Pageable limit);
	Optional<Leisure> getFirstByIdAndSellerId(Long id, Long sellerId);

	@Query(nativeQuery = true, value = "SELECT * From leisure as a"
		+ " Where a.addr Like :addr AND a.min_person <= :personnel AND a.max_person >= :personnel2 AND a.id NOT IN (:leisureIds)")
	Optional<Leisure> findAllByAddr(@Param("addr")String addr, @Param("personnel")Integer personnel, @Param("personnel2") Integer personnel2, @Param("leisureIds")List<Long> leisureIds);

	List<Leisure> findAllByAddrLikeAndMinPersonLessThanEqualAndMaxPersonGreaterThanEqual(String addr, Integer personnel, Integer personnel2);

	Optional<Page<Leisure>> findAllByAddrContaining(String s_addr, Pageable limit);

	Optional<Page<Leisure>> findAllByCategoryContaining(String category, Pageable limit);
}
