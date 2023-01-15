package com.zerobase.leisure.domain.repository.leisure;

import com.zerobase.leisure.domain.entity.leisure.LeisureWishList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureWishListRepository extends JpaRepository<LeisureWishList, Long> {
	Optional<LeisureWishList> findByMemberId(Long memberId);


	void deleteByMemberIdAndLeisureId(Long memberId, Long leisureId);

	Optional<List<LeisureWishList>> findAllByMemberId(Long memberId);
}
