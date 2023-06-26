import axios from "axios";

const API_URL = "http://localhost:8080/subir-valor/";

class SubirValorService{

    CargarValor(file){
        return axios.post(API_URL, file);
    }
}

export default new SubirValorService();