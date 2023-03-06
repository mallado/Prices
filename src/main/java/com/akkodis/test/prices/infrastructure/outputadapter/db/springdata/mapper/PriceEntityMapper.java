package com.akkodis.test.prices.infrastructure.outputadapter.db.springdata.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.akkodis.test.prices.domain.Price;
import com.akkodis.test.prices.infrastructure.outputadapter.db.springdata.dbo.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

	PriceEntityMapper INSTANCE = Mappers.getMapper(PriceEntityMapper.class);
	
	Price toDomain(PriceEntity priceEntity);

	PriceEntity toDbo(Price price);

	default List<Price> toDomainList(List<PriceEntity> priceEntityList){
		if(priceEntityList == null) {
			return new ArrayList<>();
		}
		return priceEntityList.stream().map(this::toDomain).toList();
	}
}