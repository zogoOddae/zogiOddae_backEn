package com.zerobase.accomodation.domain.entity.coupon;

import com.zerobase.accomodation.domain.entity.common.BaseEntity;
import com.zerobase.accomodation.domain.form.AddAccomodationCouponForm;
import com.zerobase.accomodation.domain.form.AddAccomodationCouponGroupForm;
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
public class AccomodationCoupon extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long customerId;
	private Long couponGroupId;

	private boolean usedYN;

	private LocalDateTime usedTime;

	private LocalDateTime endTime;

}
