package com.zerobase.leisure.domain.repository.order;

import com.zerobase.leisure.domain.entity.order.LeisureCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureCartRepository extends JpaRepository<LeisureCart, Long> {
	Optional<LeisureCart> findByCustomerId(Long customerId);

	void deleteByCustomerId(Long customerId);
}
