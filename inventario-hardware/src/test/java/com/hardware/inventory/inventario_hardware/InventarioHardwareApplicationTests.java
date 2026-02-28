package com.hardware.inventory.inventario_hardware;

import com.hardware.inventory.inventario_hardware.entity.Hardware;
import com.hardware.inventory.inventario_hardware.repository.HardwareRepository;
import com.hardware.inventory.inventario_hardware.service.HardwareService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("dev") // Asegura el uso del perfil solicitado [cite: 32]
class InventarioHardwareApplicationTests {

	@Autowired // Implementación de DI (Inyección de Dependencias)
	private HardwareService service;

	@Autowired
	private HardwareRepository repository;

	@Test
	void insertar_registros_test() { // Prueba Unitaria #1 [cite: 39]
		repository.deleteAll(); // Empezar con tabla vacía para que el conteo sea exactamente 100
		List<Hardware> lista = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			lista.add(new Hardware(null, "Componente " + i, "Hardware", 25.0));
		}

		// Servicio para insertar 100 registros [cite: 40]
		service.guardarMuchos(lista);

		// Comprobar con assertEqual el total [cite: 41, 42]
		assertEquals(100, service.contarTodo());
	}

	@Test
	void leer_registros_test() { // Prueba Unitaria #2 [cite: 43]
		// Preparar datos: insertar al menos 25 registros para esta prueba aislada
		List<Hardware> lista = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			lista.add(new Hardware(null, "Componente " + i, "Hardware", 25.0));
		}
		service.guardarMuchos(lista);

		// Servicio que recupera los primeros 25 [cite: 44]
		List<Hardware> primeros25 = service.obtenerPrimeros25();

		// Comprobar con assertEqual que el total es 25 [cite: 46]
		assertEquals(25, primeros25.size());
	}

	@Test
	void modificar_registro_test() { // Prueba Unitaria #3 [cite: 47]
		// Preparar datos: insertar al menos 50 registros; usar el ID real del 50º
		List<Hardware> lista = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			lista.add(new Hardware(null, "Componente " + i, "Hardware", 25.0));
		}
		service.guardarMuchos(lista);
		Long idRegistro50 = lista.get(49).getId(); // 50º elemento (índice 49)

		String nuevoNombre = "Procesador Actualizado";

		// Modificar el registro del 50º [cite: 48]
		service.actualizar(idRegistro50, nuevoNombre);

		// Obtener el registro modificado [cite: 49]
		Hardware modificado = service.buscarPorId(idRegistro50);

		// Comprobar con assertEqual el nuevo valor [cite: 50]
		assertEquals(nuevoNombre, modificado.getNombre());
	}

	@Test
	void borrar_resgitro_test() { // Prueba Unitaria #4
		// Preparar datos: insertar al menos 75 registros; usar el ID real del 75º
		List<Hardware> lista = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			lista.add(new Hardware(null, "Componente " + i, "Hardware", 25.0));
		}
		service.guardarMuchos(lista);
		Long idRegistro75 = lista.get(74).getId(); // 75º elemento (índice 74)

		// Servicio para borrar el 75º registro [cite: 52]
		service.eliminar(idRegistro75);

		// Comprobar con assertNull que ha sido borrado [cite: 53]
		Hardware eliminado = service.buscarPorId(idRegistro75);
		assertNull(eliminado);
	}
}