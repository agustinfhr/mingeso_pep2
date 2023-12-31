package com.tingeso.subirdataservice.services;

import com.tingeso.subirdataservice.entities.SubirDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.tingeso.subirdataservice.repositories.SubirDataRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubirDataService {

    @Autowired
    private SubirDataRepository dataRepository;


    private final Logger logg = LoggerFactory.getLogger(SubirDataService.class);


    public ArrayList<SubirDataEntity> obtenerData(){
        ArrayList<SubirDataEntity> datasByDateAsc = dataRepository.findAllOrderByDateAsc();
        return datasByDateAsc;
    }

    public List<String> findAllByCodigoOrderByDateAsc(String codigo){
        List<String> datasByCodigo = dataRepository.findAllByCodigoOrderByDateAsc(codigo);
        return datasByCodigo;
    }


    public String guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }


    public void leerCsv(String direccion){
        String texto = "";
        BufferedReader bf = null;
        // Comenta o elimina la siguiente línea para no eliminar los registros previos
        dataRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2], bfRead.split(";")[3]);
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
            System.out.println("Archivo leido exitosamente");
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
    }


    public void guardarData(SubirDataEntity data){

        dataRepository.save(data);
    }


    public void guardarDataDB(String fecha, String turno, String proveedor, String kls_leche){
        SubirDataEntity newData = new SubirDataEntity();
        newData.setFecha(fecha);
        newData.setTurno(turno);
        newData.setProveedor(proveedor);
        newData.setKls_leche(kls_leche);
        guardarData(newData);
    }
}
