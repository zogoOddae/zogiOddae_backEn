package com.zerobase.zogi_o_ddae.domain.entity.order;

import com.zerobase.zogi_o_ddae.domain.entity.common.BaseEntity;
import com.zerobase.zogi_o_ddae.domain.type.OrderStatus;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Entity(name = "orderTable")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Order extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long customerId;
	private Long sellerId;

	//주문 테이블도 나눠야 할까요??
	private Long leisureId;
	private Long accommodationId;

	//카테고리 필요할까요?
	private Long categoryId;

	private Long couponId;

	//레저 블랙리스트랑 숙박 블랙리스트는 따로 테이블 만들어서 주문시에 확인하는게 나을 거 같아요
	private Long leisureBlackListId;
	private Long accommodationBlackListId;

	private Integer price;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
}
