package br.com.yurekesley.persistencia;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.yurekesley.persistencia.model.Agencia;
import br.com.yurekesley.persistencia.model.Cliente;
import br.com.yurekesley.persistencia.model.ContaCorrente;
import br.com.yurekesley.persistencia.service.AgenciaService;
import br.com.yurekesley.persistencia.service.ClienteService;
import br.com.yurekesley.persistencia.service.ContaCorrenteService;

@SpringBootApplication
@EnableTransactionManagement
public class App {

	@Autowired
	private AgenciaService agenciaService;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired 
	private ContaCorrenteService contaCorrenteService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return ((args -> {

			Agencia agencia = new Agencia();
			agencia.setCodigo("0001");
			agenciaService.save(agencia);
			
			Cliente yure = new Cliente();
			yure.setNome("Yure");
			yure.setAgencia(agencia);
			clienteService.save(yure);

			Cliente kesley = new Cliente();
			kesley.setNome("Kesley");
			kesley.setAgencia(agencia);
			clienteService.save(kesley);

			ContaCorrente ccYure = new ContaCorrente();
			ContaCorrente ccKesley = new ContaCorrente();

			ccYure.setCliente(yure);
			ccYure.setAgencia(agencia);
			ccYure.setSaldo(new BigDecimal(10.000));
			contaCorrenteService.save(ccYure);
			
			ccKesley.setCliente(kesley);
			ccKesley.setAgencia(agencia);
			ccKesley.setSaldo(new BigDecimal(5.000));
			contaCorrenteService.save(ccKesley);

			
			
			
			// cc.s


		}));
	}

}
