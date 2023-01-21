package com.zerobase.leisure.domain.repository.order;

import com.zerobase.leisure.domain.entity.order.LeisureOrder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureOrderRepository extends JpaRepository<LeisureOrder, Long> {
	Optional<LeisureOrder> findByCustomerIdAndLeisurePaymentId(Long customerId, Long leisurePaymentId);

	List<LeisureOrder> findByCustomerId(Long customerId);
}
