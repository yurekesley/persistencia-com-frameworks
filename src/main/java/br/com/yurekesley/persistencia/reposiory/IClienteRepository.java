package br.com.yurekesley.persistencia.reposiory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.yurekesley.persistencia.model.Cliente;

@Repository
public interface IClienteRepository extends CrudRepository<Cliente, Long> {

}
