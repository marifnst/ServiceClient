/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

/**
 *
 * @author Emerio
 */
public class UtilService {

    private static UtilService utilService;
    private QName SERVICE_NAME = new QName("http://services.core.com/", "ServiceBank");
    private QName PORT_NAME = new QName("http://services.core.com/", "ServiceBankPort");
    private QName SERVICE_NAME_KURIR = new QName("http://services.core.com/", "ServiceKurir");
    private QName PORT_NAME_KURIR = new QName("http://services.core.com/", "ServiceKurirPort");
    private QName SERVICE_NAME_VIRTUAL = new QName("http://services.virtual.com/", "ServiceValidation");
    private QName PORT_NAME_VIRTUAL = new QName("http://services.virtual.com/", "ServiceValidationPort");
    private QName SERVICE_NAME_VIRTUAL_REAL = new QName("http://services.virtual.com/", "ServiceValidationReal");
    private QName PORT_NAME_VIRTUAL_REAL = new QName("http://services.virtual.com/", "ServiceValidationRealPort");
    private Service service;

    public static UtilService getInstance() {
        if (utilService == null) {
            utilService = new UtilService();
        }
        return utilService;
    }

    public void openService() {
        try {
            InputStream is = UtilDatabase.class.getClassLoader().getResourceAsStream("config.properties");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();

            String endpointAddress = null;
            while (data != null) {
                String key = data.split("\\=")[0];
                String value = data.split("\\=")[1];
                if (key.equalsIgnoreCase("service.core")) {
                    endpointAddress = value + "services/transaksi_bank";
                }
                data = br.readLine();
            }
            isr.close();

            service = Service.create(SERVICE_NAME);
            service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openServiceKurir() {
        try {
            InputStream is = UtilDatabase.class.getClassLoader().getResourceAsStream("config.properties");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();

            String endpointAddress = null;
            while (data != null) {
                String key = data.split("\\=")[0];
                String value = data.split("\\=")[1];
                if (key.equalsIgnoreCase("service.core")) {
                    endpointAddress = value + "services/transaksi_kurir";
                }
                data = br.readLine();
            }
            isr.close();

            service = Service.create(SERVICE_NAME_KURIR);
            service.addPort(PORT_NAME_KURIR, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openServiceVirtual() {
        try {
            InputStream is = UtilDatabase.class.getClassLoader().getResourceAsStream("config.properties");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();

            String endpointAddress = null;
            while (data != null) {
                String key = data.split("\\=")[0];
                String value = data.split("\\=")[1];
                if (key.equalsIgnoreCase("service.virtual")) {
                    endpointAddress = value + "services/validation";
                }
                data = br.readLine();
            }
            isr.close();

            service = Service.create(SERVICE_NAME_VIRTUAL);
            service.addPort(PORT_NAME_VIRTUAL, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openServiceVirtualReal() {
        try {
            InputStream is = UtilDatabase.class.getClassLoader().getResourceAsStream("config.properties");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();

            String endpointAddress = null;
            while (data != null) {
                String key = data.split("\\=")[0];
                String value = data.split("\\=")[1];
                if (key.equalsIgnoreCase("service.virtual.real")) {
                    endpointAddress = value + "services/validation_real";
                }
                data = br.readLine();
            }
            isr.close();

            service = Service.create(SERVICE_NAME_VIRTUAL_REAL);
            service.addPort(PORT_NAME_VIRTUAL_REAL, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
