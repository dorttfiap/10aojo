package br.com.fiap.abctechapi.repository;

import br.com.fiap.abctechapi.model.Assist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistRepository extends JpaRepository<Assist, Long> {

}
