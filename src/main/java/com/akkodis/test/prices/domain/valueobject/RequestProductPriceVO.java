package com.akkodis.test.prices.domain.valueobject;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder	
@Getter 
@Setter
public class RequestProductPriceVO {

	
	private Integer brandId;
	private OffsetDateTime applicationDate;
	private Integer productId;
}
