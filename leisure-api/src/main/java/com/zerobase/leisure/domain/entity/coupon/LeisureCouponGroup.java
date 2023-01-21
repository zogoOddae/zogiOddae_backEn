package com.zerobase.leisure.domain.entity.coupon;

import com.zerobase.leisure.domain.entity.common.BaseEntity;
import com.zerobase.leisure.domain.form.AddLeisureCouponGroupForm;
import com.zerobase.leisure.domain.type.CouponTarget;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class LeisureCouponGroup extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer salePrice;

	@Enumerated(EnumType.STRING)
	private CouponTarget couponTarget;
	private Integer issuedCount;

	private LocalDate endTime;

	public static LeisureCouponGroup of(AddLeisureCouponGroupForm form) {
		return LeisureCouponGroup.builder()
			.salePrice(form.getSalePrice())
			.couponTarget(form.getCouponTarget())
			.issuedCount(form.getIssuedCount())
			.endTime(form.getEndTime())
			.build();
	}
}
