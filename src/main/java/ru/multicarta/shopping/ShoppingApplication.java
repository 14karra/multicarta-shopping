package ru.multicarta.shopping;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedOrigins("*")
						.allowedHeaders("*");
			}
		};
	}

	@Configuration
	public static class SpringDocConfig {
		@Value("${app.name:Shopping API}")
		private String title;
		@Value("${app.description:API for Shopping server application}")
		private String description;
		@Value("${app.api.version:0.0.1}")
		private String apiVersion;
		@Value("${app.contact.email}")
		private String contactEmail;

		@Bean
		public OpenAPI springDocOpenAPI() {
			return new OpenAPI()
					.info(new Info()
							.title(title)
							.description(description)
							.version(apiVersion)
							.license(new License()
									.name("")
									.url(""))
							.termsOfService("")
							.contact(new Contact()
									.name("Shopping Development Team")
									.email(contactEmail)))
					.externalDocs(new ExternalDocumentation()
							.description("Shopping Documentation")
							.url("mailto:" + contactEmail));
		}
	}
}
