package com.tingeso.empleadoservice.reporitories;

import com.tingeso.empleadoservice.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {

    @Query(value = "select codigo from proveedor", nativeQuery = true)
    List<String> findCodigo();

    @Query("select e from ProveedorEntity e where e.codigo = :codigo")
    ProveedorEntity findByCodigo(@Param("codigo")String codigo);

}
