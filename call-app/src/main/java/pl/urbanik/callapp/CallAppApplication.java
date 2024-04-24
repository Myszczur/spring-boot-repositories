package pl.urbanik.callapp;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.urbanik.callapp.config.Configuration;
import pl.urbanik.callapp.user.User;
import pl.urbanik.callapp.user.UserService;

@Log4j
@SpringBootApplication
public class CallAppApplication {


    public static void main(String[] args) {
        log.info(Configuration.getAppName() + " ! START ");

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
