package com.zerobase.zogi_o_ddae.domain.repository.order;

import com.zerobase.zogi_o_ddae.domain.entity.order.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
