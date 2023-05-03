package com.team600.moalarm.test;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<TestDto, String> {

//    TestDto findById(String id);

//    List<TestDto> findAll();
}
