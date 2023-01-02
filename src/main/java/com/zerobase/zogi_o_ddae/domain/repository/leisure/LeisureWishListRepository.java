package com.zerobase.zogi_o_ddae.domain.repository.leisure;

import com.zerobase.zogi_o_ddae.domain.entity.leisure.LeisureWishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureWishListRepository extends JpaRepository<LeisureWishList, Long> {

}
