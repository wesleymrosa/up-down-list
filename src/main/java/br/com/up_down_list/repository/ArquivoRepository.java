package br.com.up_down_list.repository;

import br.com.up_down_list.entity.ArquivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface ArquivoRepository extends JpaRepository<ArquivoEntity, Long> {
}
