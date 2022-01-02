package com.example.udemyjdbctojpaproject.SpringJDBC;

import com.example.udemyjdbctojpaproject.SpringJDBC.entity.Person;
import com.example.udemyjdbctojpaproject.SpringJDBC.jpa.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaMain implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJpaRepository personJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("User id 10001 -> {}", personJpaRepository.findById(10001));

        logger.info("Inserting -> {}",
                personJpaRepository.insert(new Person("Tara", "Spain")));

        logger.info("Inserting -> {}",
                personJpaRepository.insert(new Person("Molly", "Italy")));

        logger.info("Update 10003 -> {}",
                personJpaRepository.update(new Person(10003, "Pieter", "Utrecht")));

        personJpaRepository.deleteById(10002);

        logger.info("All users -> {}", personJpaRepository.findAll());
    }
}
