package com.zerobase.accomodation.domain.entity.accomodation;

import com.zerobase.accomodation.domain.entity.common.BaseEntity;
import com.zerobase.accomodation.domain.form.AccomodationForm;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
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
public class Accomodation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long sellerId;

	private Long categoryId;

	// ERD 관계 시 오류 발생 -> 외래키 삭제 오류
	/*
	@OneToMany(mappedBy="accomodation_black_list", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AccomodationBlackList> AccomodationBlackList = new ArrayList<>();
	*/
	@ElementCollection
	private List<Long> AccomodationBlackList = new ArrayList<>();

	private String accomodationName;
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

	public static Accomodation of(Long sellerId, AccomodationForm form) {
		return Accomodation.builder()
			.accomodationName(form.getAccomodationName())
			.sellerId(sellerId)
			.accomodationName(form.getAccomodationName())
			.addr(form.getAddr())
			.price(form.getPrice())
			.description(form.getDescription())
			.minPerson(form.getMinPerson())
			.maxPerson(form.getMaxPerson())
			.build();
	}
}
