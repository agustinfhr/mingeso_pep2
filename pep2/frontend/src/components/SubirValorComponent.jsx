import React, { Component } from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import NavbarComponent2 from "./NavbarComponent2";
import SubirValorService from "../services/SubirValorService";
import styled from "styled-components";
import swal from 'sweetalert';
import { createGlobalStyle } from 'styled-components'

class SubirValorComponent extends Component{
    constructor(props) {
        super(props);
        this.state = {
            file: null,
        };
        this.onFileChange = this.onFileChange.bind(this);
    }

    onFileChange(event) {
        this.setState({ file: event.target.files[0] });
    }

    onFileUpload = () => {
        swal({
            title: "¿Está seguro de que desea cargar el archivo Valores.csv?",
            text: " ",
            icon: "warning",
            buttons: ["Cancelar", "Cargar"],
            dangerMode: true
        }).then(respuesta=>{
            if(respuesta){
                swal("Archivo cargado correctamente!", {icon: "success", timer: "3000"});
                const formData = new FormData();
                formData.append("file", this.state.file);
                SubirValorService.CargarValor(formData).then((res) => {
                });
            }
            else{
                swal({text: "Archivo no cargado.", icon: "error"});
            }
        });
    };

    render() {
        return (
            <div className="home">
                <NavbarComponent2 />
                <GlobalStyle />
                <Styles>
                    <div class="f">
                        <div class="container">
                            <h1><b>Cargar el archivo de datos</b></h1>
                            <Row className="mt-4">
                                <Col col="12">
                                    <Form.Group className="mb-3" controlId="formFileLg">
                                        <Form.Control type="file" size="lg" onChange={this.onFileChange} />
                                    </Form.Group>
                                    <Button varian="primary" onClick={this.onFileUpload}>
                                        Cargar el archivo a la Base de Datos</Button>
                                </Col>
                            </Row>
                        </div>
                    </div>
                    <br>
                    </br>
                    <hr>
                    </hr>
                    <div class="form1">
                        <h5><b>Recuerde que el archivo debe ser un .csv!</b></h5>
                    </div>
                </Styles>
            </div>
        );
    }
}

export default SubirValorComponent;

const GlobalStyle = createGlobalStyle`

  body {
    min-height: 100vh;
    margin: 0;
    background-image: linear-gradient(to bottom, #e0e8ff, #5d8ec2);
  }

`


const Styles = styled.div`


  .container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 2%;
  }

  .f {
    margin-top: 40px;
    border: 3px solid rgb(255, 255, 255);
    padding: 40px;
    padding-top: 10px;
    border-radius: 40px;
    margin-left: 300px;
    margin-right: 300px;
  }

  @media (max-width: 1200px) {
    .f {
      margin-left: 200px;
      margin-right: 200px;
    }

  }

  .form1 {
    border: 1px solid rgb(255, 255, 255);
    padding: 30px;
    border-radius: 30px;
    margin-left: 300px;
    margin-right: 300px;
  }
`