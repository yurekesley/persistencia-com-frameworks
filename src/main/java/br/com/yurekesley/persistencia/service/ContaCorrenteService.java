package br.com.yurekesley.persistencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yurekesley.persistencia.exeption.SaldoInsuficienteException;
import br.com.yurekesley.persistencia.model.Cliente;
import br.com.yurekesley.persistencia.model.ContaCorrente;
import br.com.yurekesley.persistencia.reposiory.IContaCorrenteRepository;

@Service
public class ContaCorrenteService extends GenericService<ContaCorrente, Long> {

	@Autowired
	private IContaCorrenteRepository contaCorrenteRepository;

	public void saque(ContaCorrente c, double valor) throws SaldoInsuficienteException {

		if (verificarPossibilidadeDeSaque(c.getSaldo(), valor)) {
			throw new SaldoInsuficienteException("Saldo Insuficiênte, saldo disponível: " + c.getSaldo());
		}

		c.setSaldo(c.getSaldo() - valor);

		this.save(c);
	}

	public void deposito(ContaCorrente c, double valor) {
		c.setSaldo(c.getSaldo() + valor);
		this.save(c);
	}

	private boolean verificarPossibilidadeDeSaque(double saldo, double valorDeSaque) {

		boolean podeRealizarSaque = false;

		podeRealizarSaque = saldo < valorDeSaque;

		return podeRealizarSaque;
	}

	public ContaCorrente buscarPorCliente(Cliente cliente) {
		return this.contaCorrenteRepository.findByCliente(cliente);
	}

}
