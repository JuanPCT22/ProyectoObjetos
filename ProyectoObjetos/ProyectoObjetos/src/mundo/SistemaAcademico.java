package mundo;

import java.util.ArrayList;
import java.util.List;

public class SistemaAcademico {

    private List<Estudiante> estudiantes;
    private List<Curso> cursos;
    private List<Calificacion> calificaciones;
    private List<Administrador> administradores;

    public SistemaAcademico() {
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();
        calificaciones = new ArrayList<>();
        administradores = new ArrayList<>();
    }
    
//REGISTRAR
    
    public void registrarAdministrador(Administrador admin) {
        administradores.add(admin);
    }

    public void registrarEstudiante(Estudiante e) throws CodigoDuplicadoException {
        for (Estudiante existente : estudiantes) {
            if (existente.getId().equals(e.getId())) {
                throw new CodigoDuplicadoException("Ya existe un estudiante con el ID " + e.getId());
            }
        }
        estudiantes.add(e);
    }

    public void registrarCurso(Curso c) throws CodigoDuplicadoException {
        for (Curso existente : cursos) {
            if (existente.getCodigo().equals(c.getCodigo())) {
                throw new CodigoDuplicadoException("Ya existe un curso con el código " + c.getCodigo());
            }
        }
        cursos.add(c);
    }

//LOCALIZAR

    public Estudiante buscarEstudiantePorId(String id) throws RegistroNoEncontradoException {
        for (Estudiante e : estudiantes) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        throw new RegistroNoEncontradoException("No existe un estudiante con ID " + id);
    }

    public Curso buscarCursoPorCodigo(String codigo) throws RegistroNoEncontradoException {
        for (Curso c : cursos) {
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        throw new RegistroNoEncontradoException("No existe un curso con código " + codigo);
    }

    
// MATRÍCULA
  

    public void matricularCurso(String idEstudiante, String codigoCurso)
            throws RegistroNoEncontradoException, PrerrequisitoNoCumplidoException {
        Estudiante e = buscarEstudiantePorId(idEstudiante);
        Curso c = buscarCursoPorCodigo(codigoCurso);

        for (String prereq : c.getPrerrequisitos()) {
            if (!e.getCursosAprobados().contains(prereq)) {
                throw new PrerrequisitoNoCumplidoException(
                        "El estudiante no cumple los prerrequisitos para matricular " + c.getNombre());
            }
        }
        e.matricular(codigoCurso);
    }


// CALIFICACIONES Y PROMEDIO PONDERADO


    public void registrarCalificacion(Calificacion cal) throws RegistroNoEncontradoException {
        Estudiante e = buscarEstudiantePorId(cal.getEstudianteId());
        calificaciones.add(cal);
        e.aprobarCurso(cal.getCodigoCurso());
        recalcularPromedio(e);
    }

    private void recalcularPromedio(Estudiante e) {
        double sumaPonderada = 0;
        int totalCreditos = 0;
        for (Calificacion cal : calificaciones) {
            if (cal.getEstudianteId().equals(e.getId())) {
                sumaPonderada += cal.getValor() * cal.getCreditos();
                totalCreditos += cal.getCreditos();
            }
        }
        e.setPromedioPonderado(totalCreditos == 0 ? 0 : sumaPonderada / totalCreditos);
    }


// ACTUALIZAR / ELIMINAR


    public void actualizarCurso(String codigo, String nuevoNombre, int nuevosCreditos) throws RegistroNoEncontradoException {
        Curso c = buscarCursoPorCodigo(codigo);
        c.setNombre(nuevoNombre);
        c.setCreditos(nuevosCreditos);
    }

    public void eliminarEstudiante(String id) throws RegistroNoEncontradoException {
        Estudiante e = buscarEstudiantePorId(id);
        estudiantes.remove(e);
    }

    public void eliminarCurso(String codigo) throws RegistroNoEncontradoException {
        Curso c = buscarCursoPorCodigo(codigo);
        cursos.remove(c);
    }

 
// LISTADOS Y DEMOSTRACIÓN DE POLIMORFISMO POR INTERFAZ
 

    public List<Estudiante> getTodosLosEstudiantes() {
        return estudiantes;
    }

    public List<Curso> getTodosLosCursos() {
        return cursos;
    }

    /**
     * Recibe cualquier objeto Identificable (puede ser un Estudiante, un
     * Administrador o un Curso, sin relación de herencia entre ellos) y lo
     * imprime de forma uniforme, sin necesidad de saber su clase exacta.
     */
    public void imprimirFicha(Identificable item) {
        System.out.println(item.getIdentificador() + " - " + item.getNombre());
    }

    /** Carga unos datos de ejemplo para que la aplicación no arranque vacía. */
    public void cargarDatosDeEjemplo() {
        try {
            registrarAdministrador(new Administrador("A001", "Celso Rodríguez", "admin123", "Docente"));

            registrarEstudiante(new Estudiante("E001", "Alejandro Córdoba", "1234", "Ingeniería de Sistemas", "Ingeniería"));
            registrarEstudiante(new Estudiante("E002", "Laura Gómez", "1234", "Ingeniería de Sistemas", "Ingeniería"));

            Curso c1 = new Curso("PROG1", "Programación I", 4, "Ingeniería de Sistemas");
            Curso c2 = new Curso("PROG2", "Programación II", 4, "Ingeniería de Sistemas");
            c2.agregarPrerrequisito("PROG1");

            registrarCurso(c1);
            registrarCurso(c2);
        } catch (CodigoDuplicadoException ex) {
            System.err.println("Error cargando datos de ejemplo: " + ex.getMessage());
        }
    }
}

