package avis.controllers;

import avis.HibernateUtil;
import avis.entities.Usuarios;
import avis.repositories.RepoReservas;
import avis.repositories.RepoUsuarios;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IniciarSesion {

    @Autowired
    private RepoUsuarios repositorioUsuarios;

    @PostMapping("/ingresar")
    public String validar(Model model, @RequestParam(value = "usr", required = true) String user,
            @RequestParam(value = "contra", required = true) String pwd) {

        
        
        if (repositorioUsuarios.buscarUsuario(user, pwd) == true) {
            
            Usuarios usuarioSeleccionado = repositorioUsuarios.validarUsuario(user, pwd);
            
            if ("admin".equals(usuarioSeleccionado.getTipo())) {

                return "panelAdministrador";

            } else if ("vendedor".equals(usuarioSeleccionado.getTipo())) {

                return "panelVendedor";
            }
        }
        
        model.addAttribute("error", "Usuario o contraseña invalidos");
        return "inicioSesion";     //ACA HACE LA VISTA ERROR

    }
}
