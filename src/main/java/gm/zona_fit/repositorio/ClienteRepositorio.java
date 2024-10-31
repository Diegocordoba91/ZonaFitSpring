package gm.zona_fit.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gm.zona_fit.modelo.Cliente;

//La calse JpaRepository es una interfaz que facilita el modelo CRUD para interactuar con la base de datos
//(Create, )

//Se indica el tipo de dato que se esta trabajando Cliente(clase) y el tipo de la llave primaria
//@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,Integer>{
    
}
