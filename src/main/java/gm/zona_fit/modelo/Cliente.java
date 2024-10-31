package gm.zona_fit.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity//JPA
@Data//Crea los métodos get y set del objeto
@NoArgsConstructor//Crea el método constructo vacío
@AllArgsConstructor//Crea el método constructor con todos los atributos
@ToString//Crea el método toString del objeto
@EqualsAndHashCode//Crea el método equals y hashcode
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//El ID se genera de forma automática en la bd y se declara acá tambie
    private Integer id;//LLave primaria
    private String nombre;
    private String apellido;
    private Integer membresia;

}
