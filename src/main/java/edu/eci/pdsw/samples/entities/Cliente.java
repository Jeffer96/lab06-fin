/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author 2106913
 */
public class Cliente implements Serializable{
    
    private String nombre;
    private long documento;
    private String telefono;
    private String direccion;
    private String email;
    private int vetado;
    private ArrayList<ItemRentado> rentados; 

    public Cliente() {
        this.vetado = 0;
        this.rentados = new ArrayList<>();
    }

    public Cliente(String nombre, long documento, String telefono, String direccion, String email, int vetado, ArrayList<ItemRentado> rentados) {   
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.vetado = vetado;
        this.rentados = rentados;
    }

  
    public Cliente(String nombre, long documento, String telefono, String direccion, String email) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.vetado = 0;
        this.rentados = new ArrayList<>();
    }

           
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int isVetado() {
        return vetado;
    }

    public void setVetado(int betado) {
        this.vetado = betado;
    }

    public ArrayList<ItemRentado> getRentados() {
        return rentados;
    }

    public void setRentados(ArrayList<ItemRentado> Rentados) {
        this.rentados = Rentados;
    }

   
    
    
}
