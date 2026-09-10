package mundo;

/**
 * TEMA APLICADO: EXCEPCIONES PROPIAS (POO)
 * -----------------------------------------------------------------------
 * Se lanza cuando se intenta registrar un estudiante o un curso cuyo
 * identificador (ID o código) ya existe en el sistema.
 */
public class CodigoDuplicadoException extends Exception {

    public CodigoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
