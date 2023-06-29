import axios from "axios";


const API_URL = "http://localhost:8080/proveedor";

class CrearPagoService {

    getPagos(){
        return axios.get(API_URL);
    }
}

export default new CrearPagoService();