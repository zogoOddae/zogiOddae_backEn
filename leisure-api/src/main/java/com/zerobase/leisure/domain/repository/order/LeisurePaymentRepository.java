package com.zerobase.leisure.domain.repository.order;

import com.zerobase.leisure.domain.entity.order.LeisurePayment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisurePaymentRepository extends JpaRepository<LeisurePayment, Long> {

	Optional<LeisurePayment> findByTid(String paymentToken);
}
