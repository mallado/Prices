package com.akkodis.test.prices.application;

import java.time.OffsetDateTime;

import com.akkodis.test.prices.domain.services.SalesService;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.dto.ResponseProductPriceDto;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.mapper.PriceRestMapper;
import com.akkodis.test.prices.infrastructure.inputport.PriceInputPort;
import com.akkodis.test.prices.infrastructure.outputport.PriceRepositoryPort;


public class PriceService implements PriceInputPort {

	private final PriceRepositoryPort entityRepository;
	private final PriceRestMapper priceRestMapper;
	
	public PriceService(PriceRepositoryPort entityRepository, PriceRestMapper priceRestMapper) {
		this.entityRepository = entityRepository;
		this.priceRestMapper = priceRestMapper;
	}
	
	@Override
	public ResponseProductPriceDto getProductPrice(Integer brandId, OffsetDateTime applicationDate, Integer productId) {
		return priceRestMapper.toResponseProductPriceDto(SalesService.getProductPrice(brandId, applicationDate, productId, entityRepository.getProductPrice(brandId, productId)));
	}


}
