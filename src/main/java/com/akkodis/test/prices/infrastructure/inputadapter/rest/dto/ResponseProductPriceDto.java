package com.akkodis.test.prices.infrastructure.inputadapter.rest.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder	
@Getter 
@Setter
public class ResponseProductPriceDto {
	private Integer productId;
	private Integer brandId;
	private BigDecimal price;
	private String curr;
	private Integer priceList;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
}
