package avis.repositories;

import avis.HibernateUtil;
import avis.entities.Clientes;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RepoClientes {

    public boolean insertarCliente(Clientes cliente) {

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();


        session.save(cliente);
        
        
        session.getTransaction().commit();

        session.close();

        return true;
    }

    public boolean buscarClientePorDNI(Integer dni) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Clientes cliente = (Clientes) session.createQuery("from Clientes WHERE dni = :dniBuscar").setParameter("dniBuscar", dni).uniqueResult();

        session.close();

        if (cliente.getNombre() == null) {
            return false;
        } else {
            return true;
        }

    }

}
