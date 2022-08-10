package avis.controllers;

import avis.entities.Reservas;
import avis.entities.Vehiculos;
import avis.repositories.RepoReservas;
import avis.repositories.RepoVehiculos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class panelVendedor {

    @Autowired
    private RepoVehiculos repositorioVehiculos;
    @Autowired
    private RepoReservas repositorioReservas;

    @PostMapping("/consultarVendedor")
    public String consutaPorDNI(Model model, @RequestParam(name = "dni_cliente", required = true) Integer dni) {

        if (repositorioReservas.validarReservas(dni) == true) {
            model.addAttribute("listaAct", repositorioReservas.obtenerReservasActPorDNI(dni));
            model.addAttribute("listaFin", repositorioReservas.obtenerReservasFinPorDNI(dni));
        } else {
            model.addAttribute("error", "No se registraron reservas con ese DNI");
        }

        return "panelVendedor";
    }

    @GetMapping("/nuevaReservaVendedor")
    public String mostrarNuevaReserva(Model model) {
        model.addAttribute("lista", repositorioVehiculos.vehiculosDisponibles());
        return "reservaNueva";
    }

}
