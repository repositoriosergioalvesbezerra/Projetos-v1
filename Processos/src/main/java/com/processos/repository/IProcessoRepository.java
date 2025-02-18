package com.processos.repository;

import com.processos.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProcessoRepository extends JpaRepository<Processo, String> {

    /**
     * Method responsible for get processo by npu
     * @param npu
     * @return Processo
     */
    Optional<Processo> findByNpu(String npu);
}
