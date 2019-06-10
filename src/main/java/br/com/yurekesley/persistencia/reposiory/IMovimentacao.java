package br.com.yurekesley.persistencia.reposiory;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.yurekesley.persistencia.model.ContaCorrente;
import br.com.yurekesley.persistencia.model.Movimentacao;

public interface IMovimentacao extends CrudRepository<Movimentacao, Long>{

	public List<Movimentacao> findByContaOrigem(ContaCorrente contaCorrente);
	
	@Query("FROM Movimentacao m WHERE m.contaOrigem.cliente.id = :clienteID ")
	public List<Movimentacao> extrato(@Param("clienteID") Long clienteID);
	
	
	@Query("FROM Movimentacao m WHERE m.contaOrigem.agencia.id = :agenciaID ")
	public List<Movimentacao> relatorioPorAgencia(@Param("agenciaID") Long agenciaID);
}
