package mundo;

import java.util.ArrayList;
import java.util.List;

/**
 * Estudiante hereda de Usuario
 * el estudiante solo guarda su información
 * básica y listas simples (ArrayList) de cursos.
 */
public class Estudiante extends Usuario {

    private String programa;
    private String facultad;
    private int semestreActual;
    private double promedioPonderado; // GPA

    private List<String> cursosAprobados;     // códigos de cursos ya aprobados
    private List<String> cursosMatriculados;  // códigos de cursos matriculados en el semestre actual

    public Estudiante(String id, String nombre, String contrasena, String programa, String facultad) {
        super(id, nombre, contrasena);
        this.programa = programa;
        this.facultad = facultad;
        this.semestreActual = 1;
        this.promedioPonderado = 0.0;
        this.cursosAprobados = new ArrayList<>();
        this.cursosMatriculados = new ArrayList<>();
    }

    @Override
    public String getRol() {
        return "Estudiante";
    }

    @Override
    public boolean tieneAccesoCompleto() {
        return false; // solo puede consultar y matricular, no editar ni eliminar
    }

    public String getPrograma() {
        return programa;
    }

    public String getFacultad() {
        return facultad;
    }

    public int getSemestreActual() {
        return semestreActual;
    }

    public void setSemestreActual(int semestreActual) {
        this.semestreActual = semestreActual;
    }

    public double getPromedioPonderado() {
        return promedioPonderado;
    }

    public void setPromedioPonderado(double promedioPonderado) {
        this.promedioPonderado = promedioPonderado;
    }

    public List<String> getCursosAprobados() {
        return cursosAprobados;
    }

    public List<String> getCursosMatriculados() {
        return cursosMatriculados;
    }

    public void matricular(String codigoCurso) {
        if (!cursosMatriculados.contains(codigoCurso)) {
            cursosMatriculados.add(codigoCurso);
        }
    }

    public void aprobarCurso(String codigoCurso) {
        cursosMatriculados.remove(codigoCurso);
        if (!cursosAprobados.contains(codigoCurso)) {
            cursosAprobados.add(codigoCurso);
        }
    }
}

