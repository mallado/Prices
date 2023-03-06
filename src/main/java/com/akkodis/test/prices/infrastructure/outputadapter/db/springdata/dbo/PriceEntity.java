package com.akkodis.test.prices.infrastructure.outputadapter.db.springdata.dbo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="prices")
public class PriceEntity {

	@Id
	private Integer id;
	
	@Column(nullable = false)
	private Integer brandId;
	
	@Column(nullable = false)
	private OffsetDateTime startDate;
	
	@Column(nullable = false)
	private OffsetDateTime endDate;
	
	@Column(nullable = false)
	private Integer priceList;
	
	@Column(nullable = false)
	private Integer productId;
	
	@Column(nullable = false)
	private Integer priority;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private String curr;
}
