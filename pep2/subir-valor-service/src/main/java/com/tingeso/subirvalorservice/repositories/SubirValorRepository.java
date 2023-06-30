package com.tingeso.subirvalorservice.repositories;

import com.tingeso.subirvalorservice.entities.SubirValorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubirValorRepository extends JpaRepository <SubirValorEntity, Integer>{

    @Query("select e from SubirValorEntity e where e.proveedor = :proveedor")
    SubirValorEntity findByProveedor(@Param("proveedor") String proveedor);

    @Query("select count(e) > 0 from SubirValorEntity e")
    boolean existsAny();
}

