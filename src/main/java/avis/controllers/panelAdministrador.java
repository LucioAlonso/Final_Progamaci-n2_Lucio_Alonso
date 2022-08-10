package avis.controllers;

import avis.entities.Reservas;
import avis.entities.Vehiculos;
import avis.repositories.RepoReservas;
import avis.repositories.RepoVehiculos;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class panelAdministrador {

    @Autowired
    private RepoVehiculos repositorioVehiculos;
    @Autowired
    private RepoReservas repositorioReservas;

    @PostMapping("/consultarAdmin")
    public String consutaPorDNIAdmin(Model model, @RequestParam(name = "dni_cliente", required = true) Integer dni) {

        if (repositorioReservas.validarReservas(dni) == true) {
            model.addAttribute("listaAct", repositorioReservas.obtenerReservasActPorDNI(dni));
            model.addAttribute("listaFin", repositorioReservas.obtenerReservasFinPorDNI(dni));
        } else {
            model.addAttribute("error", "No se registraron reservas con ese DNI");
        }

        return "panelAdministrador";
    }

    @GetMapping("/cancelarNuevo")
    public String vistaAdministrador(Model model) {
        return "panelAdministrador";
    }

    @GetMapping("/nuevaReservaAdmin")
    public String mostrarNuevaReservaAdmin(Model model) {
        model.addAttribute("lista", repositorioVehiculos.vehiculosDisponibles());
        return "reservaNueva";
    }

    @GetMapping("/vistaVehiculoNuevo")
    public String vistaVehiculosNuevo(Model model) {
        return "vehiculoNuevo";
    }

    @PostMapping("/finalizarReserva")
    public String finalizadorReserva(Model model, @RequestParam(name = "reservasAct") Integer id_reserva) throws ParseException {

        Reservas reserva = new Reservas();
        Vehiculos vehiculo = new Vehiculos();

        reserva = repositorioReservas.obtenerReservasActPorId(id_reserva);
        repositorioReservas.actualizarEstado(reserva);

        vehiculo = repositorioVehiculos.vehiculoBuscar(reserva.getPatente());
        vehiculo.setEstado(false);
        repositorioVehiculos.actualizarEstado(vehiculo);

        model.addAttribute("listaAct", repositorioReservas.obtenerReservasActPorDNI(reserva.getDni_cliente()));
        model.addAttribute("listaFin", repositorioReservas.obtenerReservasFinPorDNI(reserva.getDni_cliente()));

        return "panelAdministrador";
    }

}
