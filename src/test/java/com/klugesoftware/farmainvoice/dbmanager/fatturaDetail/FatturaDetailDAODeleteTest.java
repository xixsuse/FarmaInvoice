package com.klugesoftware.farmainvoice.dbmanager.fatturaDetail;

import com.klugesoftware.farmainvoice.dbmanager.DAOFactory;
import com.klugesoftware.farmainvoice.dbmanager.FatturaDetailDAOManager;
import com.klugesoftware.farmainvoice.dbmanager.FatturaHeaderDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

class FatturaDetailDAODeleteTest {

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
    void deleteRecordById() {
        Assertions.assertTrue(FatturaDetailDAOManager.deleteFatturaDetail(1));
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