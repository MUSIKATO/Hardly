package com.hardware.inventory.inventario_hardware.service;

import com.hardware.inventory.inventario_hardware.entity.Hardware;
import com.hardware.inventory.inventario_hardware.repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de servicio (Service) para operaciones sobre Hardware.
 *
 * Actúa como intermediaria entre los tests/controladores y la base de datos.
 * Contiene la lógica de negocio del proyecto.
 */
@Service // Le dice a Spring que gestione esta clase como un componente de servicio
public class HardwareService {

    // Referencia al repositorio. Spring la provee automáticamente (DI = Inyección de Dependencias)
    private final HardwareRepository repository;

    /**
     * Constructor: Spring detecta que necesita un HardwareRepository y lo inyecta solo.
     * Esto es IoC (Inversión de Control): Spring controla la creación del objeto.
     */
    public HardwareService(HardwareRepository repository) {
        this.repository = repository;
    }

    /** Guarda todos los objetos de la lista en la base de datos de una sola vez. */
    public void guardarMuchos(List<Hardware> lista) {
        repository.saveAll(lista);
    }

    /** Devuelve el total de registros que hay actualmente en la tabla. */
    public long contarTodo() {
        return repository.count();
    }

    /** Obtiene todos los registros y devuelve únicamente los primeros 25. */
    public List<Hardware> obtenerPrimeros25() {
        return repository.findAll().stream().limit(25).toList();
    }

    /**
     * Busca un registro por su ID y le cambia el nombre.
     * Si el ID no existe, devuelve null sin lanzar error.
     */
    public Hardware actualizar(Long id, String nuevoNombre) {
        Hardware h = repository.findById(id).orElse(null);
        if (h != null) {
            h.setNombre(nuevoNombre);
            return repository.save(h); // Persiste el cambio en la base de datos
        }
        return null;
    }

    /** Elimina de la base de datos el registro que tenga el ID indicado. */
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    /**
     * Busca y devuelve el registro con el ID indicado.
     * Si no existe, devuelve null.
     */
    public Hardware buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
