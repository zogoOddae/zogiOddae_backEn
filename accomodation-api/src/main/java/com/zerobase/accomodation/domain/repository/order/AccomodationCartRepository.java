package com.zerobase.accomodation.domain.repository.order;

import com.zerobase.accomodation.domain.entity.order.AccomodationCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationCartRepository extends JpaRepository<AccomodationCart, Long> {

}
