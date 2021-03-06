package com.klugesoftware.farmainvoice.dbmanager.anagrafica;

import com.klugesoftware.farmainvoice.dbmanager.AnagraficaDAOManager;
import com.klugesoftware.farmainvoice.dbmanager.DAOFactory;
import com.klugesoftware.farmainvoice.model.Anagrafica;
import com.klugesoftware.farmainvoice.model.TipoAnagrafica;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class AnagraficaDeleteTest {

    private String dbUrl;


    @BeforeEach
    void setUp() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(DAOFactory.PROPERTIES_FILE_NAME));
            dbUrl = prop.getProperty("dbUrl");
            prop.setProperty("dbUrl","jdbc:mysql://localhost:3306/FarmaInvoiceUnitTest");
            prop.store(new FileOutputStream(DAOFactory.PROPERTIES_FILE_NAME),null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @Test
    void createAnagrafica(){
        Anagrafica anagrafica = new Anagrafica();
        anagrafica.setDenominazione("prova srl da cancellare");
        anagrafica.setTipoAnagrafica(TipoAnagrafica.FORNITORE);
        anagrafica.setPartitaIva("00000000000");
        anagrafica.setCodiceFiscale("SCGMRC73B12L219L");
        anagrafica.setIndirizzo("via Roma 16");
        anagrafica.setCap("01000");
        anagrafica.setComune("Roma");
        anagrafica.setNazione("IT");

        anagrafica = AnagraficaDAOManager.insert(anagrafica);

        assertNotNull(anagrafica.getIdAnagrafica());
    }

    @Test
    void deleteAnagraficaById(){
        Anagrafica anagrafica = new Anagrafica();
        anagrafica = AnagraficaDAOManager.findByPartitaIva("00000000000");
        assertEquals("prova srl da cancellare",anagrafica.getDenominazione());

        assertEquals(true,AnagraficaDAOManager.deleteAnagrafica(anagrafica));
        assertNull(AnagraficaDAOManager.findByPartitaIva("00000000000").getIdAnagrafica());
    }

    @AfterEach
    void tearDown() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(DAOFactory.PROPERTIES_FILE_NAME));
            prop.setProperty("dbUrl",dbUrl);
            prop.store(new FileOutputStream(DAOFactory.PROPERTIES_FILE_NAME),null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}