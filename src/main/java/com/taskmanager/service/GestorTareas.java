package com.taskmanager.service;

import com.taskmanager.model.Tarea;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorTareas {
    private List<Tarea> tareas = new ArrayList<>();

    public void agregarTarea(String titulo, String descripcion) {
        tareas.add(new Tarea(titulo, descripcion));
    }

    public boolean completarTarea(String titulo) {
        for (Tarea t : tareas) {
            if (t.getTitulo().equalsIgnoreCase(titulo)) {
                t.completar();
                return true;
            }
        }
        return false;
    }

    public List<Tarea> filtrarPorEstado(boolean completada) {
        return tareas.stream().filter(t -> t.isCompletada() == completada).collect(Collectors.toList());
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
