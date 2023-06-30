package com.tingeso.subirdataservice.repositories;

import com.tingeso.subirdataservice.entities.SubirDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SubirDataRepository extends JpaRepository <SubirDataEntity, Integer>{

    @Query("select e from SubirDataEntity e order by e.fecha asc")
    ArrayList<SubirDataEntity> findAllOrderByDateAsc();

    @Query("select e.kls_leche from SubirDataEntity e where e.proveedor = :proveedor order by e.fecha asc")
    List<String> findAllByCodigoOrderByDateAsc(@Param("proveedor") String proveedor);

    @Query("select count(e) > 0 from SubirDataEntity e")
    boolean existsAny();
}
