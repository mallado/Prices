package com.akkodis.test.prices.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor	
@Getter 
@Setter
public class Price {

	private Integer id;
	private Integer brandId;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private Integer priceList;
	private Integer productId;
	private Integer priority;
	private BigDecimal price;
	private String curr;
	
}
