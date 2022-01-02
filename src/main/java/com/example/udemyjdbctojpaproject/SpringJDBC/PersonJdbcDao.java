package com.example.udemyjdbctojpaproject.SpringJDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            return person;
        }
    }

        //SELECT * FROM person but in Java
        public List<Person> findAll() {
            return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());

        /*The first part is the query we send to the database. The second part is
        where we want the queried data to return to. We want it to return into the model.
         */

        }

        public Person findById(int id) {
            return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<Person>(Person.class));
        }

        public int deleteById(int id) {
            return jdbcTemplate.update("DELETE FROM person WHERE id=?", new Object[]{id});
        }


        public int insert(Person person) {
            return jdbcTemplate.update("INSERT INTO person (id, name, location) " + "VALUES(?,?,?)",
                    new Object[]{person.getId(), person.getName(), person.getLocation()});
        }

        public int update(Person person) {
            return jdbcTemplate.update("UPDATE person " + " SET name = ?, location = ?" + " WHERE id = ?",
                    new Object[]{person.getName(), person.getLocation(), person.getId()});
        }

}
