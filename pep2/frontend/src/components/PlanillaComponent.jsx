import React, {Component, useState} from "react";
import NavbarComponent4 from "./NavbarComponent4";
import styled from "styled-components";
import swal from "sweetalert";
import Button from "react-bootstrap/Button";
import { createGlobalStyle } from 'styled-components'

class PlanillaComponent extends Component{
    constructor(props){
        super(props);
        this.state = {
            planillas: [],
        };
    }

    componentDidMount(){
        fetch("http://localhost:8080/planilla-pago")
            .then((response) => response.json())
            .then((data) => this.setState({ planillas: data }));
    }


    render(){
        return(
            <div className="home">
                <NavbarComponent4 />
                <GlobalStyle />
                <Styles>
                    <h1 className="text-center"> <b>Listado de planillas</b></h1>
                    <div className="f">
                        <table border="1" class="content-table">
                            <thead>
                            <tr>
                                <th>Quincena</th>
                                <th>Codigo Proveedor</th>
                                <th>Nombre Proveedor</th>
                                <th>TOTAL KLS leche</th>
                                <th>Pago por Leche</th>
                                <th>%Grasa</th>
                                <th>Pago por Grasa</th>
                                <th>%Solidos Totales</th>
                                <th>Pago por Solidos Totales</th>
                                <th>Nro. días que envío leche</th>
                                <th>Promedio diario KLS leche</th>
                                <th>%Variación Leche</th>
                                <th>%Variación Grasa</th>
                                <th>%Variación ST</th>
                                <th>Bonificación por Frecuencia</th>
                                <th>Dcto. Variación Leche</th>
                                <th>Dcto. Variación Grasa</th>
                                <th>Dcto. Variación ST</th>
                                <th>Pago TOTAL</th>
                                <th>Monto Retención</th>
                                <th>Monto FINAL</th>

                            </tr>
                            </thead>
                            <tbody>
                            {this.state.planillas.map((planilla) => (
                                <tr key={planilla.codigo_proveedor}>
                                    <td>{planilla.quincena}</td>
                                    <td>{planilla.codigo_proveedor}</td>
                                    <td>{planilla.nombre_proveedor}</td>
                                    <td>{planilla.total_kls_leche}</td>
                                    <td>{planilla.pago_por_leche}</td>
                                    <td>{planilla.pct_grasa}</td>
                                    <td>{planilla.pago_por_grasa}</td>
                                    <td>{planilla.pct_solidos_totales}</td>
                                    <td>{planilla.pago_por_solidos_totales}</td>
                                    <td>{planilla.nro_dias_envio_leche}</td>
                                    <td>{planilla.promedio_diario_kls_leche}</td>
                                    <td>{planilla.pct_variacion_leche}</td>
                                    <td>{planilla.pct_variacion_grasa}</td>
                                    <td>{planilla.pct_variacion_st}</td>
                                    <td>{planilla.bonificacion_frecuencia}</td>
                                    <td>{planilla.dcto_variacion_leche}</td>
                                    <td>{planilla.dcto_variacion_grasa}</td>
                                    <td>{planilla.dcto_variacion_st}</td>
                                    <td>{planilla.pago_total}</td>
                                    <td>{planilla.monto_retencion}</td>
                                    <td>{planilla.monto_final}</td>


                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </Styles>
            </div>
        )
    }
}

export default PlanillaComponent;

const GlobalStyle = createGlobalStyle`

  body {
    min-height: 100vh;
    margin: 0;
    background-image: linear-gradient(to bottom, #e0e8ff, #5d8ec2);
  }

`

const Styles = styled.div`

  .text-center {
    text-align: center;
    justify-content: center;
    padding-top: 8px;
    font-family: "Arial Black", Gadget, sans-serif;
    font-size: 30px;
    letter-spacing: 0px;
    word-spacing: 2px;
    color: #2a3944;
    font-weight: 700;
    text-decoration: none solid rgb(68, 68, 68);
    font-style: normal;
    font-variant: normal;
    text-transform: uppercase;
  }

  .f {
    justify-content: center;
    align-items: center;
    display: flex;
  }

  * {
    font-family: sans-serif;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

  .content-table {
    border-collapse: collapse;
    margin: 25px 0;
    font-size: 0.9em;
    min-width: 400px;
    border-radius: 5px 5px 0 0;
    overflow: hidden;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  }

  .content-table thead tr {
    background-color: #377bbd;
    border-bottom: 1px solid #dddddd;
    color: #ffffff;
    text-align: left;
    font-weight: bold;
  }

  .content-table th,
  .content-table td {
    padding: 12px 15px;
  }

  .content-table tbody tr {
    background-color: rgba(255, 255, 255, 0.49);
    border-bottom: 1px solid #dddddd;
  }

  .content-table tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
  }

  .content-table tbody tr:last-of-type {
    border-bottom: 2px solid #377bbd;
  }

  .content-table tbody tr.active-row {
    font-weight: bold;
    color: #377bbd;
  }


  .content-table tbody tr:last-of-type {
    border-bottom: 2px solid #377bbd;
  }

  .content-table tbody tr.active-row {
    font-weight: bold;
    color: #377bbd;
  }
`