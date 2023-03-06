package com.akkodis.test.prices.infrastructure.outputport;

import java.util.List;

import com.akkodis.test.prices.domain.Price;

public interface PriceRepositoryPort {
		
		public List<Price> getProductPrice(Integer brand, Integer productId);
}
