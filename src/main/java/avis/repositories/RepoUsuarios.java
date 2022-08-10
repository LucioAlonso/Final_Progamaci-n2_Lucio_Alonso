package avis.repositories;

import avis.HibernateUtil;
import avis.entities.Clientes;
import avis.entities.Reservas;
import avis.entities.Usuarios;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public class RepoUsuarios {

    public Usuarios validarUsuario(String user, String pwd) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Usuarios usr = new Usuarios();
        usr = (Usuarios) session.createQuery("from Usuarios WHERE (contr = :pwd) AND (usuario = :user)").setParameter("pwd", pwd).setParameter("user", user).uniqueResult();

        session.close();

        if (usr.getUsuario().equals(user)) {
            return usr;
        } else {

            usr.setUsuario(null);
            usr.setContr(null);
            usr.setApellido(null);
            usr.setDni(null);
            usr.setMail(null);
            usr.setNombre(null);
            usr.setTelefono(null);
            usr.setTipo(null);

        }
        return usr;
    }

    public boolean buscarUsuario(String user, String pwd) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Usuarios usuario = (Usuarios) session.createQuery("from Usuarios WHERE (usuario = :usuarioBuscar) AND (contr = :contrBuscar)").setParameter("usuarioBuscar", user).setParameter("contrBuscar", pwd).uniqueResult();

        session.close();

        if (usuario == null) {
            return false;
        } else {
            return true;
        }

    }
}
