package com.hardware.inventory.inventario_hardware;

import com.hardware.inventory.inventario_hardware.entity.Hardware;
import com.hardware.inventory.inventario_hardware.service.HardwareService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

/**
 * Clase principal de la aplicación. Es el punto de arranque del proyecto.
 */
@SpringBootApplication // Activa la configuración automática de Spring Boot (escanea capas, configura BD, etc.)
public class InventarioHardwareApplication {

    /** Método principal: inicia toda la aplicación Spring Boot. */
    public static void main(String[] args) {
        SpringApplication.run(InventarioHardwareApplication.class, args);
    }

    /**
     * Se ejecuta automáticamente una vez que la aplicación arranca.
     * Su función es precargar 100 registros de ejemplo en la base de datos H2
     * para que puedan visualizarse en la consola web en http://localhost:8081/h2-console/
     */
    @Bean // Spring gestiona este método como un componente más del sistema
    CommandLineRunner initDatabase(HardwareService service) {
        return args -> {
            // Genera 100 objetos Hardware numerados del 1 al 100 y los guarda en la BD
            service.guardarMuchos(
                IntStream.rangeClosed(1, 100)
                    .mapToObj(i -> new Hardware(null, "Componente inicial " + i, "Hardware", 50.0))
                    .toList()
            );
            System.out.println("Base de datos cargada con 100 registros.");
        };
    }
}
