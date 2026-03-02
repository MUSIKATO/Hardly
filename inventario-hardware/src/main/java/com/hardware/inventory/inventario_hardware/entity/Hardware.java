package com.hardware.inventory.inventario_hardware.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa un componente de hardware en la base de datos.
 * Cada instancia de esta clase corresponde a una fila en la tabla "HARDWARE".
 */
@Entity            // Le indica a Spring que esta clase es una tabla de la base de datos
@Data              // Lombok genera automáticamente: getters, setters, equals, hashCode y toString
@NoArgsConstructor // Lombok genera un constructor vacío: new Hardware()
@AllArgsConstructor // Lombok genera un constructor con todos los campos: new Hardware(id, nombre, ...)
public class Hardware {

    @Id                                                 // Este campo es la clave primaria (identificador único de cada fila)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se asigna automáticamente: 1, 2, 3...
    private Long id;

    private String nombre;    // Nombre del componente, ej: "Componente inicial 1"
    private String categoria; // Categoría del componente, ej: "Hardware"
    private Double precio;    // Precio del componente, ej: 50.0
}
