package gm.zona_fit.servicio;

import java.util.List;

import gm.zona_fit.modelo.Cliente;

public interface IClienteServicio {

    public List<Cliente> listarClientes();
    public Cliente buscarClienteID(Integer idCliente);
    
    //Este método guarda clientes nuevo y modifica los existentes con base en la llave primaria
    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente); 
    
}
