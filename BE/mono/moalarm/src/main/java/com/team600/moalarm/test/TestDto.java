package com.team600.moalarm.test;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Data
@Document(collation = "test")
public class TestDto {

    @Id
    String id;
    String message;
}
