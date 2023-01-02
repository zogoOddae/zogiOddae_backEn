package com.zerobase.zogi_o_ddae.domain.entity.coupon;

import com.zerobase.zogi_o_ddae.domain.entity.common.BaseEntity;
import com.zerobase.zogi_o_ddae.domain.type.CouponTarget;
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
public class CouponGroup extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long salePrice;
	private CouponTarget couponTarget;
	private Integer count;
}
