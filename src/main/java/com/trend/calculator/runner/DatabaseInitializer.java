package com.trend.calculator.runner;

import com.trend.calculator.model.Operation;
import com.trend.calculator.model.User;
import com.trend.calculator.repository.IOperationRepository;
import com.trend.calculator.security.WebSecurityConfig;
import com.trend.calculator.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final IOperationRepository iOperationRepository;

    @Override
    public void run(String... args) {
        if (!userService.getUsers().isEmpty()) {
            return;
        }

        USERS.forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            OPERATIONS.forEach(operation -> {
                operation.setOperations(user);

                iOperationRepository.save(operation);

            });

        });




        log.info("Database initialized");
    }

    private static final List<User> USERS = Arrays.asList(
            new User("admin", "admin", "Admin", "admin@mycompany.com", WebSecurityConfig.ADMIN),
            new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER),
            new User("jlozano", "12345", "Llozano", "jllozano.100@gmail.com", WebSecurityConfig.USER)
    );

    private static final List<Operation> OPERATIONS = Arrays.asList(
        new Operation("RANDOM", 12.9,new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER)),
        new Operation("ADD", 10.9,new User("jlozano", "12345", "Llozano", "jllozano.100@gmail.com", WebSecurityConfig.USER))

        );
}
