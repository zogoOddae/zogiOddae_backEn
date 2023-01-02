package com.zerobase.zogi_o_ddae.domain.entity.accommodation;

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
public class Accommodation extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long sellerId;

	//여기에 블랙리스트 테이블을 연결하는게?
	private Long blackListId;

	//이러면 하나의 시설이 하나의 카테고리 밖에 못가지는데, N:M으로 연결하려면 테이블 하나가 더 필요할듯..? 어떻게 할까용
	//모르겠네용
	private Integer categoryId;

	private String name;
	private Integer price;
	private String pictureUrl;

	private LocalDate dayOff;

	private String description;

	private Integer minPerson;
	private Integer maxPerson;

	private double lat;
	private double lon;
}
