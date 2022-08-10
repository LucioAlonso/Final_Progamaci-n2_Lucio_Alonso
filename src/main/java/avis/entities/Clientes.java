package avis.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Clientes implements Serializable {

    @Id
    private Integer dni, telefono;
    private String nombre, direccion;
}
