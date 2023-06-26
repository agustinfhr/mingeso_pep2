import axios from "axios";

class NuevoProveedorService {

    IngresarProveedor(proveedor){
        return axios.post(`http://localhost:8080/proveedor`, proveedor);
    }
}

export default new NuevoProveedorService();