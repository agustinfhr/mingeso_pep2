import React, {Component, useState} from "react";
import NavbarComponent2 from "./NavbarComponent2";
import styled from "styled-components";
import { createGlobalStyle } from 'styled-components'


class ProveedorComponent extends Component{
    constructor(props){
        super(props);
        this.state = {
            valores: [],
        };
    }

    componentDidMount(){
        fetch("http://localhost:8080/subir-valor")
            .then((response) => response.json())
            .then((data) => this.setState({ valores: data }));
    }

    render(){
        return(
            <div className="home">
                <NavbarComponent2 />
                <GlobalStyle />
                <Styles>
                    <h1 className="text-center"> <b>Ultimo Valores.csv</b></h1>
                    <div className="f">
                        <table border="1" class="content-table">
                            <thead>
                            <tr>
                                <th>Proveedor</th>
                                <th>%Grasa</th>
                                <th>%Solido total</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.valores.map((valor) => (
                                <tr key={valor.ID}>
                                    <td>{valor.proveedor}</td>
                                    <td>{valor.pct_grasa}</td>
                                    <td>{valor.pct_solido_total}</td>
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

export default ProveedorComponent;


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