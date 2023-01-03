package com.zerobase.leisure.domain.entity.leisure;

import com.zerobase.leisure.domain.entity.common.BaseEntity;
import com.zerobase.leisure.domain.entity.common.BlackList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
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

	@OneToMany
	private List<BlackList> blackListId;

	private String name;
	private String addr;
	private Integer price;
	private String pictureUrl;

	private String description;

	private LocalDate dayOff;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;
}
