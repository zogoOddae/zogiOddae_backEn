package com.zerobase.accomodation.domain.entity.order;

import com.zerobase.accomodation.domain.entity.common.BaseEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class AccomodationCart extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long customerId;
	private Long sellerId;

	private Long accommodationId;

	private LocalDate reservationDay;
	private LocalDateTime reservationTime;

	private Integer price;
	private Integer persons;
}
