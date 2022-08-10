package avis.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {

    @Id
    private Integer dni, telefono;
    private String usuario, contr, tipo, nombre, apellido, mail;
}
