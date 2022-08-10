package avis.controllers;

import avis.entities.Reservas;
import avis.repositories.RepoReservas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Index {

    @Autowired
    private RepoReservas repositorioReservas;

    @GetMapping("/")
    public String mostrarIndex(Model model) {
        //model.addAttribute("lista", repositorioReservas.obtenerTodos());
        return "index";
    }

    @PostMapping("/consultarCliente")
    public String consutaPorDNI(Model model, @RequestParam(name = "dni_cliente", required = true) Integer dni) {

        if (repositorioReservas.validarReservas(dni) == true) {
            model.addAttribute("listaAct", repositorioReservas.obtenerReservasActPorDNI(dni));
            model.addAttribute("listaFin", repositorioReservas.obtenerReservasFinPorDNI(dni));
        } else model.addAttribute("error", "No se registraron reservas con ese DNI");

        return "index";
    }

    @PostMapping("/iniciar")
    public String mostrarInicioSesion(Model model) {
        return "inicioSesion";
    }
}
