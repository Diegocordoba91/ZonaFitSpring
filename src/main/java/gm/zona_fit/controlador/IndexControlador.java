package gm.zona_fit.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.View;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;

//import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
import org.joinfaces.autoconfigure.primefaces.PrimefacesAutoConfiguration.Primefaces10_0_0AutoConfiguration;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component//Componente para la fabrica de Spring
@Data//Genera los metodos get y set de forma automatica
@ViewScoped//La aplicación solo tiene una página, permite visualizar la información de la BD, siempre que la vista
//este activa

//Esta clase

public class IndexControlador {
    
    @Autowired
    IClienteServicio clienteServicio;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);
    //El logger permite enviar información a consolta
    
    @PostConstruct//Despues de llamar el contructor de la clase, se envía a llamar el método
    public void init(){//Carga la información de la base de datos
        cargarDatos();
    }


    public void cargarDatos(){//Obtiene el listado de clientes.
        this.clientes = this.clienteServicio.listarClientes();
        for (Cliente cliente : clientes) {
            logger.info(cliente.toString());
        
        }
    }

    public void agregarCliente(){
        this.clienteSeleccionado = new Cliente();
        
    }

    public void guardarCliente(){
        logger.info("Cliente a guardar: "+this.clienteSeleccionado);

        if(this.clienteSeleccionado.getId()==null){//Insert
            this.clienteServicio.guardarCliente(clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Agregado"));

        }else{//update
            this.clienteServicio.guardarCliente(clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Actualizado"));

        }
        //Ocultar ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
        //Actualizar la tabla usando ajax
        PrimeFaces.current().ajax().update("forma-clientes:mensajes","forma-clientes:clientes-tabla");
        
        //Resetear el objeto cliente seleccionado
        this.clienteSeleccionado = null;
    }

    public void eliminarCliente(){
        logger.info("Cliente a eliminar: "+ clienteSeleccionado);

        this.clienteServicio.eliminarCliente(clienteSeleccionado);
        
        //Eliminar el cliente seleccionado
        this.clientes.remove(clienteSeleccionado);

        //Resetear cliente seleccionado
        this.clienteSeleccionado = null;
        //Se envia mensaje a pantalla y se actualiza la tabla de la vista principal
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Eliminado"));
        PrimeFaces.current().ajax().update("forma-clientes:mensajes","forma-clientes:clientes-tabla");
    }

}
