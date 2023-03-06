package com.akkodis.test.prices.infrastructure.outputadapter.db.springdata.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.akkodis.test.prices.domain.Price;
import com.akkodis.test.prices.infrastructure.outputadapter.db.springdata.dbo.PriceEntity;
import com.akkodis.test.prices.infrastructure.outputadapter.db.springdata.mapper.PriceEntityMapper;
import com.akkodis.test.prices.infrastructure.outputport.PriceRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PriceDboRepository implements PriceRepositoryPort {

  @Autowired
  private SpringDataPriceRepository priceRepository;

  @Autowired
  private PriceEntityMapper priceEntityMapper;

  @Override
  public List<Price> getProductPrice(Integer brand, Integer productId) {
	  
	  PriceEntity priceProductFilter = new PriceEntity();
	  priceProductFilter.setBrandId(brand);
	  priceProductFilter.setProductId(productId);
	  Example<PriceEntity> example = Example.of(priceProductFilter);
	  return priceEntityMapper.toDomainList(priceRepository.findAll(example));
  }

}