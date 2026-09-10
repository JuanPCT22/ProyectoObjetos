package mundo;

/**
 * Se lanza cuando se busca un estudiante o un curso por su identificador
 * y no existe ningún registro con ese ID/código en el sistema.
 */
public class RegistroNoEncontradoException extends Exception {

    public RegistroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
