package avis.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "reservas")
public class Reservas implements Serializable {

    @Id
    private Integer id_reserva, dni_cliente;
    private Double precio_total;
    private String patente, fecha_inicio, fecha_fin;

}
