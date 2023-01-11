package com.zerobase.accomodation.domain.entity.coupon;

import com.zerobase.accomodation.domain.entity.common.BaseEntity;
import com.zerobase.accomodation.domain.form.AddAccomodationCouponGroupForm;
import com.zerobase.accomodation.domain.type.CouponTarget;
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
public class AccomodationCouponGroup extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long salePrice;
	private CouponTarget couponTarget;
	private Integer issusedcount;

	private LocalDateTime endTime;

	public static AccomodationCouponGroup of(AddAccomodationCouponGroupForm form){
		return AccomodationCouponGroup.builder()
			.id(form.getAccomodationCouponGroupid())
			.salePrice(form.getSalePrice())
			.couponTarget(form.getCouponTarget())
			.issusedcount(form.getIssusedcount())
			.endTime(form.getEndTime())
			.build();
	}

}
