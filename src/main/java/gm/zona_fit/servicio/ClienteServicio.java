package gm.zona_fit.servicio;

import java.util.List;

import org.hibernate.boot.model.internal.CreateKeySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.repositorio.ClienteRepositorio;

@Service
public class ClienteServicio  implements IClienteServicio{

    @Autowired//Inyecta una referencia de la capa de datos de repositorio
    private ClienteRepositorio cRepositorio;//Permite manipular los objetos de la base de datos como si fuera una clase.

    @Override
    public List<Cliente> listarClientes() {
        //Es método lista todos los objetos de tipo cliente de la base de datos
        List<Cliente> clientes =  cRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClienteID(Integer idCliente) {
        Cliente cliente = cRepositorio.findById(idCliente).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        cRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        cRepositorio.delete(cliente);
    }

}
