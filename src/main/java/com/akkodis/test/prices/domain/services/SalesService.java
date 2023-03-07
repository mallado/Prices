package com.akkodis.test.prices.domain.services;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

import com.akkodis.test.prices.domain.Price;
import com.akkodis.test.prices.domain.exception.PriceException;

/**
 * Servicio encargado de realizar las operaciones de venta para los productos
 * 
 * @author fmallado
 * @since 1.0.0
 */
public class SalesService {

	private SalesService() {
		throw new IllegalStateException("Clase de utilidades.");
	}

	/**
	 * Método que calcula el precio de venta que hay que aplicar a un producto.
	 * 
	 * @param brandId          Identifcador de la cadena del grupo.
	 * @param applicationDate  Fecha de aplicación del precio al producto.
	 * @param productId        Identificador del producto.
	 * @param productPriceList Lista de precios disponibles para el producto.
	 * @return Precio de venta.
	 * @throws PriceException Excepción de dominio.
	 */
	public static Price getProductPrice(Integer brandId, OffsetDateTime applicationDate, Integer productId,
			List<Price> productPriceList) throws PriceException {
		validations(brandId, applicationDate, productId, productPriceList);

		// Filtramos la lista por brandId y productos
		List<Price> productPricesByBrandId = productPriceList.stream()
				.filter(price -> price.getBrandId().equals(brandId) && price.getProductId().equals(productId)).toList();
		if (productPricesByBrandId.isEmpty())
			throw new PriceException("No existen precios definidos para el brandId y productId indicados.");

		// Verificamos si existen precios para la fecha de aplicación indicada.
		List<Price> pricesByApplicationDate = productPricesByBrandId.stream().filter(
				price -> price.getStartDate().isBefore(applicationDate) && price.getEndDate().isAfter(applicationDate))
				.sorted(Comparator.comparingInt(Price::getPriority).reversed()).toList();
		if (pricesByApplicationDate.isEmpty())
			throw new PriceException("No existen precios definidos para la fecha de aplicación indicada.");

		// Ordenamos los precios vigentes por prioridad y nos quedamos con el más
		// prioritario.
		List<Price> pricesByApplicationDateOrderByPriority = pricesByApplicationDate.stream()
				.sorted(Comparator.comparingInt(Price::getPriority).reversed()).toList();
		return pricesByApplicationDateOrderByPriority.get(0);
	}

	/**
	 * Méodo de validación de los parámetros de entrada al método getProductPrice
	 * 
	 * @param brandId          Identifcador de la cadena del grupo.
	 * @param applicationDate  Fecha de aplicación del precio al producto.
	 * @param productId        Identificador del producto.
	 * @param productPriceList Lista de precios disponibles para el producto.
	 * 
	 * @throws IllegalArgumentException
	 * @throws PriceException
	 */
	private static void validations(Integer brandId, OffsetDateTime applicationDate, Integer productId,
			List<Price> productPriceList) throws IllegalArgumentException, PriceException {
		if (brandId == null)
			throw new IllegalArgumentException("El parámetro brandId es obligatorio.");
		if (applicationDate == null)
			throw new IllegalArgumentException("El parámetro applicationDate es obligatorio.");
		if (productId == null)
			throw new IllegalArgumentException("El parámetro productId es obligatorio.");
		if (productPriceList == null)
			throw new IllegalArgumentException("La lista de precios del producto es obligatoria.");
		if (productPriceList.isEmpty())
			throw new PriceException("No existen precios definidos para el brandId y productId indicados.");
	}
}
