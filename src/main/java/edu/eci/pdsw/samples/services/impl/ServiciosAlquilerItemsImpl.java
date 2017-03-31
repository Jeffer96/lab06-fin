package edu.eci.pdsw.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Juan Villate
 * @author Jefferson Castañeda
 */

@Singleton
public class ServiciosAlquilerItemsImpl implements ServiciosAlquiler {
    
    private static final int multaDia=5000;
    
    
    public ServiciosAlquilerItemsImpl() {
        
    }
    @Inject
    private ItemDAO daoItem;
    @Inject
    private ClienteDAO daoCliente;
    @Inject
    private ItemRentadoDAO daoItemRentado;
    @Inject
    private TipoItemDAO daoTipoItem;
    
  
    
    @Override
    public int valorMultaRetrasoxDia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente consultarCliente(int docu) throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.load(docu);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el cliente"+docu,ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemRentado> consultarItemCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.loadRentados(idcliente);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item del cliente "+idcliente,ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return daoCliente.loadAll();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los clientes "+ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return daoItem.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
       try {
            return daoItem.loadDisponibles();
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar los disponibles"+ex);
        }
    }

    @Override
    public long consultarMultaAlquiler(ItemRentado iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        
        LocalDate fechaMinimaEntrega = iditem.getFechafinrenta().toLocalDate();
        LocalDate fechaEntrega = fechaDevolucion.toLocalDate();
        long diasRetraso = ChronoUnit.DAYS.between(fechaMinimaEntrega, fechaEntrega);
        if( diasRetraso * multaDia>0){
            return diasRetraso * multaDia;}
        else{
            return 0;}
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        TipoItem TI =null;
        try {
            TI = daoTipoItem.load(id);
            
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosAlquilerItemsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TI;
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        List<TipoItem> tipos= null;
        try {
            tipos = daoTipoItem.loadAll();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosAlquilerItemsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipos;
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        LocalDate ld=date.toLocalDate();
        LocalDate ld2=ld.plusDays(numdias);
        Date date2 = Date.valueOf(ld2);
        
        try {
            if(daoCliente.load(docu).getDocumento()== docu){
                daoCliente.AddItemRentado(docu,item.getId(),date,date2);   
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosAlquilerItemsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void registrarCliente(Cliente p) throws ExcepcionServiciosAlquiler {
        try {
            daoCliente.save(p);
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosAlquilerItemsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        long total = 0;
        try {
            if(daoItem.load(iditem)!=null){
                total = daoItem.load(iditem).getTarifaxDia()* numdias;
            }else{
                throw new ExcepcionServiciosAlquiler("El item " + iditem + "no esta en alquiler");
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosAlquilerItemsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    @Override
    public void registrarDevolucion(int iditem) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vetarCliente(long docu, int estado) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      
    
}
