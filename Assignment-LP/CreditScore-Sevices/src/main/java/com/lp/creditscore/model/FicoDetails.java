package com.lp.creditscore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table("fico_details")
public class FicoDetails {

	@Id
	@Column("id")
	private Long id;
	@Column("ficoScore")
	private Long ficoScore;
	@Column("creditPullDate")
	private String creditPullDate;
	@Column("scoreVersion")
	private String scoreVersion;
	@Column("cra")
	private String cra;
	
}
