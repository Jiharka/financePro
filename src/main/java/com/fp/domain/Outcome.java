package com.fp.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@NoArgsConstructor
public class Outcome {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private BigDecimal planned;

	@Column(nullable = false)
	private BigDecimal fact;
	
	@ManyToOne//(cascade = CascadeType.ALL)
	private Category category;

	@Column
	private String comments;

}