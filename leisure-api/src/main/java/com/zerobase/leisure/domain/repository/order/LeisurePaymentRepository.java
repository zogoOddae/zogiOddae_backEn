package com.zerobase.leisure.domain.repository.order;

import com.zerobase.leisure.domain.entity.order.LeisurePayment;
import com.zerobase.leisure.domain.type.PaymentStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisurePaymentRepository extends JpaRepository<LeisurePayment, Long> {

	Optional<LeisurePayment> findByTid(String paymentToken);

	Page<LeisurePayment> findAllByCustomerIdAndStatus(Long customerId, PaymentStatus paid, Pageable limit);
}
