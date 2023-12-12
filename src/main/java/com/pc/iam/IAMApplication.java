package com.pc.iam;

import com.pc.iam.helpers.RefreshableCRUDRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = RefreshableCRUDRepositoryImpl.class)
@SpringBootApplication
public class IAMApplication {

    public static void main(String[] args) {
        SpringApplication.run(IAMApplication.class, args);
    }

}
