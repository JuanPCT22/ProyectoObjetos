package mundo;

import java.util.ArrayList;
import java.util.List;

/**
 *  MODELADO DE ENTIDADES DEL DOMINIO (POO)
 * Representa una materia del plan de estudios. La lista de prerrequisitos
 * se usa para construir las ARISTAS del grafo de prerrequisitos
 * (GrafoPrerrequisitos), donde cada código de esta lista es un nodo que
 * debe estar aprobado antes de poder matricular este curso.
 */
public class Curso implements Identificable {

    private String codigo;
    private String nombre;
    private int creditos;
    private String programa;
    private List<String> prerrequisitos; // códigos de cursos requeridos previamente

    public Curso(String codigo, String nombre, int creditos, String programa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.programa = programa;
        this.prerrequisitos = new ArrayList<>();
    }

    public void agregarPrerrequisito(String codigoPrerrequisito) {
        if (!prerrequisitos.contains(codigoPrerrequisito)) {
            prerrequisitos.add(codigoPrerrequisito);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    // Implementación del contrato Identificable (POLIMORFISMO VÍA INTERFAZ)
    @Override
    public String getIdentificador() {
        return codigo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getPrograma() {
        return programa;
    }

    public List<String> getPrerrequisitos() {
        return prerrequisitos;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + creditos + " créditos)";
    }
}