package com.akkodis.test.prices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.akkodis.test.prices.domain.exception.PriceException;
import com.akkodis.test.prices.domain.services.SalesService;
import com.akkodis.test.prices.infrastructure.config.spring.SpringBootService;
import com.akkodis.test.prices.infrastructure.inputadapter.rest.dto.ResponseProductPriceDto;

@DisplayName("Pruebas unitarias sobre el API Rest")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes= SpringBootService.class)
class PricesApiTests {
	
    private static final Logger logger = LogManager.getLogger(PricesApiTests.class);

    @Autowired
	private WebTestClient client;
    
	private static final Integer PRODUCT_ID = 35455;
	private static final OffsetDateTime DATE_APPLICATION_PRODUCT_EXISTENT  = OffsetDateTime.parse("2020-06-15T00:01:00+02:00");
	
	private static final Integer BRAND_ID_NON_EXISTEN = 99;
	private static final Integer BRAND_ID = 1;
	private static final Integer PRODUCT_ID_NON_EXISTENT = 999999;
	private static final OffsetDateTime DATE_APPLICATION_PRODUCT_NON_EXISTENT  = OffsetDateTime.parse("2000-01-01T00:00:00+01:00");

    
	@ParameterizedTest(name = "Petición el {1} para el producto {2} y la brand {0}")
	@CsvFileSource(resources = {"/prices.csv"}, delimiter = ';', numLinesToSkip = 1)
	@DisplayName("Pruebas con los casos de uso indicados en la definidión del ejercicio de AKKODIS.")
	void productPriceTest(Integer brandId, OffsetDateTime applicationDate, Integer productId, Integer priceList, BigDecimal price, String curr, OffsetDateTime startDate, OffsetDateTime endDate) {
		logger.info("##### Start productPriceTest #####");
		        	
		client.get().uri(uriBuilder -> uriBuilder
			    .path("/api/price/v0/product-price")
			    .queryParam("brandId", "{brandId}")
			    .queryParam("applicationDate", "{applicationDate}")
			    .queryParam("productId", "{productId}")
			    .build(brandId, applicationDate, productId)).exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(ResponseProductPriceDto.class)
        .consumeWith(response -> {
        	ResponseProductPriceDto applicationPrice = response.getResponseBody();
        	assertNotNull(applicationPrice, () -> "No se ha encontrado el precio del producto para la fecha de aplicación indicada.");
    		assertEquals(applicationPrice.getBrandId(), brandId,  () -> "La cadena del grupo del precio devuelto (%s) no es la misma que la cadena solicitada (%s).".formatted(applicationPrice.getBrandId(), brandId)); 
    		assertEquals(applicationPrice.getProductId(), productId,  () -> "El identificador del producto devuelto (%s) no es el mismo que el identificador del producto solicitado (%s).".formatted(applicationPrice.getProductId(), productId));
    		assertEquals(applicationPrice.getPriceList(), priceList,  () -> "El identificador de tarifa devuelto (%s) no corresponde con el esperado (%s).".formatted(priceList, applicationPrice.getPriceList()));
    		assertEquals(applicationPrice.getPrice(), price,  () -> "El precio del producto devuelto (%s) no corresponde con el precio esperado (%s).".formatted(price, applicationPrice.getPrice()));
    		assertEquals(applicationPrice.getCurr(), curr,  () -> "La moneda del precio devuelta (%s) no corresponde con la esperada (%s).".formatted(curr, applicationPrice.getCurr()));
        });
		
		logger.info("##### End productPriceTest #####");
	}

	@Test
	@DisplayName("Prueba con una fecha de aplicación en la que no existe el producto.")
	void productPriceBeforeDateDisponibilityTest() {
		logger.info("##### Start productPriceBeforeDateDisponibilityTest #####");
		
		client.get().uri(uriBuilder -> uriBuilder
			    .path("/api/price/v0/product-price")
			    .queryParam("brandId", "{brandId}")
			    .queryParam("applicationDate", "{applicationDate}")
			    .queryParam("productId", "{productId}")
			    .build(BRAND_ID, DATE_APPLICATION_PRODUCT_NON_EXISTENT, PRODUCT_ID)).exchange()
        .expectStatus().is4xxClientError()
        .expectBody(String.class).consumeWith(result -> {
            assertThat(result.getResponseBody()).contains("No existen precios definidos para la fecha de aplicación indicada.");
          });
		
		logger.info("##### End productPriceBeforeDateDisponibilityTest #####");
	}
	

	@Test
	@DisplayName("Prueba con un producto que no existe.")
	void productNotExistTest() {
		logger.info("##### Start productNotExistTest #####");

		client.get().uri(uriBuilder -> uriBuilder
			    .path("/api/price/v0/product-price")
			    .queryParam("brandId", "{brandId}")
			    .queryParam("applicationDate", "{applicationDate}")
			    .queryParam("productId", "{productId}")
			    .build(BRAND_ID, DATE_APPLICATION_PRODUCT_EXISTENT, PRODUCT_ID_NON_EXISTENT)).exchange()
        .expectStatus().is4xxClientError()
        .expectBody(String.class).consumeWith(result -> {
            assertThat(result.getResponseBody()).contains("No existen precios definidos para el brandId y productId indicados.");
          });
		
		logger.info("##### End productNotExistTest #####");
	}
	
	
	
	@Test
	@DisplayName("Prueba con una cadena del grupo que no existe.")
	void brandNotExistTest() {
		logger.info("##### Start brandNotExistTest #####");
				
		client.get().uri(uriBuilder -> uriBuilder
			    .path("/api/price/v0/product-price")
			    .queryParam("brandId", "{brandId}")
			    .queryParam("applicationDate", "{applicationDate}")
			    .queryParam("productId", "{productId}")
			    .build(BRAND_ID_NON_EXISTEN, DATE_APPLICATION_PRODUCT_EXISTENT, PRODUCT_ID)).exchange()
        .expectStatus().is4xxClientError()
        .expectBody(String.class).consumeWith(result -> {
            assertThat(result.getResponseBody()).contains("No existen precios definidos para el brandId y productId indicados.");
          });
		
		logger.info("##### End brandNotExistTest #####");
	}
	

	@Test
	@DisplayName("Prueba con los campos obligatorios a nulos.")
	void requeryParamWhitOutTest() {
			
		logger.info("##### Start requeryParamWhitOutTest #####");
				
		client.get().uri(uriBuilder -> uriBuilder
			    .path("/api/price/v0/product-price")
			    .queryParam("applicationDate", "{applicationDate}")
			    .queryParam("productId", "{productId}")
			    .build(DATE_APPLICATION_PRODUCT_EXISTENT, PRODUCT_ID)).exchange()
        .expectStatus().is4xxClientError()
        .expectBody(String.class).consumeWith(result -> {
            assertThat(result.getResponseBody()).contains("Bad Request");
          });
		
		client.get().uri(uriBuilder -> uriBuilder
			    .path("/api/price/v0/product-price")
			    .queryParam("brandId", "{brandId}")
			    .queryParam("productId", "{productId}")
			    .build(BRAND_ID, PRODUCT_ID)).exchange()
        .expectStatus().is4xxClientError()
        .expectBody(String.class).consumeWith(result -> {
        	assertThat(result.getResponseBody()).contains("Bad Request");
          });
		
		client.get().uri(uriBuilder -> uriBuilder
			    .path("/api/price/v0/product-price")
			    .queryParam("brandId", "{brandId}")
			    .queryParam("applicationDate", "{applicationDate}")
			    .build(BRAND_ID, DATE_APPLICATION_PRODUCT_EXISTENT)).exchange()
        .expectStatus().is4xxClientError()
        .expectBody(String.class).consumeWith(result -> {
        	assertThat(result.getResponseBody()).contains("Bad Request");
          });
		
		logger.info("##### End requeryParamWhitOutTest #####");
	}	
	
/*
	@Disabled
	@Test
	@DisplayName("Prueba con una fecha que tiene un formato erroneo.")
	void dateTimeFormatErrorTest() {
		logger.info("##### Start dateTimeFormatErrorTest #####");
		
		logger.info("##### End dateTimeFormatErrorTest #####");
	}*/
}
