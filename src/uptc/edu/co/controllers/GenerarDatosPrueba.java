/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uptc.edu.co.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uptc.edu.co.models.persistence.PersistenceManager;
import uptc.edu.co.models.session.PomodoroRecord;
import uptc.edu.co.models.session.PomodoroType;
import uptc.edu.co.models.session.Session;
import uptc.edu.co.models.user.Role;
import uptc.edu.co.models.user.User;

/**
 *
 * @author alber
 */
public class GenerarDatosPrueba {

    private final PersistenceManager manager;
    private final Random random;

    public GenerarDatosPrueba() {
        this.manager = new PersistenceManager();
        this.random = new Random();
    }

    /**
     * Genera usuarios de prueba con sesiones de estudio
     */
    public void generarDatosPrueba() throws IOException {
        System.out.println("🔧 Generando datos de prueba...");

        // Crear usuarios de prueba
        List<User> usuariosPrueba = crearUsuariosPrueba();
        
        // Guardar usuarios
        for (User usuario : usuariosPrueba) {
            manager.addUser(usuario);
        }

        // Crear sesiones con horas de estudio para cada usuario
        for (User usuario : usuariosPrueba) {
            crearSesionesParaUsuario(usuario);
        }

        System.out.println("✅ Datos de prueba generados exitosamente");
        System.out.println("📊 Total de usuarios creados: " + usuariosPrueba.size());
    }

    /**
     * Crea lista de usuarios de prueba
     */
    private List<User> crearUsuariosPrueba() {
        List<User> usuarios = new ArrayList<>();
        
        usuarios.add(new User(1000, "Ana García", "ana.garcia@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1001, "Carlos Ruiz", "carlos.ruiz@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1002, "María López", "maria.lopez@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1003, "Juan Pérez", "juan.perez@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1004, "Laura Martínez", "laura.martinez@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1005, "Diego Torres", "diego.torres@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1006, "Sofía Ramírez", "sofia.ramirez@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1007, "Pablo Vargas", "pablo.vargas@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1008, "Camila Rojas", "camila.rojas@test.com", "Test123!", Role.USER));
        usuarios.add(new User(1009, "Andrés Silva", "andres.silva@test.com", "Test123!", Role.ADMIN));

        return usuarios;
    }

    /**
     * Crea sesiones de estudio con diferentes duraciones para un usuario
     */
    private void crearSesionesParaUsuario(User usuario) {
        // Determinar cuántas horas de estudio tendrá este usuario (entre 5 y 80 horas)
        int horasTotales = 5 + random.nextInt(76);
        
        // Crear múltiples sesiones para simular uso real
        int numeroSesiones = 3 + random.nextInt(8); // Entre 3 y 10 sesiones
        
        for (int i = 0; i < numeroSesiones; i++) {
            Session sesion = crearSesionConHoras(usuario, horasTotales / numeroSesiones, i);
            manager.saveSession(usuario.getId(), sesion);
        }

        System.out.println("   ✓ Usuario: " + usuario.getName() + " - " + horasTotales + " horas");
    }

    /**
     * Crea una sesión con un número específico de horas de estudio
     */
    private Session crearSesionConHoras(User usuario, int horas, int numeroSesion) {
        Session sesion = new Session(usuario, "Sesión de estudio #" + (numeroSesion + 1));
        
        // Fecha de inicio (días atrás aleatorios)
        LocalDateTime fechaInicio = LocalDateTime.now().minusDays(random.nextInt(30));
        sesion.setStartTime(fechaInicio);
        
        // Fecha de fin (sumando las horas de estudio)
        LocalDateTime fechaFin = fechaInicio.plusHours(horas).plusMinutes(random.nextInt(60));
        sesion.setEndTime(fechaFin);

        // Crear registros de pomodoros para hacer más realista
        List<PomodoroRecord> registros = generarPomodorosParaHoras(horas, fechaInicio);
        for (PomodoroRecord record : registros) {
            sesion.addPomodoroRecord(record);
        }

        return sesion;
    }

    /**
     * Genera pomodoros realistas para una cantidad de horas
     */
    private List<PomodoroRecord> generarPomodorosParaHoras(int horas, LocalDateTime inicio) {
        List<PomodoroRecord> registros = new ArrayList<>();
        LocalDateTime tiempo = inicio;
        
        // Aproximadamente 2 pomodoros por hora (25 min trabajo + 5 min descanso)
        int numeroPomodoros = horas * 2;
        
        for (int i = 0; i < numeroPomodoros; i++) {
            // Pomodoro de trabajo (25 minutos)
            LocalDateTime finTrabajo = tiempo.plusMinutes(25);
            PomodoroRecord trabajo = new PomodoroRecord(
                PomodoroType.WORK,
                tiempo,
                finTrabajo,
                true, // completado
                25 * 60 // 25 minutos en segundos
            );
            registros.add(trabajo);
            
            tiempo = finTrabajo;
            
            // Descanso corto cada 4 pomodoros se hace largo
            boolean esDescansoLargo = (i + 1) % 4 == 0;
            int minutosDescanso = esDescansoLargo ? 15 : 5;
            PomodoroType tipoDescanso = esDescansoLargo ? PomodoroType.LONG_BREAK : PomodoroType.SHORT_BREAK;
            
            LocalDateTime finDescanso = tiempo.plusMinutes(minutosDescanso);
            PomodoroRecord descanso = new PomodoroRecord(
                tipoDescanso,
                tiempo,
                finDescanso,
                true,
                minutosDescanso * 60
            );
            registros.add(descanso);
            
            tiempo = finDescanso;
        }
        
        return registros;
    }

    /**
     * Método principal para ejecutar la generación
     */
    public static void main(String[] args) {
        try {
            GenerarDatosPrueba generador = new GenerarDatosPrueba();
            generador.generarDatosPrueba();
        } catch (IOException e) {
            System.err.println("❌ Error al generar datos de prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
