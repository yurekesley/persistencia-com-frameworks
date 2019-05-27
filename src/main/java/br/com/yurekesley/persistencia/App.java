package br.com.yurekesley.persistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.yurekesley.persistencia.model.Agencia;
import br.com.yurekesley.persistencia.reposiory.IAgenciaRepository;
import br.com.yurekesley.persistencia.reposiory.IClienteRepository;
import br.com.yurekesley.persistencia.reposiory.VetDAO;

@SpringBootApplication
@EnableTransactionManagement
public class App {
	
	@Autowired
	private IAgenciaRepository agenciaRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner runner(VetDAO dao) {
		return ((args -> {
			
			Agencia agencia = new Agencia();
			
			agencia.setCodigo("0001");
			agenciaRepository.save(agencia);
			

		//	clienteRepository.save(new)

		}));
	}

}
