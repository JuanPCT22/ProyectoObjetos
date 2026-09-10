package mundo;


// Se crea la Clase Usuario la cual sera la base de la que se heredaran Administrador y estudiante

public abstract class Usuario implements Identificable {
	
//los atributos al ser protected se pueden utilizar dentros de su propia clase o sus clases hijas(ENCAPSULAMIENTO)
	
    protected String id;
    protected String nombre;
    protected String contrasena;

    public Usuario(String id, String nombre, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getId() {
        return id;
    }

    // Implementación del contrato Identificable (POLIMORFISMO VÍA INTERFAZ)
    @Override
    public String getIdentificador() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public boolean validarContrasena(String intento) {
        return this.contrasena != null && this.contrasena.equals(intento);
    }
   /**
    *  (POLIMORFISMO) se añaden los metodos getRol() y tieneAccesoCompleto()
     al ser abstractos cada subclase los implementa de forma distinta 
    y el sistema puede tratar a cualquiera de los dos como un "Usuario" sin
    saber de cuál subclase se trata exactamente.

    * @return
    */
    // Método abstracto: obliga a cada subclase a definir su propio rol (POLIMORFISMO)
    
    public abstract String getRol();

    // Método abstracto: define el nivel de permisos de cada tipo de usuario
    
    public abstract boolean tieneAccesoCompleto();

    @Override
    public String toString() {
        return nombre + " (" + id + ") - " + getRol();
    }
}
