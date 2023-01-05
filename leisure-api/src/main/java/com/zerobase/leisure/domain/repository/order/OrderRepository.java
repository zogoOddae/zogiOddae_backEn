package com.zerobase.leisure.domain.repository.order;

import com.zerobase.leisure.domain.entity.order.LeisureOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<LeisureOrder, Long> {

}
