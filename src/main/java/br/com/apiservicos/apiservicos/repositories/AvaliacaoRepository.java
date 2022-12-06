package br.com.apiservicos.apiservicos.repositories;

import br.com.apiservicos.apiservicos.domain.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    Page<Avaliacao> findByServicoPrestadorId(Long id, Pageable pageable);
}
