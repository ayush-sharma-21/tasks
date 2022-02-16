package com.lp.creditscore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("score_factor_dto")
public class ScoreFactor {
	
	@Id
	@Column("id")
	private Long id;
	@Column("tuCode")
	private int tuCode;
	@Column("exCode")
	private int exCode;
	@Column("reasonStatement")
	private String reasonStatement;
	@Column("keepInMind")
	private String keepInMind;
	@Column("fullDescription")
	private String fullDescription;
	@Column("ficoId")
	private Long ficoId;

}
