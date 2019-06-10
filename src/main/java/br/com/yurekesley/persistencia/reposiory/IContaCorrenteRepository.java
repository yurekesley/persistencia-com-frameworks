package br.com.yurekesley.persistencia.reposiory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.yurekesley.persistencia.model.Cliente;
import br.com.yurekesley.persistencia.model.ContaCorrente;

@Repository
public interface IContaCorrenteRepository extends CrudRepository<ContaCorrente, Long> {
	public ContaCorrente findByCliente(Cliente cliente);
}
