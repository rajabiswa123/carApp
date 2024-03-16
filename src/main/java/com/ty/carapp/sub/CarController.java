package com.ty.carapp.sub;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Car")
public class CarController {
	@Autowired
	private CarRepository carRepository;

	@PostMapping("/savecar")
	public ResponseEntity<ResponseStructure<Car>> saveCar(@RequestBody Car c) {
		ResponseStructure<Car> responseStructure =  new ResponseStructure<Car>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("inserted");
		Car responseCar = carRepository.save(c);
		responseStructure.setData(responseCar);
		return new ResponseEntity<>(responseStructure,HttpStatus.CREATED );
	}

	@GetMapping("/findbyId")
	public ResponseEntity<ResponseStructure<Car>> findCarById(@PathVariable int carId) {
		Optional<Car> optional = carRepository.findById(carId);
		if (optional.isPresent()) {
			ResponseStructure<Car> responseStructure= new ResponseStructure<Car>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(optional.get());
			
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure,HttpStatus.OK);
		}
		return null;
	}

	@DeleteMapping("/delete")
	public String deleteCar(@RequestParam int carId) {
		Optional<Car> optional = carRepository.findById(carId);
		if (optional.isPresent()) {
			ResponseStructure<Car> responseStructure= new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.MOVED_PERMANENTLY.value());
			responseStructure.setMessage("deleted");
			responseStructure.setData(optional.get());
			// carRepository.delete(optional.get());
			carRepository.deleteById(carId);
			return "deleted";
		}
		return "id not  found";
	}
	@PostMapping("/savelistofcar")
	public List<Car> postMethodName(@RequestBody List<Car> cars) {
		System.out.println(cars);
		//step1
		//for(Car car:cars){
//		carRepository.save(car);
//	}
		return carRepository.saveAll(cars);
	}
	@PatchMapping("/update/carprice")
	public Car updateCarPrice(@RequestParam int carId,@RequestParam double price) {
		Optional<Car> optional =  carRepository.findById(carId);
		if(optional.isPresent()) {
			Car car = optional.get();
			car.setPrice(65767);
			return carRepository.save(car);
		}
		else {
			return null;
		}
	}
	@GetMapping("/findall")
	public List<Car> findAll(){
		return carRepository.findAll();
	}
	
	//find the id by path variable
	@GetMapping("/findbyId/{carId}")
	public Car findCarByIdByPathVariable(@PathVariable int carId) {
		Optional<Car> optional = carRepository.findById(carId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	//find car by brand and price
	
	@PostMapping("/find/bybrandprice")
	public Car carLogin(@RequestParam String carbrand,@RequestParam double carPrice) {
		return carRepository.findCarByBrandAndPrice(carbrand,carPrice);
	}
	
	
	//find by brand
	@GetMapping("/find/bybrand")
	public Car FindByBrand(@RequestParam String carbrand) {
		return carRepository.findCarByBrand(carbrand);
	}
	
	
	public void m1() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
