package com.mongo.example.mongodemo.models;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongo.example.mongodemo.repo.CarInterface;
import com.mongo.example.mongodemo.repo.CarRepository;
import com.mongodb.client.result.UpdateResult;

@Service
public class CarHandler implements CarInterface {
	@Override
	public RestCar incSlider(int id, RestCar restCar) {
	      return restCar;
	}	
}
