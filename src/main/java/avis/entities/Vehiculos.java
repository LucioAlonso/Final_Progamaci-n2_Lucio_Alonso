package avis.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculos")
public class Vehiculos implements Serializable {

    @Id
    private Integer tanque, modelo;
    private Double precio;
    private boolean estado;
    private String patente, marca, color;
}
