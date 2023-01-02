package com.zerobase.zogi_o_ddae.domain.repository.order;

import com.zerobase.zogi_o_ddae.domain.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
