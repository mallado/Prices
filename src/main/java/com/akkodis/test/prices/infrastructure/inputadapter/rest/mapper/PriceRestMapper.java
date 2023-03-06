package com.akkodis.test.prices.infrastructure.inputadapter.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.akkodis.test.prices.domain.Price;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.dto.PriceDto;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.dto.ResponseProductPriceDto;

@Mapper(componentModel = "spring")
public interface PriceRestMapper {

	PriceRestMapper INSTANCE = Mappers.getMapper(PriceRestMapper.class);
	
	PriceDto toDto (Price price);

	Price toDomain(PriceDto priceDto);
		
	ResponseProductPriceDto toResponseProductPriceDto (Price price);
	
}