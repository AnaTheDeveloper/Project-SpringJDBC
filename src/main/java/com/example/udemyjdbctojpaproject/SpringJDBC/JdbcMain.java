package com.example.udemyjdbctojpaproject.SpringJDBC;

import com.example.udemyjdbctojpaproject.SpringJDBC.entity.Person;
import com.example.udemyjdbctojpaproject.SpringJDBC.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class JdbcMain implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJdbcDao dao;

    public static void main(String[] args) {
        SpringApplication.run(JdbcMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All users -> {}", dao.findAll());

        logger.info("User id 10001 -> {}", dao.findById(10001));

        logger.info("Deleting 10002 -> {}", dao.deleteById(10002));

        logger.info("Inserting 10004 -> {}", dao.insert(new Person(10004, "Mary", "Berlin")));

        logger.info("Update 10003 -> {}", dao.update(new Person(10003, "Pieter", "France")));
    }

    /*
    Here we used Spring JDBC to execute a query and print the results of that query.
     */
}
