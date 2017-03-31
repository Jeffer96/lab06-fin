/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 2106991
 */
public class MyBATISClienteDAO implements ClienteDAO{
    @Inject
    private ClienteMapper clienteMapper;    
        
    @Override
    public void save(Cliente client) throws PersistenceException{
        try{
            System.out.println(client.getDocumento());
            clienteMapper.agregarCliente(client);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el Cliente "+client.toString(),e);
        }        
        
    }

    @Override
    public Cliente load(long id) throws PersistenceException {
        try{
            return clienteMapper.getCliente((int) id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el cliente "+id,e);
        }
    }

    @Override
    public List<Cliente> loadAll() throws PersistenceException {
        try{
            return clienteMapper.consultarClientes();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el clientes "+e);
        }
    }

    @Override
    public List<ItemRentado> loadRentados(long id) throws PersistenceException {
        try{
            return clienteMapper.consultarItemRentado((int) id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el clientes "+e);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AddItemRentado(long id, int idit, Date fechainicio, Date fechafin) throws PersistenceException {
        
        clienteMapper.agregarItemRentadoACliente(id, idit, fechainicio, fechafin);
        
    }

   

   
}
  
