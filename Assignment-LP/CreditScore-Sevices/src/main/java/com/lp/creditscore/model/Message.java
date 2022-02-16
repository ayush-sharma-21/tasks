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
@Table("message")
public class Message {

	@Id
	@Column("id")
	private Long id;
	@Column("min")
	private Long min;
	@Column("max")
	private Long max;
	@Column("is_score_factor_availavle")
	private Long is_score_factor_availavle;
	@Column("message")
	private String message;
	@Column("display_image")
	private Long display_image;
}
