package avis.repositories;

import avis.HibernateUtil;
import avis.entities.Vehiculos;
import avis.entities.Clientes;
import avis.entities.Reservas;
import avis.entities.Vehiculos;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RepoVehiculos {

    public List<Vehiculos> vehiculosDisponibles() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Vehiculos WHERE estado = 'si'");
        List<Vehiculos> listaVehiculos = query.getResultList();

        session.close();

        return listaVehiculos;
    }

    public Vehiculos vehiculoBuscar(String patente) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Vehiculos vehiculo = (Vehiculos) session.createQuery("from Vehiculos WHERE patente = :pate").setParameter("pate", patente).uniqueResult();

        session.close();

        return vehiculo;
    }

    public boolean actualizarEstado(Vehiculos vehiculo) {

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();

        session.update(vehiculo);       //aca subo los datos a la base de datos
        session.getTransaction().commit();

        session.close();

        return true;
    }
    
    public boolean insertar(Vehiculos vehiculo) {

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();

        session.save(vehiculo);       //aca subo los datos a la base de datos
        session.getTransaction().commit();
        
        session.close();

        return true;
    }
}
