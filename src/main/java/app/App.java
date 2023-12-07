package app;

import app.model.dto.request.RegisterRequest;
import app.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static app.model.entity.user.Role.ADMIN;


@SpringBootApplication
@EntityScan(basePackages = "app")
@EnableJpaRepositories("app")
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }


//       Use it for the very first run to generate admin user
//    @Bean
//    public CommandLineRunner commandLineRunner(
//            AuthenticationService service
//    ) {
//        return args -> {
//            var admin = RegisterRequest.builder()
//                    .name("admin")
//                    .lastname("Admin")
//                    .login("admin")
//                    .password("password")
//                    .role(ADMIN)
//                    .build();
//            System.out.println("Admin token: " + service.register(admin).getAccessToken());
//        };
//    }

}
