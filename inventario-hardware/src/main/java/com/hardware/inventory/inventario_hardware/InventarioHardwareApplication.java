package com.hardware.inventory.inventario_hardware;

import com.hardware.inventory.inventario_hardware.entity.Hardware;
import com.hardware.inventory.inventario_hardware.service.HardwareService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class InventarioHardwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioHardwareApplication.class, args);
	}

	// Este Bean hará que los datos se carguen apenas inicies la app (Run) [cite:
	// 33, 40]
	@Bean
	CommandLineRunner initDatabase(HardwareService service) {
		return args -> {
			List<Hardware> lista = new ArrayList<>();
			for (int i = 1; i <= 100; i++) {
				// Usamos el constructor de la entidad con Lombok
				lista.add(new Hardware(null, "Componente inicial " + i, "Hardware", 50.0));
			}
			service.guardarMuchos(lista);
			System.out.println("Base de datos cargada con 100 registros para visualización en H2.");
		};
	}
}