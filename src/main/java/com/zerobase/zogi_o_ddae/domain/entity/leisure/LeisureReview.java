package com.zerobase.zogi_o_ddae.domain.entity.leisure;

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
public class LeisureReview extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long customerId;
	private Long sellerId;

	private Long leisureId;

	//카테고리 필요할지?
	private Long categoryId;

	//요건 필요없을거 같네요?? 블랙리스트라면 이용을 못할테니..!
	private Long leisureBlackListId;

	private double rating;
	private double description;

	private String reply;
}
