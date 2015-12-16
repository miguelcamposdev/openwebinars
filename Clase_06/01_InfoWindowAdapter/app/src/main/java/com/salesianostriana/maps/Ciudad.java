package com.salesianostriana.maps;

/**
 * Created by miguelcampos on 9/12/15.
 */
public class Ciudad {
    private long id;
    private String nombre;

    public Ciudad(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
