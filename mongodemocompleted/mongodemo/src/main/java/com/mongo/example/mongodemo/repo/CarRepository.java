package com.mongo.example.mongodemo.repo;

import java.util.List;

//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mongo.example.mongodemo.models.RestCar;

@Repository
public interface CarRepository extends MongoRepository<RestCar, Integer>{
	
//	@Query("{'_id': ?0},{'$inc': {'sunroofSlider':1}}")
	@Query("{ speed: {$eq: ?0} }")
	public List<RestCar> incSlider(int speed);
//	db.cars.update({ _id:1 },{ $inc: { SunroofSlider: 1 } }, {upsert:true})
}


