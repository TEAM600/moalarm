package com.team600.moalarm.test;

import lombok.Data;
import org.springframework.data.annotation.Id;

//@Getter
@Data
public class TestDto {

    @Id
    String id;
    String message;
}
