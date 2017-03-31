/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.PersistenceException;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.List;

/**
 *
 * @author Juan Villate
 * @author Jefferson Castañeda
 */
public class MyBATISItemRentadoDAO implements ItemRentadoDAO{
    @Inject
    private ItemMapper itemMapper;    
        
    @Override
    public void save(Item it) throws PersistenceException{
        try{
            itemMapper.insertarItemRentado(it);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+it.toString(),e);
        }        
        
    }

    @Override
    public ItemRentado load(int id) throws PersistenceException {
        try{
            return itemMapper.consultarItemRentado(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }
        
        
    }
    @Override
    public List<ItemRentado> loadAll() throws PersistenceException {
        try{
            return itemMapper.getItemsRentados();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los items "+e);
        }
    }

    
}
