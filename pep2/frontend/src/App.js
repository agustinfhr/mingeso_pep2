import './App.module.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomeComponent from './components/HomeComponent';
import FileUploadComponent from './components/FileUploadComponent';
import SubirValorComponent from './components/SubirValorComponent';
import ProveedorComponent from './components/ProveedorComponent';
import NuevoProveedorComponent from './components/NuevoProveedorComponent';
import PlanillaComponent from './components/PlanillaComponent';
import DataFileInformationComponent from "./components/DataFileInformationComponent";
import ValorFileInformationComponent from "./components/ValorFileInformationComponent";


function App() {
  return (
    <div>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomeComponent />} />
        <Route path= "/subir-archivo" element={<FileUploadComponent />} />
        <Route path= "/acopio-information" element={<DataFileInformationComponent />} />
        <Route path= "/subir-valor" element={<SubirValorComponent />} />
        <Route path= "/valor-information" element={<ValorFileInformationComponent />} />
        <Route path= "/lista-proveedores" element={<ProveedorComponent />} />
        <Route path= "/nuevo-proveedor" element={<NuevoProveedorComponent />} />
        <Route path= "/planilla-pago" element={<PlanillaComponent />} />

      </Routes>
    </BrowserRouter>
  </div>
  );
}

export default App;
