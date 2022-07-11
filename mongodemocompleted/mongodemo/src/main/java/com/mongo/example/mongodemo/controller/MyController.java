package com.mongo.example.mongodemo.controller;

import java.io.Console;
import java.lang.reflect.Field;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mongo.example.mongodemo.models.CarHandler;
import com.mongo.example.mongodemo.models.RestCar;
import com.mongo.example.mongodemo.repo.CarInterface;
import com.mongo.example.mongodemo.repo.CarRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

@RestController
@RequestMapping("/car")
public class MyController {

	@Autowired
	private CarRepository carRepo;
	
	// @Autowired 
	// private MongoOperations mongo;
	// @Autowired
	// private CarHandler carHandler;

	@Autowired
	MongoTemplate mongoTemplate;

	private RestCar vw;

	@GetMapping("/speed")
	public ResponseEntity<?> getSpeed() {
		return ResponseEntity.ok(this.carRepo.findAll());
	}
	
	@PostMapping("/addSpeed")
	public ResponseEntity<?> addSpeedtry(@RequestBody RestCar restCar) {
		if(restCar.getSpeed() >= 0 && restCar.getSpeed() <=20)
		{
			restCar.setLevel(1);
		}
		else if(restCar.getSpeed() > 20 && restCar.getSpeed() <= 40) {
			restCar.setLevel(2);
		}
		else if(restCar.getSpeed() > 40 && restCar.getSpeed() <= 60) {
			restCar.setLevel(3);
		}
		else if(restCar.getSpeed() > 60 && restCar.getSpeed() <= 80) {
			restCar.setLevel(4);
		}
		else if(restCar.getSpeed() > 80 && restCar.getSpeed() <= 100) {
			restCar.setLevel(5);
		}
		RestCar save = this.carRepo.insert(restCar);
		return ResponseEntity.ok(save);
	}

	@PutMapping("/doorlock/{id}")
	public String handlingDoorUnlock( @PathVariable("id") int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);

	    if(restCar!=null){
	        Update update=new Update().set("door","LOCK");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}

	@RequestMapping("/doorunlock/{id}")
	public String handlingDoorOff(@PathVariable("id") int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);

	    if(restCar!=null){
	        Update update=new Update().set("door","UNLOCK");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}

	@RequestMapping("/trunkopen/{id}")
	public String handlingTrunkOpen(@PathVariable("id") int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);

	    if(restCar!=null){
	        Update update=new Update().set("trunk","OPEN");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}

	@RequestMapping("/trunkclose/{id}")
	public String handlingTrunkClose(@PathVariable("id") int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);

	    if(restCar!=null){
	        Update update=new Update().set("trunk","CLOSE");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}

	@RequestMapping("/acon/{id}")
	public String handlingACOn(@PathVariable("id") int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);

	    if(restCar!=null){
	        Update update=new Update().set("ac","ON");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}

	@RequestMapping("/acoff/{id}")
	public String handlingACOff(@PathVariable("id") int id) {
		Query query=new Query(Criteria.where("_id").is(id));
	    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);

	    if(restCar!=null){
	        Update update=new Update().set("ac","OFF");
	        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
	        System.out.println("count incre"+result.getModifiedCount());
	    }else{
System.out.println("else");		   
}		    
	return  "success";
	}

	@PutMapping("/actempchange")
	public ResponseEntity<?> AcTempPlus(@RequestBody RestCar restCar) {
		RestCar save = this.carRepo.save(restCar);
		if (restCar.getMinTemp() < 16 && restCar.getMaxTemp() > 30)
			System.out.println("InVlid Tempurature");

		return ResponseEntity.ok(save);
	}

	@PutMapping("/decrsunroofslider/{id}")
	public String DecreSunroofSlider( @PathVariable("id") int id) {
		    Query query=new Query(Criteria.where("_id").is(id));
		    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);

		    if(restCar!=null){
		        Update update=new Update().inc("sunroofSlider",-1);
		        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
		        System.out.println("count decre"+result.getModifiedCount());
		    }else{
System.out.println("else");		   
}		    
		return  "success";
	}

	@PutMapping("/incrsunroofslider/{id}")
	public String IncreSunroofSlider( @PathVariable("id") int id) {

		    Query query=new Query(Criteria.where("_id").is(id));
		    List<RestCar> restCar=mongoTemplate.find(query,RestCar.class);

		    if(restCar!=null){
		        Update update=new Update().inc("sunroofSlider",1);
		        UpdateResult result = mongoTemplate.updateFirst(query,update,RestCar.class);	
		        System.out.println("count incre"+result.getModifiedCount());
		    }else{
System.out.println("else");		   
}		    
		return  "success";
	}
}
