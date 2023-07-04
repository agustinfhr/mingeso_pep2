import React from "react";
import styled from "styled-components";

function NavbarComponent(){
    return(
        <>
        <NavStyle>
            <header class="header">
                <div class="logo">
                    <h1>MilkStgo</h1>
                </div>
                <nav>
                </nav>
                <a class="btn" href="/"><button>Volver al menú principal</button></a>
                <a class="btn-2" href="/acopio-information"><button>Ver el último Acopio.csv cargado</button></a>
            </header>
            </NavStyle>
        </>
    )
}

export default NavbarComponent;


const NavStyle = styled.nav`
  .header{
    background-color: #ffffff;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    height: 85px;
    padding: 5px 10%;
  }
  .header .logo{
    margin-right: auto;
    color: #2a3944;
    font-family: 'Helvetica',cursive;
  }
  .header .btn button{
    margin-left: 20px;
    font-weight: 700;
    color: #1b3039;
    padding: 9px 25px;
    background: #eceff1;
    border: none;
    border-radius: 50px;
    cursor: pointer;
    transition: all 0.3s ease 0s;
  }
  .header .btn button:hover{
    background-color: #377bbd;
    color: #f6f6f6;
    transform: scale(1.1);
  }
  .header .btn-2 button {
    margin-left: 20px;
    font-weight: 700;
    color: #1b3039;
    padding: 9px 25px;
    background: #eceff1;
    border: none;
    border-radius: 50px;
    cursor: pointer;
    transition: all 0.3s ease 0s;
  }
  .header .btn-2 button:hover{
    background-color: #377bbd;
    color: #f6f6f6;
    transform: scale(1.1);
  }
`