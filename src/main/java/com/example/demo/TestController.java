package com.example.demo;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Optional;

@RestController
@Slf4j
public class TestController {

    @Autowired
    TestRepository testRepository;

    @Autowired
    DataSource dataSource;

    @GetMapping("/test")
    public ResponseEntity<Person> startTest() {
        Optional<Person> byId = testRepository.findById(1);
        if (dataSource instanceof HikariDataSource) {
            System.out.println("Active connections: " + ((HikariDataSource) dataSource).getHikariPoolMXBean().getActiveConnections());
        }
        return ResponseEntity.ok(byId.get());
    }
}
