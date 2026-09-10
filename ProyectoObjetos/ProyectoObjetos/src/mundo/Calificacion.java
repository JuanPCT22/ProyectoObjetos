package mundo;

/**
 * TEMA APLICADO: MODELADO DE ENTIDADES DEL DOMINIO (POO)
 * ---------------------------------------------------------
 * Cada Calificación relaciona a un estudiante con un curso y un valor
 * numérico. Los créditos del curso se guardan aquí también para poder
 * calcular el promedio PONDERADO (cada nota pesa según los créditos de
 * la materia), tal como lo pide el Requerimiento R6.
 */
public class Calificacion {

    private String estudianteId;
    private String codigoCurso;
    private double valor;
    private int creditos;
    private int semestre;

    public Calificacion(String estudianteId, String codigoCurso, double valor, int creditos, int semestre) {
        this.estudianteId = estudianteId;
        this.codigoCurso = codigoCurso;
        this.valor = valor;
        this.creditos = creditos;
        this.semestre = semestre;
    }

    public String getEstudianteId() {
        return estudianteId;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public double getValor() {
        return valor;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getSemestre() {
        return semestre;
    }

    @Override
    public String toString() {
        return codigoCurso + ": " + valor + " (créditos: " + creditos + ", semestre " + semestre + ")";
    }
}