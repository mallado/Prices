package com.akkodis.test.prices.infrastructure.inputadapter.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto implements Serializable{
	
	private static final long serialVersionUID = -2697898889590875916L;
	
	@JsonProperty
	private Integer productId;
	@JsonProperty
	private Integer brandId;
	@JsonProperty
	private Integer priceList;
	@JsonProperty
	private OffsetDateTime startDate;
	@JsonProperty
	private OffsetDateTime endDate;
	@JsonProperty
	private BigDecimal price;
	@JsonProperty
	private String curr;
}
