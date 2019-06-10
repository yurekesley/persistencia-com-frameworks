package br.com.yurekesley.persistencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yurekesley.persistencia.exeption.ContaInvalida;
import br.com.yurekesley.persistencia.model.Cliente;
import br.com.yurekesley.persistencia.model.Movimentacao;
import br.com.yurekesley.persistencia.model.TipoMovimentacao;
import br.com.yurekesley.persistencia.reposiory.IMovimentacao;

@Service
public class MovimentacaoService extends GenericService<Movimentacao, Long> {

	@Autowired
	private IMovimentacao movimentacaoRepository;

	@Autowired
	private ContaCorrenteService contaCorrenteService;

	public void saque(Movimentacao saque) throws Exception {
		if (saque.getTipoMovimentacao() != TipoMovimentacao.SAQUE) {
			throw new Exception("Operação Inválida");
		}

		this.contaCorrenteService.saque(saque.getContaOrigem(), saque.getValor());

		this.save(saque);
	}

	public void deposito(Movimentacao deposito) {

		verificarContaDeOrigem(deposito);

		this.contaCorrenteService.deposito(deposito.getContaOrigem(), deposito.getValor());

		this.save(deposito);

	}

	private void verificarContaDeOrigem(Movimentacao movimentacao) throws ContaInvalida {

		if (movimentacao == null || movimentacao.getContaOrigem() == null) {
			throw new ContaInvalida("Conta de Origem inválida");
		}

	}

	private void verificarContaDeDestino(Movimentacao movimentacao) throws ContaInvalida {

		if (movimentacao == null || movimentacao.getContaDestino() == null) {
			throw new ContaInvalida("Conta de Destino inválida");
		}

	}

	public void transferencia(Movimentacao transferencia) {

		verificarContaDeOrigem(transferencia);

		verificarContaDeDestino(transferencia);

		this.contaCorrenteService.saque(transferencia.getContaOrigem(), transferencia.getValor());

		this.contaCorrenteService.deposito(transferencia.getContaDestino(), transferencia.getValor());

		this.save(transferencia);

	}

	public List<Movimentacao> extrato(Cliente cliente) {		
		return movimentacaoRepository.extrato(cliente.getId());
	}

}
