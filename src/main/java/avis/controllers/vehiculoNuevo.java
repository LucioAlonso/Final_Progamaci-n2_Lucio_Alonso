package avis.controllers;

import avis.entities.Reservas;
import avis.entities.Vehiculos;
import avis.repositories.RepoClientes;
import avis.repositories.RepoReservas;
import avis.repositories.RepoVehiculos;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class vehiculoNuevo {

    @Autowired
    private RepoReservas repositorioReservas;
    @Autowired
    private RepoVehiculos repositorioVehiculos;
    @Autowired
    private RepoClientes repositorioClientes;

    @PostMapping("/cargarVehiculoNuevo")
    public String insertarVehiculoNuevo(Model model, @RequestParam(name = "patente", required = true) String patente, @RequestParam(name = "marca", required = true) String marca, @RequestParam(name = "modelo", required = true) Integer modelo, @RequestParam(name = "color", required = true) String color, @RequestParam(name = "tanque", required = true) Integer tanque, @RequestParam(name = "precio", required = true) Double precio) {

        Vehiculos vehiculo = new Vehiculos();
        
        vehiculo.setColor(color);
        vehiculo.setEstado(false);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setPatente(patente);
        vehiculo.setPrecio(precio);
        vehiculo.setTanque(tanque);
        
        repositorioVehiculos.insertar(vehiculo);
        model.addAttribute("error", "Vehiculo cargado con exito");
        return "panelAdministrador";
    }
    
    
    
}
