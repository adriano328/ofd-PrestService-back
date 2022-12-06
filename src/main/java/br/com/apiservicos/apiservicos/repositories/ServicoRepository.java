package br.com.apiservicos.apiservicos.repositories;

import br.com.apiservicos.apiservicos.domain.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Page<Servico> findAllByPrestadorId(Long id, Pageable pageable);

    Page<Servico> findAllByClienteId(Long id, Pageable pageable);
}
