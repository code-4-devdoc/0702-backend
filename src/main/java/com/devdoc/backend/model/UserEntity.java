package com.devdoc.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사용자 정보를 저장하고 관리하는 모델")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Schema(description = "사용자 고유 ID", example = "1")
	private String id; // 사용자 고유 ID

	@Column(nullable = false)
	@Schema(description = "사용자 닉네임", example = "mynickname")
	private String username; // 사용자 닉네임

	@Column(nullable = false)
	@Schema(description = "사용자 이메일", example = "example1@example.com")
	private String email; // 사용자 이메일

	@Column(nullable = false)
	@Schema(description = "사용자 비밀번호", example = "mypassword11!")
	private String password; // 사용자 비밀번호

	@Schema(description = "생성일자", example = "2024-07-05T10:15:30")
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Schema(description = "사용자와 연결된 이력서 목록")
	private List<Resume> resumes; // 사용자와 연결된 이력서 목록

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now(); // 엔티티가 생성될 때 자동으로 현재 시간이 설정됨
	}
}



