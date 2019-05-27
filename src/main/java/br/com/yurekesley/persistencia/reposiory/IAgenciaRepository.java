package br.com.yurekesley.persistencia.reposiory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.yurekesley.persistencia.model.Agencia;

@Repository
public interface IAgenciaRepository extends CrudRepository<Agencia, Long> {

}
