package com.zerobase.zogi_o_ddae.domain.entity.leisure;

import com.zerobase.zogi_o_ddae.domain.entity.common.BaseEntity;
import com.zerobase.zogi_o_ddae.domain.entity.common.Category;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.joda.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Leisure extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long sellerId;
	private Long blackListId;

	private Integer categoryId;

	private String name;
	private Integer price;
	private String pictureUrl;

	private String description;

	private LocalDate dayOff;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;
}
