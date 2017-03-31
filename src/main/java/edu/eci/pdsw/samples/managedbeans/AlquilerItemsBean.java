/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import static java.time.Instant.now;
import static java.time.LocalDate.now;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2106913
 */
@ManagedBean(name = "AlquilerItems")
@SessionScoped

public class AlquilerItemsBean implements Serializable {
    
    ServiciosAlquiler sp = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
    
    private Cliente nuevoCliente;
    private Cliente selecCliente;
    private int codigoBarras;
    private int dias;
    private long Costo;
   
    
    public AlquilerItemsBean() {
       
        nuevoCliente =new Cliente();
        selecCliente =null;
        codigoBarras=0;
        Costo=0;
        dias=0;
    }
    
    public List<Cliente> getConsultarClientes() throws ExcepcionServiciosAlquiler {
        return  sp.consultarClientes();
    }

    public Cliente getSelecCliente() {
        return selecCliente;
    }

    public long getCosto() {
        return Costo;
    }

    public void setCosto(int Costo) {
        this.Costo = Costo;
    }
    
    public void setSelecCliente(Cliente selecCliente) {
        this.selecCliente = selecCliente;
    }
    public Cliente getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(Cliente nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }
    
    public void registrarNuevoCliente() throws ExcepcionServiciosAlquiler {
        sp.registrarCliente(nuevoCliente);
        
    }
    
    public List<ItemRentado> getConsultarItemsCliente() throws ExcepcionServiciosAlquiler {
        return sp.consultarItemCliente(selecCliente.getDocumento());
    }
    public List<Item> getConsultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
        List<Item> s =sp.consultarItemsDisponibles();
        return s;
    }
    public long getConsultarMultaAlquiler() throws ExcepcionServiciosAlquiler{
        long total=0;
        List<ItemRentado> lista = getConsultarItemsCliente();
        
        if (lista.size() > 0) {
            for (int i = 0; i < lista.size()  ; i++) {
                total += sp.consultarMultaAlquiler(lista.get(i), java.sql.Date.valueOf(LocalDate.now()));
            }
        }
        return total;
    }
    
    public int getDias() {
        return dias;
    }


    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(int codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    public void consultarCostoAlquiler() throws ExcepcionServiciosAlquiler {
        if (codigoBarras > 0) {
            Costo = sp.consultarCostoAlquiler(codigoBarras, dias);
        }
    }

    public void registrarAlquiler() throws ExcepcionServiciosAlquiler{
        if (codigoBarras > 0) {
            Item nuevoItem = sp.consultarItem(codigoBarras);
            sp.registrarAlquilerCliente(java.sql.Date.valueOf(LocalDate.now()), selecCliente.getDocumento(), nuevoItem, dias);
            dias=0;
            Costo=0;
            codigoBarras=0;
        }

    }
;
}
