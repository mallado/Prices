package com.akkodis.test.prices.application;

import java.time.OffsetDateTime;

import com.akkodis.test.prices.domain.services.SalesService;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.dto.ResponseProductPriceDto;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.mapper.PriceRestMapper;
import com.akkodis.test.prices.infrastructure.inputport.PriceInputPort;
import com.akkodis.test.prices.infrastructure.outputport.PriceRepositoryPort;

/**
 * Servicio de la capa de aplicación encargado de implementar el puerto de
 * entrada del API Rest.
 * 
 * @author fmallado
 * @since 1.0.0
 */
public class PriceService implements PriceInputPort {

	/**
	 * Repositorio utilizado como conector con la BBDD
	 */
	private final PriceRepositoryPort entityRepository;

	/**
	 * Mapper usado para realizar los mapeos entre los objetos usados por los
	 * puertos de entrada y los objetos del dominio.
	 */
	private final PriceRestMapper priceRestMapper;

	/**
	 * Constructor del servicio
	 * 
	 * @param entityRepository Repositorio utilizado como conector con la BBDD
	 * @param priceRestMapper  Mapper usado para realizar los mapeos entre los
	 *                         objetos usados por los puertos de entrada y los
	 *                         objetos del dominio.
	 */
	public PriceService(PriceRepositoryPort entityRepository, PriceRestMapper priceRestMapper) {
		this.entityRepository = entityRepository;
		this.priceRestMapper = priceRestMapper;
	}

	@Override
	public ResponseProductPriceDto getProductPrice(Integer brandId, OffsetDateTime applicationDate, Integer productId) {
		return priceRestMapper.toResponseProductPriceDto(SalesService.getProductPrice(brandId, applicationDate,
				productId, entityRepository.getProductPrice(brandId, productId)));
	}
}
