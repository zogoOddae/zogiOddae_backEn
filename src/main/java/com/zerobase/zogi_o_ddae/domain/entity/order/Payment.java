package com.zerobase.zogi_o_ddae.domain.entity.order;

import com.zerobase.zogi_o_ddae.domain.entity.common.BaseEntity;
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
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Payment extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//아예 유저 테이블도 customer 랑 seller 로 나눌까요..?
	private Long customerId;
	private Long sellerId;

	//레저 아이디만 있는데, 레저랑 숙박이 따로라 결제도 따로 해야할지..?
	private Long leisureId;
	private Long accommodationId;

	//카테고리 아이디가 필요할지??
	private Long categoryId;

	private Integer price;
	private String paymentToken;
}
