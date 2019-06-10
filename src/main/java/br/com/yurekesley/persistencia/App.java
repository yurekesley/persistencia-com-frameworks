package br.com.yurekesley.persistencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.yurekesley.persistencia.model.Agencia;
import br.com.yurekesley.persistencia.model.Cliente;
import br.com.yurekesley.persistencia.model.ContaCorrente;
import br.com.yurekesley.persistencia.model.Movimentacao;
import br.com.yurekesley.persistencia.model.TipoMovimentacao;
import br.com.yurekesley.persistencia.service.AgenciaService;
import br.com.yurekesley.persistencia.service.ClienteService;
import br.com.yurekesley.persistencia.service.ContaCorrenteService;
import br.com.yurekesley.persistencia.service.MovimentacaoService;

@SpringBootApplication
@EnableTransactionManagement
public class App {

	@Autowired
	private AgenciaService agenciaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ContaCorrenteService contaCorrenteService;

	@Autowired
	private MovimentacaoService movimentacaoService;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * @return
	 */
	/**
	 * @return
	 */
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

			// Transações na conta 1111111111111

			ContaCorrente ccYure = new ContaCorrente(agencia, yure, "1111111111111");
			ccYure.setSaldo(10.000);
			contaCorrenteService.save(ccYure);

			Movimentacao saque = new Movimentacao(TipoMovimentacao.SAQUE, ccYure, 2.000);
			movimentacaoService.saque(saque);

			Movimentacao deposito = new Movimentacao(TipoMovimentacao.DEPOSITO, ccYure, 4.000);
			movimentacaoService.deposito(deposito);

			// Transações na conta 2222222222222

			ContaCorrente ccKesley = new ContaCorrente(agencia, kesley, "2222222222222");
			ccKesley.setSaldo(5.000);
			contaCorrenteService.save(ccKesley);
			
			Movimentacao deposito2 = new Movimentacao(TipoMovimentacao.DEPOSITO, ccKesley, 12.000);
			movimentacaoService.deposito(deposito2);
			

			Movimentacao transferencia = new Movimentacao(TipoMovimentacao.TRANSFERENCIA, ccYure, ccKesley, 4.000);
			movimentacaoService.transferencia(transferencia);

			Cliente cliente = this.clienteService.findById(1l);

			List<Movimentacao> movimentacoes = movimentacaoService.extrato(cliente);
			
			System.out.println("\n\n");
			System.out.println("XXXXXXXXXXXXXXXX EXTRATO CLIENTE YURE");
			
			System.out.println("\n");
			for (Movimentacao movimentacao : movimentacoes) {

				System.out.println(movimentacao);

			}
			
			System.out.println("\n\n");
			System.out.println("XXXXXXXXXXXXXXXX RELATORIO DA AGÊNCIA 0001");
						
			List<Movimentacao> movimentacoesDaAgencia = movimentacaoService.relatorioPorAgencia(agencia);
			
			
			System.out.println("\n");
			for (Movimentacao movimentacao : movimentacoesDaAgencia) {

				System.out.println(movimentacao);

			}
			

		}));

	}

}
