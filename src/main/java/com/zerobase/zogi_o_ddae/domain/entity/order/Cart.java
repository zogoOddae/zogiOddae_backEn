package com.zerobase.zogi_o_ddae.domain.entity.order;

import com.zerobase.zogi_o_ddae.domain.entity.common.BaseEntity;
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
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Cart extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long customerId;
	private Long sellerId;

	//장바구니 테이블도 나눠야 할까요??
	private Long leisureId;
	private Long accommodationId;

	//카테고리 필요할까요?
	private Long categoryId;

	//장바구니에서는 확인 할 필요 없을 것 같습니다..!
	private Long leisureBlackListId;
	private Long accommodationBlackListId;

	//장바구니에 넣어놨습니다 예약일, 예약 시간! 주문테이블에도 넣어야 할지 모르겠네요..?
	private LocalDate reservationDay;
	private LocalDateTime reservationTime;

	private Integer price;
	private Integer persons;
}
