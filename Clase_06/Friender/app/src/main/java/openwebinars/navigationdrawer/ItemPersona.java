package openwebinars.navigationdrawer;

/**
 * Created by miguelcampos on 1/12/15.
 */
public class ItemPersona {
    private String nombre;
    private int edad;

    // En este atributo vamos a almacenar un valor
    // 1 > si la persona nos ha dado un LIKE
    // 2 > si la personas nos ha dado un SUPERLIKE
    private int tipoCoincidencia;

    public ItemPersona(String nombre, int edad, int tipoCoincidencia) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipoCoincidencia = tipoCoincidencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTipoCoincidencia() {
        return tipoCoincidencia;
    }

    public void setTipoCoincidencia(int tipoCoincidencia) {
        this.tipoCoincidencia = tipoCoincidencia;
    }
}
