package com.example.useremployee.repositories;

import com.example.useremployee.model.Employee;
import com.example.useremployee.model.Gender;
import com.example.useremployee.model.User;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void testAtLeastOneJens() {
        List<Employee> lst = employeeRepository.findByName("jens");
        assertTrue(lst.size() > 0);
    }

    @Test
    void testDeleteEmployee() {
        List<Employee> lst = employeeRepository.findByName("jens");
        Employee employee = lst.get(0);
        assertEquals("Jens", employee.getName());
        //userRepository.delete(employee.getUser());
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> userRepository.delete(employee.getUser()));
    }

    @Test
    void testDeleteUser() {
        List<User> lst = userRepository.findAllByEmail("jens@aol.com");
        User user = lst.get(0);
        assertEquals("jens@aol.com", user.getEmail());
        //userRepository.delete(employee.getUser());
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> employeeRepository.delete(user.getEmployee()));
    }

    @Test
    void testCreateEmployee() {
        User user = new User();
        Employee employee = new Employee();
        employee.setBorn(LocalDateTime.of(1990, 5, 10, 16, 10, 12));
        employee.setName("Jens");
        employee.setGender(Gender.MALE);
        employee.setVegetarian(true);
        employee.setUser(user);

        List<Employee> lst = employeeRepository.findByName("Jens");

        assertEquals("Jens",employee.getName());

    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("Kjar@gmail.com");
        user.setPassword("x");
        userRepository.save(user);

        List<User> lst = userRepository.findAllByEmail("Kjar@gmail.com");

        assertEquals("Kjar@gmail.com", user.getEmail());


    }


}