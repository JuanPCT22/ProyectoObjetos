package mundo;

/**
 * Se lanza cuando un estudiante intenta matricular un curso sin haber
 * aprobado previamente todos los cursos que figuran como prerrequisito.
 */
public class PrerrequisitoNoCumplidoException extends Exception {

    public PrerrequisitoNoCumplidoException(String mensaje) {
        super(mensaje);
    }
}
