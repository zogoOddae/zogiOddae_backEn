package com.zerobase.accommodation.domain.repository.payment;

import com.zerobase.accommodation.domain.entity.payment.AccommodationPayment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPaymentRepository extends JpaRepository<AccommodationPayment, Long> {

    Optional<AccommodationPayment> findByTid(String paymentToken);
}
