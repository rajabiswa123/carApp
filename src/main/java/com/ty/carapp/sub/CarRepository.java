package com.ty.carapp.sub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Integer> {
	@Query(value = "select c from Car c where c.brand =?1 and c.price =?2")
	Car findCarByBrandAndPrice(String carbrand, double carPrice);

	Car findCarByBrand(String carbrand);
	//coustom method without using query
	//findEntityByVariablenameAndvariablename
	//Car findCarByBrandAndPrice(String carbrand, double carPrice)
	
}
