package interfaz;

import java.util.List;
import java.util.Scanner;

import mundo.Administrador;
import mundo.Calificacion;
import mundo.CodigoDuplicadoException;
import mundo.Curso;
import mundo.Estudiante;
import mundo.PrerrequisitoNoCumplidoException;
import mundo.RegistroNoEncontradoException;
import mundo.SistemaAcademico;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static SistemaAcademico sistema = new SistemaAcademico();

    public static void main(String[] args) {
        sistema.cargarDatosDeEjemplo();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            ejecutarOpcion(opcion);
        } while (opcion != 0);

        System.out.println("Fin del programa.");
    }

    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE GESTIÓN ACADÉMICA =====");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Registrar curso");
        System.out.println("3. Listar estudiantes");
        System.out.println("4. Listar cursos");
        System.out.println("5. Buscar estudiante por ID");
        System.out.println("6. Matricular curso");
        System.out.println("7. Registrar calificación");
        System.out.println("8. Actualizar curso");
        System.out.println("9. Eliminar estudiante");
        System.out.println("10. Eliminar curso");
        System.out.println("0. Salir");
    }

    private static void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarEstudiante();
                case 2 -> registrarCurso();
                case 3 -> listarEstudiantes();
                case 4 -> listarCursos();
                case 5 -> buscarEstudiante();
                case 6 -> matricularCurso();
                case 7 -> registrarCalificacion();
                case 8 -> actualizarCurso();
                case 9 -> eliminarEstudiante();
                case 10 -> eliminarCurso();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } catch (CodigoDuplicadoException | RegistroNoEncontradoException | PrerrequisitoNoCumplidoException ex) {
// Manejo centralizado de las 3 excepciones propias del sistema
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    private static void registrarEstudiante() throws CodigoDuplicadoException {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Facultad: ");
        String facultad = scanner.nextLine();
        System.out.print("Programa: ");
        String programa = scanner.nextLine();

        sistema.registrarEstudiante(new Estudiante(id, nombre, contrasena, programa, facultad));
        System.out.println("Estudiante registrado.");
    }

    private static void registrarCurso() throws CodigoDuplicadoException {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        int creditos = leerEntero("Créditos: ");
        System.out.print("Programa: ");
        String programa = scanner.nextLine();
        System.out.print("Prerrequisitos (códigos separados por coma, o vacío): ");
        String prereqTexto = scanner.nextLine();

        Curso c = new Curso(codigo, nombre, creditos, programa);
        if (!prereqTexto.isBlank()) {
            for (String p : prereqTexto.split(",")) {
                c.agregarPrerrequisito(p.trim().toUpperCase());
            }
        }
        sistema.registrarCurso(c);
        System.out.println("Curso registrado.");
    }

    private static void listarEstudiantes() {
        List<Estudiante> lista = sistema.getTodosLosEstudiantes();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        // Se recorre como Identificable para demostrar el polimorfismo por interfaz
        for (Estudiante e : lista) {
            sistema.imprimirFicha(e);
        }
    }

    private static void listarCursos() {
        List<Curso> lista = sistema.getTodosLosCursos();
        if (lista.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        for (Curso c : lista) {
            sistema.imprimirFicha(c); // mismo método, distinta clase real: POLIMORFISMO
        }
    }

    private static void buscarEstudiante() throws RegistroNoEncontradoException {
        System.out.print("ID a buscar: ");
        String id = scanner.nextLine();
        Estudiante e = sistema.buscarEstudiantePorId(id);
        System.out.println("Encontrado: " + e.getNombre() + " - " + e.getPrograma()
                + " - Semestre " + e.getSemestreActual() + " - GPA " + e.getPromedioPonderado());
    }

    private static void matricularCurso() throws RegistroNoEncontradoException, PrerrequisitoNoCumplidoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        System.out.print("Código curso: ");
        String codigo = scanner.nextLine();
        sistema.matricularCurso(id, codigo);
        System.out.println("Curso matriculado.");
    }

    private static void registrarCalificacion() throws RegistroNoEncontradoException {
        System.out.print("ID estudiante: ");
        String id = scanner.nextLine();
        System.out.print("Código curso: ");
        String codigo = scanner.nextLine();
        double valor = leerDouble("Valor (0.0 - 5.0): ");
        int creditos = leerEntero("Créditos del curso: ");
        int semestre = leerEntero("Semestre: ");
        sistema.registrarCalificacion(new Calificacion(id, codigo, valor, creditos, semestre));
        System.out.println("Calificación registrada y promedio actualizado.");
    }

    private static void actualizarCurso() throws RegistroNoEncontradoException {
        System.out.print("Código del curso a actualizar: ");
        String codigo = scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        int creditos = leerEntero("Nuevos créditos: ");
        sistema.actualizarCurso(codigo, nombre, creditos);
        System.out.println("Curso actualizado.");
    }

    private static void eliminarEstudiante() throws RegistroNoEncontradoException {
        System.out.print("ID del estudiante a eliminar: ");
        String id = scanner.nextLine();
        sistema.eliminarEstudiante(id);
        System.out.println("Estudiante eliminado.");
    }

    private static void eliminarCurso() throws RegistroNoEncontradoException {
        System.out.print("Código del curso a eliminar: ");
        String codigo = scanner.nextLine();
        sistema.eliminarCurso(codigo);
        System.out.println("Curso eliminado.");
    }

//utilidades de lectura segura
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpia el salto de línea pendiente
        return valor;
    }

    private static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}