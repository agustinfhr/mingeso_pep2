import React, { useState } from "react";
import NavbarComponent3 from "./NavbarComponent3";
import styled from "styled-components";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import swal from 'sweetalert';
import NuevoProveedorService from "../services/NuevoProveedorService";
import { createGlobalStyle } from 'styled-components'

export default function NuevoProveedorComponent(props){

    const initialState = {
        codigo: "",
        nombre: "",
        categoria: "",
        retencion: ""
    };

    const [input, setInput] = useState(initialState);

    const changeCodigoHandler = event => {
        setInput({ ...input, codigo: event.target.value });
        console.log(input.codigo);
    };
    const changeNombreHandler = event => {
        setInput({ ...input, nombre: event.target.value });
        console.log(input.nombre);
    };
    const changeCategoriaHandler = event => {
        setInput({ ...input, categoria: event.target.value });
        console.log(input.categoria);
    };
    const changeRetencionHandler = event => {
        setInput({ ...input, retencion: event.target.value });
        console.log(input.retencion);
    };


    const ingresarProveedor = e => {
        e.preventDefault();
        swal({
            title: "¿Está seguro de que desea ingresar este Proveedor?",
            text: " ",
            icon: "warning",
            buttons: ["Cancelar", "Enviar"],
            dangerMode: true
        }).then(respuesta=>{
            if(respuesta){
                swal("Proveedor ingresado correctamente!", {icon: "success", timer: "3000"});
                let proveedor = { codigo: input.codigo, nombre: input.nombre, categoria: input.categoria, retencion: input.retencion};
                console.log(input.codigo)
                console.log(input.nombre)
                console.log(input.categoria)
                console.log(input.retencion)
                console.log("Proveedor => " + JSON.stringify(proveedor));
                NuevoProveedorService.IngresarProveedor(proveedor).then(
                    (res) => {
                    }
                );
                }
            else{
                swal({text: "Proveedor no ingresado.", icon: "error"});
            }
        });
    };

    return(

        <Styles>
            <div className="home">
                <NavbarComponent3 />
                <GlobalStyle />
                <div className="mainclass">
                    <div className="form1">
                        <h1 className="text-center"><b>Proveedores</b></h1>
                        <div className="formcontainer">
                            <hr></hr>
                            <div className="container">
                                <Form>
                                    <Form.Group className="mb-3" controlId="codigo" value = {input.codigo} onChange={changeCodigoHandler}>
                                        <Form.Label>Codigo del proveedor</Form.Label>
                                        <Form.Control type="codigo" placeholder="Codigo del proveedor en formato XXXXXX" />
                                    </Form.Group>

                                    <Form.Group className="mb-3" controlId="nombre" value = {input.nombre} onChange={changeNombreHandler}>
                                        <Form.Label>Nombre del proveedor</Form.Label>
                                        <Form.Control type="nombre" placeholder="nombre en formato string" />
                                    </Form.Group>

                                    <Form.Group className="mb-3" controlId="categoria" value = {input.categoria} onChange={changeCategoriaHandler}>
                                        <Form.Label>Categoria del proveedor</Form.Label>
                                        <Form.Control type="categoria" placeholder="Categoria en formato X" />
                                    </Form.Group>

                                    <Form.Group className="mb-3" controlId="retencion" value = {input.retencion} onChange={changeRetencionHandler}>
                                        <Form.Label>Retencion del proveedor</Form.Label>
                                        <Form.Control type="retencion" placeholder="Rentencion en formato Si/No" />
                                    </Form.Group>
                                </Form>
                            </div>
                            <Button className="boton" onClick={ingresarProveedor}>Registrar Proveedor</Button>
                        </div>
                    </div>
                </div>

            </div>
        </Styles>
    )
}


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
    color: #000000;
    font-weight: 700;
    text-decoration: none solid rgb(68, 68, 68);
    font-style: normal;
    font-variant: normal;
    text-transform: uppercase;
}



.mainclass{
    margin-top: 20px;
    display: flex;
    justify-content: center;
    font-family: Roboto, Arial, sans-serif;
    font-size: 15px;
}

.form1{
  border: 6px solid #f1f1f1;
  background-image: linear-gradient(to bottom, rgba(255, 255, 255, 0.34), rgba(255, 255, 255, 0.28));
    width: 50%;
    padding: 36px;
}

input[type=rut], input[type=fecha] {
    width: 100%;
    padding: 16px 8px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

Button {
    background-color: #377bbd;
    color: white;
    padding: 14px 0;
    margin: 10px 0;
    border: none;
    cursor: grabbing;
    width: 100%;
}

Button:hover {
    opacity: 0.8;
}

.formcontainer {
    text-align: left;
    margin: 24px 100px 9px;
}

.container {
    padding: 24px 0;
    text-align:left;
}

span.psw {
    float: right;
    padding-top: 0;
    padding-right: 15px;
}
`