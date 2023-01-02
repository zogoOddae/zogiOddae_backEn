package com.zerobase.zogi_o_ddae.domain.entity.common;

import com.zerobase.zogi_o_ddae.domain.entity.accommodation.Accommodation;
import com.zerobase.zogi_o_ddae.domain.entity.leisure.Leisure;
import java.util.ArrayList;
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
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Category extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//리스트로 연결
	private Integer leisureList;
	//리스트로 연결
	private Integer accommodationList;

	private String categoryName;
}
