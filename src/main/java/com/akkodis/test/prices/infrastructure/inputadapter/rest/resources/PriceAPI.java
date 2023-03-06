package com.akkodis.test.prices.infrastructure.inputadapter.rest.resources;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.akkodis.test.prices.domain.exception.PriceException;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.dto.ResponseProductPriceDto;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.error.RestException;
import com.akkodis.test.prices.infrastructure.inputport.PriceInputPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Price", description = "Operaciones sobre los precios de los productos de un determinado grupo ")
@RestController
@CrossOrigin()
@RequestMapping(value = "api/price")
public class PriceAPI {

	@Autowired
	PriceInputPort priceService;
		
	@GetMapping(value = "v0/product-price", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Obtiene el precio de un producto para una fecha determinada", method = "getProductPrice")
	public ResponseEntity<ResponseProductPriceDto> getProductPrice(
			@Parameter(required = true, example = "1", description = "Foreign key de la cadena del grupo (1 = ZARA)") @RequestParam(required = true) Integer brandId, 
			@Parameter(required = true, example = "2020-12-31T23:59:59+01:00", description = "Fecha de aplicación para el cálculo del precio del producto") @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime applicationDate, 
			@Parameter(required = true, example = "35455", description = "Identificador del producto") @RequestParam(required = true) Integer productId,
			HttpServletRequest request) throws RestException {
		
		final String requestMappingOperation = new AntPathMatcher().extractPathWithinPattern(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString(),request.getRequestURI());
		try {
			return ResponseEntity.ok(priceService.getProductPrice(brandId, applicationDate, productId));

		} catch (final PriceException e) {
			throw new RestException(HttpStatus.BAD_REQUEST, e, requestMappingOperation);
		}
	}
}
