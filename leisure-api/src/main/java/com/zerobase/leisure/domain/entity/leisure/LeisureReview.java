package com.zerobase.leisure.domain.entity.leisure;

import com.zerobase.leisure.domain.entity.common.BaseEntity;
import com.zerobase.leisure.domain.form.AddLeisureReviewForm;
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
public class LeisureReview extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long customerId;
	private Long sellerId;

	private Long leisureId;
	private String description;

	private double rating;

	private String reply;

	public static LeisureReview of(AddLeisureReviewForm form) {
		return LeisureReview.builder()
			.customerId(form.getCustomerId())
			.sellerId(form.getSellerId())
			.leisureId(form.getProductId())
			.rating(form.getRating())
			.description(form.getDescription())
			.reply(form.getReply())
			.build();
	}
}
