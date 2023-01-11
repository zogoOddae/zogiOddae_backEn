package com.zerobase.leisure.domain.entity.leisure;

import com.zerobase.leisure.domain.entity.common.BaseEntity;
import com.zerobase.leisure.domain.entity.common.LeisureBlackList;
import com.zerobase.leisure.domain.form.AddLeisureForm;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
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
	private List<LeisureBlackList> leisureBlackListId = new ArrayList<>();

	private String leisureName;
	private String addr;
	private Integer price;
	private String pictureUrl;

	private String description;

	@ElementCollection
	private List<LocalDate> dayOff = new ArrayList<>();

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;

	public static Leisure of(Long sellerId, AddLeisureForm form) {
		return Leisure.builder()
			.sellerId(sellerId)
			.leisureName(form.getName())
			.addr(form.getAddr())
			.price(form.getPrice())
			.description(form.getDescription())
			.minPerson(form.getMinPerson())
			.maxPerson(form.getMaxPerson())
			.build();
	}
}
