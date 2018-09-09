package com.saults.HomeObserverBackend;

import com.saults.HomeObserverBackend.Entities.User;
import com.saults.HomeObserverBackend.Repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            System.out.println("=============== Loading database ===============");
            System.out.println("Preloading " + repository.save(new User("Dad", "adult")));
            System.out.println("Preloading " + repository.save(new User("Isaac", "child")));
        };
    }
}
