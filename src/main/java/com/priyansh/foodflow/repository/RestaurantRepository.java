package com.priyansh.foodflow.repository;

import com.priyansh.foodflow.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

}
