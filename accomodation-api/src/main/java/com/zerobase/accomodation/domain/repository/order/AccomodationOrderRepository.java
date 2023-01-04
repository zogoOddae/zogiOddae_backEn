package com.zerobase.accomodation.domain.repository.order;

import com.zerobase.accomodation.domain.entity.order.AccomodationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccomodationOrderRepository extends JpaRepository<AccomodationOrder, Long> {

}
