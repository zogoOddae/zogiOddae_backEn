package com.zerobase.zogi_o_ddae.domain.repository.order;

import com.zerobase.zogi_o_ddae.domain.entity.order.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
