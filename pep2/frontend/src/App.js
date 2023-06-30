import './App.module.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomeComponent from './components/HomeComponent';
import FileUploadComponent from './components/FileUploadComponent';
import SubirValorComponent from './components/SubirValorComponent';
import FileInformationComponent from './components/FileInformationComponent';
import ProveedorComponent from './components/ProveedorComponent';
import NuevoProveedorComponent from './components/NuevoProveedorComponent';
import PlanillaComponent from './components/PlanillaComponent';

function App() {
  return (
    <div>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomeComponent />} />
        <Route path= "/subir-archivo" element={<FileUploadComponent />} />
        <Route path= "/informacion-archivo" element={<FileInformationComponent />} />
        <Route path= "/subir-valor" element={<SubirValorComponent />} />
        <Route path= "/lista-proveedores" element={<ProveedorComponent />} />
        <Route path= "/nuevo-proveedor" element={<NuevoProveedorComponent />} />
        <Route path= "/planilla-pago" element={<PlanillaComponent />} />

      </Routes>
    </BrowserRouter>
  </div>
  );
}

export default App;
