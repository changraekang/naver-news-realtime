package com.cos.navernewssave.domain;

import org.springframework.data.mongodb.repository.MongoRepository; 
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;



public interface NewsRepository extends MongoRepository<News, String> {

	
}
