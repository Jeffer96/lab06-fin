package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;


import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    //----------- Item ----------------------//
    public List<Item> getItems();       
    
    public Item consultarItem(@Param("idItem")int id);
  
    public void insertarItem(@Param("item")Item it);
    //-----------Item Rentado----------------//
    
    public List<ItemRentado> consultarItemsRentados();
    
    public List<Item> consultarItemsDisponibles();
    
    public ItemRentado consultarItemRentado(@Param("idItem")int id);
  
    public void insertarItemRentado(@Param("item")Item it);

        
}
