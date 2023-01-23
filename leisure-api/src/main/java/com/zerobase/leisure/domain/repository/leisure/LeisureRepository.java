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

	@Query(nativeQuery = true, value = "SELECT * From accommodation as a"
		+ " Where a.addr Like :p_addr AND a.min_person <= :per1 AND a.max_person >= :per2 AND a.id NOT IN (:p_ids)")
	Optional<Leisure> findAllByAddr(@Param("p_addr") String addr,@Param("per1")Integer personnel,@Param("per2") Integer personnel2,@Param("p_ids")List<Long> accommodationIds);

	List<Leisure> findAllByAddrLikeAndMinPersonLessThanEqualAndMaxPersonGreaterThanEqual(String addr, Integer personnel, Integer personnel2);

	Optional<Page<Leisure>> findAllByAddrContaining(String s_addr, Pageable limit);

	Optional<Page<Leisure>> findAllByCategoryContaining(String category, Pageable limit);
}
