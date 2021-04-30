package com.example.usuarios;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String tipo;

    public String getNombre() {
        return nombre;        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; 
    }

    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo=tipo;
    }
}
