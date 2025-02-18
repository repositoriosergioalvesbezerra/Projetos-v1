package com.processos;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Aplicação Cadastro de Processos",
				description = "Aplicação Cadastro de Processos REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Sérgio Alves Bezerra",
						email = "sergiobezerra02@gmail.com",
						url = "https://www.linkedin.com/in/sergioalvesbezerra/"
				),
				license = @License(name = "Apache 2.0",
						url = "https://www.eazybytes.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Aplicação Cadastro de Processos REST API Documentation",
				url= "https://github.com/repositoriosergioalvesbezerra/projetos"
		)

)
public class ProcessosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessosApplication.class, args);
	}

}
