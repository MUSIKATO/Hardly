package com.hardware.inventory.inventario_hardware.service;

import com.hardware.inventory.inventario_hardware.entity.Hardware;
import com.hardware.inventory.inventario_hardware.repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HardwareService {

    @Autowired // IoC y DI en acción
    private HardwareRepository repository;

    public void guardarMuchos(List<Hardware> lista) {
        repository.saveAll(lista);
    }

    public long contarTodo() {
        return repository.count();
    }

    public List<Hardware> obtenerPrimeros25() {
        return repository.findAll().stream().limit(25).collect(Collectors.toList());
    }

    public Hardware actualizar(Long id, String nuevoNombre) {
        Hardware h = repository.findById(id).orElse(null);
        if (h != null) {
            h.setNombre(nuevoNombre);
            return repository.save(h);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Hardware buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}