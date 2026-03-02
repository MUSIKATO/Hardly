package com.hardware.inventory.inventario_hardware;

import com.hardware.inventory.inventario_hardware.entity.Hardware;
import com.hardware.inventory.inventario_hardware.repository.HardwareRepository;
import com.hardware.inventory.inventario_hardware.service.HardwareService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Pruebas unitarias del proyecto.
 * Verifican que cada operación sobre la base de datos funciona correctamente.
 */
@SpringBootTest         // Levanta toda la aplicación Spring Boot para ejecutar los tests
@ActiveProfiles("dev")  // Usa el perfil "dev", cargando application-dev.properties (base de datos H2 en memoria)
class InventarioHardwareApplicationTests {

    @Autowired // Spring inyecta automáticamente el servicio (DI = Inyección de Dependencias)
    private HardwareService service;

    @Autowired // Spring inyecta el repositorio para poder limpiar la tabla en los tests
    private HardwareRepository repository;

    /**
     * Método auxiliar reutilizable: genera una lista de objetos Hardware.
     * Evita repetir el mismo bloque de código en cada prueba.
     */
    private List<Hardware> crearLista(int cantidad) {
        return IntStream.rangeClosed(1, cantidad)
            .mapToObj(i -> new Hardware(null, "Componente " + i, "Hardware", 25.0))
            .toList();
    }

    /**
     * Prueba Unitaria #1: insertar_registros_test()
     * Inserta 100 registros y comprueba que el total en la base de datos sea exactamente 100.
     */
    @Test
    void insertar_registros_test() {
        repository.deleteAll();                  // Limpia la tabla para empezar desde cero
        service.guardarMuchos(crearLista(100));  // Inserta 100 registros usando el servicio
        assertEquals(100, service.contarTodo()); // Verifica que hay exactamente 100 registros
    }

    /**
     * Prueba Unitaria #2: leer_registros_test()
     * Verifica que el servicio devuelva exactamente 25 registros al leer los primeros.
     */
    @Test
    void leer_registros_test() {
        service.guardarMuchos(crearLista(100));               // Asegura que hay al menos 25 registros en la BD
        assertEquals(25, service.obtenerPrimeros25().size()); // Verifica que el servicio devuelve 25
    }

    /**
     * Prueba Unitaria #3: modificar_registro_test()
     * Modifica el nombre del registro 50 y verifica que el cambio quedó guardado correctamente.
     */
    @Test
    void modificar_registro_test() {
        List<Hardware> lista = crearLista(100);
        service.guardarMuchos(lista);
        Long id50 = lista.get(49).getId(); // Obtiene el ID real del 50º registro insertado (índice 49)

        service.actualizar(id50, "Procesador Actualizado"); // Cambia el nombre del registro 50

        // Verifica que el nombre guardado en la BD es el nuevo valor asignado
        assertEquals("Procesador Actualizado", service.buscarPorId(id50).getNombre());
    }

    /**
     * Prueba Unitaria #4: borrar_registro_test()
     * Borra el registro 75 y verifica que ya no existe en la base de datos.
     */
    @Test
    void borrar_registro_test() {
        List<Hardware> lista = crearLista(100);
        service.guardarMuchos(lista);
        Long id75 = lista.get(74).getId(); // Obtiene el ID real del 75º registro insertado (índice 74)

        service.eliminar(id75);            // Borra el registro 75 usando el servicio

        assertNull(service.buscarPorId(id75)); // Verifica que ya no existe (buscar devuelve null)
    }
}
