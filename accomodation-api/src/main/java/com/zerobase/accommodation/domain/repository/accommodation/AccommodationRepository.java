package com.zerobase.accommodation.domain.repository.accommodation;

import com.zerobase.accommodation.domain.entity.accommodation.Accommodation;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Page<Accommodation> findAllBySellerId(Long sellerId, Pageable pageable);
    Optional<Accommodation> findByIdAndSellerId(Long accommodationId, Long sellerId);

    @Query(nativeQuery = true, value = "SELECT * From accommodation as a"
        + " Where a.addr Like :p_addr AND a.min_person <= :per1 AND a.max_person >= :per2 AND a.id NOT IN (:p_ids)")
    Optional<Accommodation> findAllByAddr(@Param("p_addr") String addr,@Param("per1")Integer personnel,@Param("per2") Integer personnel2,@Param("p_ids")List<Long> accommodationIds);

    List<Accommodation> findAllByAddrLikeAndMinPersonLessThanEqualAndMaxPersonGreaterThanEqual(String addr, Integer personnel, Integer personnel2);

    Optional<Page<Accommodation>> findAllByAddrContaining(String s_addr, Pageable limit);

    Optional<Page<Accommodation>> findAllByCategoryContaining(String category, Pageable limit);

}
