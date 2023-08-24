package com.example.useremployee.config;

import com.example.useremployee.model.Employee;
import com.example.useremployee.model.Gender;
import com.example.useremployee.model.User;
import com.example.useremployee.repositories.EmployeeRepository;
import com.example.useremployee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InitData implements CommandLineRunner {


    @Autowired
    UserRepository userRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("erik@aol.com");
        user.setPassword("lasdfkjalk");
        userRepository.save(user);


        user.setUserID(0);
        user.setEmail("kurt@aol.com");
        user.setPassword("masrdjalk");
        userRepository.save(user);

        user.setUserID(0);
        user.setEmail("jens@aol.com");
        user.setPassword("pasrdjalk");
        userRepository.save(user);

        Employee emp1=new Employee();
        emp1.setBorn(LocalDateTime.of(1990, 5, 10, 16, 10, 12));
        emp1.setName("Jens");
        emp1.setGender(Gender.MALE);
        emp1.setVegetarian(true);
        emp1.setUser(user);
        employeeRepository.save(emp1);


    }
}
