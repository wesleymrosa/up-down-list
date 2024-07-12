package br.com.up_down_list.repository;

import br.com.up_down_list.entity.ArquivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivoEntity, Long> {
}
