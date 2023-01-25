package com.zerobase.leisure.domain.entity.leisure;

import com.zerobase.leisure.domain.entity.common.BaseEntity;
import com.zerobase.leisure.domain.form.LeisureForm;
import com.zerobase.leisure.domain.type.Category;
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
public class Leisure extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long sellerId;

	private Category category;

	private String leisureName;
	private String addr;
	private Integer price;
	private String pictureUrl;

	private String description;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;

	public static Leisure of(Long sellerId, LeisureForm form) {
		return Leisure.builder()
			.sellerId(sellerId)
			.leisureName(form.getName())
			.addr(form.getAddr())
			.price(form.getPrice())
			.description(form.getDescription())
			.pictureUrl(form.getPictureUrl())
			.minPerson(form.getMinPerson())
			.maxPerson(form.getMaxPerson())
			.lat(form.getLat())
			.lon(form.getLon())
			.build();
	}
}
