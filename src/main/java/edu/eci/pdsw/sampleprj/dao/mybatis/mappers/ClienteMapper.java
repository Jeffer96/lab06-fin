package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") int id); 
    
     public void agregarCliente(@Param("cli")Cliente client);
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     */
    public void agregarItemRentadoACliente(@Param("idcli")long id, 
            @Param("iditem")int idit, 
            @Param("Inicio")Date fechainicio,
            @Param("Fin")Date fechafin);

    /**
     * Consultar todos los clientes
     * @return retorna lista de clientes
     */
    public List<Cliente> consultarClientes();
   
    public List<ItemRentado> consultarItemRentado(@Param("idcli") int id);
    
}
