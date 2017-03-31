/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.services.impl.ServiciosAlquilerItemsImpl;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.Guice.createInjector;
import com.google.inject.Injector;
import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.TipoItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISClienteDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISTipoItemDAO;
import edu.eci.pdsw.samples.entities.Cliente;

/**
 *
 * @author hcadavid
 */
public class ServiciosAlquilerFactory {

    private static final ServiciosAlquilerFactory instance = new ServiciosAlquilerFactory();
    
    private static Injector injector;
    
    private static Injector testInjector;
    
    private ServiciosAlquilerFactory(){
        
        injector = createInjector(new XMLMyBatisModule() {

                    @Override
                    protected void initialize() {
                        install(JdbcHelper.MySQL);                        
                        setClassPathResource("mybatis-config.xml");                        
                        bind(ServiciosAlquiler.class).to(ServiciosAlquilerItemsImpl.class);
                        bind(ClienteDAO.class).to(MyBATISClienteDAO.class);
                        bind(ItemDAO.class).to(MyBATISItemDAO.class);
                        bind(ItemRentadoDAO.class).to(MyBATISItemRentadoDAO.class);
                        bind(TipoItemDAO.class).to(MyBATISTipoItemDAO.class);
                    }

                }
                
        );

        testInjector = createInjector(new XMLMyBatisModule() {

                    @Override
                    protected void initialize() {
                        install(JdbcHelper.MySQL);                        
                        setClassPathResource("mybatis-config-h2.xml");                        
                        bind(ServiciosAlquiler.class).to(ServiciosAlquilerItemsImpl.class);
                        bind(ClienteDAO.class).to(MyBATISClienteDAO.class);
                        bind(ItemDAO.class).to(MyBATISItemDAO.class);
                        bind(ItemRentadoDAO.class).to(MyBATISItemRentadoDAO.class);
                        bind(TipoItemDAO.class).to(MyBATISTipoItemDAO.class);
                    }

                }
                
        );

        
    }
    
    public ServiciosAlquiler getServiciosAlquiler(){
        return injector.getInstance(ServiciosAlquiler.class);   
    }

    public ClienteDAO getClienteDAO() {
        return injector.getInstance(ClienteDAO.class);
    }
    
    public ItemDAO getItemDAO() {
        return injector.getInstance(ItemDAO.class);
    }
    
    public ItemRentadoDAO getItemRentadoDAO() {
        return injector.getInstance(ItemRentadoDAO.class);
    }
     
    public TipoItemDAO getTipoItemDAO() {
        return injector.getInstance(TipoItemDAO.class);
    }
    
    public ServiciosAlquiler getServiciosAlquilerTesting(){
        return testInjector.getInstance(ServiciosAlquiler.class);   
    }

    public ClienteDAO getClienteDAOTesting() {
        return testInjector.getInstance(ClienteDAO.class);
    }
    
    public ItemDAO getItemDAOTesting() {
        return testInjector.getInstance(ItemDAO.class);
    }
    
    public ItemRentadoDAO getItemRentadoDAOTesting() {
        return testInjector.getInstance(ItemRentadoDAO.class);
    }
     
    public TipoItemDAO getTipoItemDAOTesting() {
        return testInjector.getInstance(TipoItemDAO.class);
    }
    public static ServiciosAlquilerFactory getInstance(){
        return instance;
    }
}
