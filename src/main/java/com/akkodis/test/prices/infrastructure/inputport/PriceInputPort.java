package com.akkodis.test.prices.infrastructure.inputport;

import java.time.OffsetDateTime;

import com.akkodis.test.prices.infrastructure.inputadapter.rest.dto.ResponseProductPriceDto;

public interface PriceInputPort {
	
	public ResponseProductPriceDto getProductPrice(Integer brandId, OffsetDateTime applicationDate, Integer productId);
}
