package com.taskmanager.service;

import com.taskmanager.model.Tarea;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GestorTareasTest {

    private GestorTareas gestor;
    private String tareaTitulo;

    @BeforeClass
    @Parameters({ "titulo", "descripcion" })
    public void setUp(String titulo, String descripcion) {
        gestor = new GestorTareas();
        gestor.agregarTarea(titulo, descripcion);
        tareaTitulo = titulo;
    }

    @Test(priority = 1)
    @Parameters({ "titulo", "descripcion" })
    public void testAgregarTarea(String titulo, String descripcion) {
        int tamañoInicial = gestor.getTareas().size();
        gestor.agregarTarea(titulo, descripcion);
        Assert.assertEquals(gestor.getTareas().size(), tamañoInicial + 1, "La tarea no fue agregada correctamente");
    }

    @Test(priority = 2)
    public void testCompletarTareaExistente() {
        boolean resultado = gestor.completarTarea(tareaTitulo);
        Assert.assertTrue(resultado, "No se pudo completar la tarea existente");
    }

    @Test(priority = 3)
    public void testFiltrarPorCompletadas() {
        List<Tarea> completadas = gestor.filtrarPorEstado(true);
        Assert.assertFalse(completadas.isEmpty(), "No se encontraron tareas completadas");
    }

    @Test(priority = 4)
    public void testFiltrarPorPendientes() {
        List<Tarea> pendientes = gestor.filtrarPorEstado(false);
        Assert.assertFalse(pendientes.isEmpty(), "No se encontraron tareas pendientes");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Pruebas finalizadas.");
    }
}