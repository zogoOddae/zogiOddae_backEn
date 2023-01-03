package com.zerobase.leisure.domain.entity.order;

import com.zerobase.leisure.domain.entity.common.BaseEntity;
import com.zerobase.leisure.domain.type.OrderStatus;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;

@Entity(name = "orderTable")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Order extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long customerId;
	private Long sellerId;

	@OneToMany
	private List<OrderItem> items;

	private Long leisureId;

	private Long couponId;

	private Integer price;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
}
