package avis.repositories;

import avis.HibernateUtil;
import avis.entities.Reservas;
import avis.entities.Vehiculos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RepoReservas {

    public List<Reservas> obtenerTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Reservas");
        List<Reservas> lista = query.getResultList();

        session.close();

        return lista;
    }

    public List<Reservas> obtenerReservasFinPorDNI(Integer dni) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Reservas WHERE (dni_cliente = :dni) AND (fecha_fin != 'NULL')").setParameter("dni", dni);
        List<Reservas> listaFin = query.getResultList();

        session.close();

        return listaFin;
    }

    public Reservas obtenerReservasActPorId(Integer id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Reservas reserva = (Reservas) session.createQuery("from Reservas WHERE (id_reserva = :id) AND (fecha_fin IS NULL)").setParameter("id", id).uniqueResult();

        session.close();

        return reserva;
    }

    public boolean validarReservas(Integer dni) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Reservas reserva = new Reservas();
        
        Query query = session.createQuery("from Reservas WHERE dni_cliente = :dni").setParameter("dni", dni);
        List<Reservas> listaFin = query.getResultList();

        session.close();

        if(listaFin.isEmpty()){
            return false;
        } else return true;

    }

    public List<Reservas> obtenerReservasActPorDNI(Integer dni) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Reservas WHERE (dni_cliente = :dni) AND (fecha_fin IS NULL)").setParameter("dni", dni);
        List<Reservas> listaAct = query.getResultList();

        session.close();

        return listaAct;
    }

    public boolean insertar(Reservas reserva) {

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();

        session.save(reserva);       //aca subo los datos a la base de datos
        session.getTransaction().commit();

        session.close();

        return true;
    }

    public boolean actualizarEstado(Reservas reserva) throws ParseException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha_hoy = dtf.format(LocalDateTime.now());

        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();

        Double precio_total = reserva.getPrecio_total();

        precio_total = precio_total * diferenciarDias(reserva.getFecha_inicio(), fecha_hoy);

        reserva.setPrecio_total(precio_total);
        reserva.setFecha_fin(fecha_hoy);

        session.update(reserva);       //aca subo los datos a la base de datos
        session.getTransaction().commit();

        session.close();

        return true;
    }

    public int diferenciarDias(String fechaInicio, String fechaFin) throws ParseException {
        int dif = 1;

        java.util.Date fechaIncialdate = stringaFecha(fechaInicio);
        java.util.Date fechaFinaldate = stringaFecha(fechaFin);

        dif = (int) ((fechaFinaldate.getTime() - fechaIncialdate.getTime()) / (1000 * 60 * 60 * 24));

        if (dif == 0) {
            return 1;
        }
        return dif;
    }

    public java.util.Date stringaFecha(String fecha) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        java.util.Date fecha1 = formato.parse(fecha);

        return fecha1;
    }

}
