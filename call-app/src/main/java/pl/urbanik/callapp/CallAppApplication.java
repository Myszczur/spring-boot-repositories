package pl.urbanik.callapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.urbanik.callapp.user.User;
import pl.urbanik.callapp.user.UserService;

@SpringBootApplication
public class CallAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserService service
    ) {
        return args -> {
            service.register(User.builder()
                    .username("Kamil")
                    .email("Kamil@mail.com")
                    .password("aaa")
                    .build());

            service.register(User.builder()
                    .username("John")
                    .email("john@mail.com")
                    .password("aaa")
                    .build());

            service.register(User.builder()
                    .username("Anny")
                    .email("anna@mail.com")
                    .password("aaa")
                    .build());
        };
    }
}
