package io.github.hobbstech.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Drones API",
                description = "Drones API",
                contact = @Contact(
                        name = "Wilson Chiviti",
                        email = "wilsonchiviti@gmail.com"
                ),
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Localhost Dev Server"
                ),
        }
)
public class DocumentationConfiguration {
}
