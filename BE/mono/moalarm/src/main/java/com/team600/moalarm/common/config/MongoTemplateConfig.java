package com.team600.moalarm.common.config;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoTemplateConfig {

    private final MongoTemplate mongoTemplate;

    public MongoTemplateConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
