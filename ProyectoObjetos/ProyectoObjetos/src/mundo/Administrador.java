package mundo;

// La clase Administardor hereda a la clase abstracta Usuario

public class Administrador extends Usuario {

    private String cargo; // "Coordinador" o "Docente"

    public Administrador(String id, String nombre, String contrasena, String cargo) {
        super(id, nombre, contrasena); // llamada al constructor de la clase padre
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String getRol() {
        return "Administrador";
    }

    @Override
    public boolean tieneAccesoCompleto() {
        return true; // el administrador puede crear, leer, actualizar y eliminar
    }
}
