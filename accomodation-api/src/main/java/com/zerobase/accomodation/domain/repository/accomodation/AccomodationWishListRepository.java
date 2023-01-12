package com.zerobase.accomodation.domain.repository.accomodation;

import com.zerobase.accomodation.domain.entity.accomodation.AccomodationWishList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationWishListRepository extends JpaRepository<AccomodationWishList, Long> {

    void deleteByMemberIdAndAccomodationId(Long memberId, Long accomodationId);

    Optional<AccomodationWishList> findByMemberIdAndAccomodationId(Long memberId, Long accomodationId);

    Optional<AccomodationWishList> findByMemberId(Long memberId);

}
