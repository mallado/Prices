package com.akkodis.test.prices.infrastructure.outputadapter.db.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akkodis.test.prices.infrastructure.outputadapter.db.springdata.dbo.PriceEntity;

@Repository
public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Integer> 
{

}
