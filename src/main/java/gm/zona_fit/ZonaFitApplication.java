package gm.zona_fit;


import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;
import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio; 
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {

		logger.info("Inciando aplicación");
		//Levantar la fábrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);

		SpringApplication.run(ZonaFitApplication.class, args);

		logger.info("Aplicacion finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		appFit();
	}

	private void appFit(){
		logger.info("Aplicación Zona FIT (GYM)");
		var salir = false;
		var consola = new Scanner(System.in);

		while(!salir){
			try {
				
				int opcion = menuAplicacion(consola);
				salir = ejecutarOpciones(consola,opcion, clienteServicio);

			} catch (Exception e) {
				System.out.println("Error de aplicación: "+e.getMessage());
			}
		}
	
	}

	private int menuAplicacion(Scanner consola){

		System.out.printf("""
				Seleccione una opción:
				1.Listar clientes.
				2.Agregar cliente.
				3.Modificar cliente.
				4.Eliminar cliente.
				5.Salir.
				Opcion: \s""");

		return Integer.parseInt(consola.nextLine());		

	}

	private boolean ejecutarOpciones(Scanner consola, int opcion, IClienteServicio cServicio){

		var salir = false;

		try {

			switch (opcion) {
				case 1->listarClientesApp(cServicio);
				case 2->agregarClienteApp(consola);
				case 3->modificarClienteApp(consola,clienteServicio);
				case 4->eliminarclienteapp(consola,clienteServicio);
				case 5->{
					salir=true;
					System.out.println("Gracias por usar la Zona Fit App");	
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Error opcion: "+e.getMessage());
		}

		return salir;
	}

	private void listarClientesApp(IClienteServicio cServicio){
		var clientes = cServicio.listarClientes();
		System.out.println("\nLos clientes son:");
		for (Cliente cliente : clientes) {
			System.out.println("\t"+cliente.toString());
			
		}
		
		
	}

	private void agregarClienteApp(Scanner consola){

		System.out.println("Por favor ingrese los datos del cliente-->");
		System.out.print("\tNombre: ");
		var nombre=consola.nextLine();
		System.out.print("\tApellido: ");
		var apellido = consola.nextLine();
		System.out.print("\tMembresia: ");
		var membresia = Integer.parseInt(consola.nextLine());

		var nuevoCliente = new Cliente(null,nombre,apellido,membresia);
		clienteServicio.guardarCliente(nuevoCliente);
		System.out.println("Cliente guardado !");
		
	}

	private void modificarClienteApp(Scanner consola, IClienteServicio csServicio){
		System.out.print("Ingrese ID del cliente: ");
		var idModificar = Integer.parseInt(consola.nextLine());

		var clienteModificar = csServicio.buscarClienteID(idModificar);

		if(clienteModificar != null){
			System.out.println("Ingrese los datos a actualizar-->");
			System.out.print("\tNombre: ");
			clienteModificar.setNombre(consola.nextLine());
			System.out.print("\tApellido: ");
			clienteModificar.setApellido(consola.nextLine()); 
			System.out.print("\tMembresia: ");
			clienteModificar.setMembresia(Integer.parseInt(consola.nextLine()));

			csServicio.guardarCliente(clienteModificar);
		}else{
			System.out.print("Desea crear el nuevo cliente? (Si/No): ");
			var crearCliente = consola.nextLine().toLowerCase().charAt(0);
			if(crearCliente=='s')
				agregarClienteApp(consola);
		}
	
	}

	private void eliminarclienteapp(Scanner consola,IClienteServicio cServicio){

		System.out.print("Ingrese ID del cliente: ");
		var idEliminar = Integer.parseInt(consola.nextLine());
		var clienteEliminar = cServicio.buscarClienteID(idEliminar);

		if(clienteEliminar != null){
			cServicio.eliminarCliente(clienteEliminar);
			System.out.println("\tEl cliente ha sido eliminado !\n");
		}else{
			System.out.println("Cliente no encontrado. Intente nuevamente.\n");
		}

	}

	
}
