package br.com.yurekesley.persistencia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.yurekesley.persistencia.model.Vet;
import br.com.yurekesley.persistencia.reposiory.VetDAO;

@SpringBootApplication
@EnableTransactionManagement
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner runner(VetDAO dao) {
		return ((args -> {

			dao.create(new Vet("Yure", "Kesley"));

		}));
	}

}
