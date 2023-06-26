package com.tingeso.marcasrelojservice.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "datas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubirDataEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String fecha;
    private String turno;
    private String proveedor;
    private String kls_leche;

}
