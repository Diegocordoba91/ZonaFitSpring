package gm.zona_fit;

import javax.swing.SwingUtilities;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication
public class ZonaFitSwing {

    public static void main(String[] args) {
        //Instanciar la fábrica de Sprint

        ConfigurableApplicationContext contextoSpring = 
        new SpringApplicationBuilder(ZonaFitSwing.class)
        .headless(false)
        .web(WebApplicationType.NONE)
        .run(args);

        //Crear un objeto de Swing

        /*SwingUtilities.invokeLater(()->{
            contextoSpring.getBean(ZonaFitForma.class)
        });*/
    }

    
}
