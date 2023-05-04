package com.team600.moalarm.test;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestRepository testRepository;

    @GetMapping("/{id}")
    public TestDto find(@PathVariable String id) {
        return testRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<TestDto> findAll() {
        return testRepository.findAll();
    }

    @PostMapping
    public TestDto save(@RequestBody TestDto dto) {
        return testRepository.save(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody TestDto dto) {
        dto.setId(id);
        testRepository.save(dto);
    }
}
