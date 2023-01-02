package com.zerobase.zogi_o_ddae.domain.entity.common;

import com.zerobase.zogi_o_ddae.domain.type.MemberGrant;
import com.zerobase.zogi_o_ddae.domain.type.MemberStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Member extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 개인 정보
	private String name;
	private String email;
	private String password;
	private String phone;

	// 이메일 인증
	private String emailAuthKey;
	private boolean emailAuthYn;


	// 상태, 권한
	@Enumerated(EnumType.STRING)
	private MemberStatus status;

	@Column(name = "grantType")
	@Enumerated(EnumType.STRING)
	private MemberGrant grant;
}
