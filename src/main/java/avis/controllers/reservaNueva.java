package avis.controllers;

import avis.entities.Clientes;
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
public class reservaNueva {

    @Autowired
    private RepoReservas repositorioReservas;
    @Autowired
    private RepoVehiculos repositorioVehiculos;
    @Autowired
    private RepoClientes repositorioClientes;

    @PostMapping("/agregarReservaVendedor")
    public String reservaNuevaVendedor(Model model, @RequestParam(name = "dni", required = true) Integer dni, @RequestParam(name = "nombre", required = true) String nombre, @RequestParam(name = "telefono", required = true) Integer telefono, @RequestParam(name = "direccion", required = true) String direccion, @RequestParam(name = "vehiculos", required = true) String patente) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha_inicio = dtf.format(LocalDateTime.now());

        Reservas reserva = new Reservas();
        Vehiculos vehiculo = new Vehiculos();
        Clientes cliente = new Clientes();
        
        reserva.setDni_cliente(dni);
        reserva.setFecha_fin(null);
        reserva.setFecha_inicio(fecha_inicio);
        reserva.setId_reserva(0);
        reserva.setPatente(patente);
        reserva.setPrecio_total(repositorioVehiculos.vehiculoBuscar(patente).getPrecio());
        
        cliente.setDireccion(direccion);
        cliente.setDni(dni);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        
       repositorioClientes.insertarCliente(cliente);
        
        vehiculo = repositorioVehiculos.vehiculoBuscar(patente);
        vehiculo.setEstado(true);
        repositorioVehiculos.actualizarEstado(vehiculo);
        
        repositorioReservas.insertar(reserva);

        model.addAttribute("error", "La reserva se cargo con exito");
        
        return "index";    //mensaje de carga exitosa
    }

    @GetMapping("/cancelar")
    public String mostrarPanelVendedor(Model model) {
        return "index"; 
    }

}
