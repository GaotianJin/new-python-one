
package com.fms.webservice.ewealth.client;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 2.5.1
 * 2015-09-23T16:59:29.566+08:00
 * Generated source version: 2.5.1
 * 
 */
public final class ProductDefSynWebservice_ProductDefSynWebservicePort_Client {

    private  static final QName SERVICE_NAME = new QName("http://webservice.web.product.ewealth.sinosoft.com/", "ProductDefSynWebserviceService");
    
    public ProductDefSynWebservice_ProductDefSynWebservicePort_Client() {
    }
  
    /****调用产品信息接口同步至前端****/
    public String  invokeServerProductInfo(String _synProductDefInfo_arg0){
        URL wsdlURL = ProductDefSynWebserviceService.WSDL_LOCATION;
        ProductDefSynWebserviceService ss = new ProductDefSynWebserviceService(wsdlURL, SERVICE_NAME);
        ProductDefSynWebservice port = ss.getProductDefSynWebservicePort();  
        System.out.println("Invoking synProductDefInfo...");
        java.lang.String _synProductDefInfo__return = port.synProductDefInfo(_synProductDefInfo_arg0);
        System.out.println("synProductDefInfo.result=" + _synProductDefInfo__return);
        return   _synProductDefInfo__return;
    }
    /*****调用净值信息同步至前端*****/
    public String  invokeServerProductNetValueInfo(String _synProductDefInfo_arg0){
        URL wsdlURL = ProductDefSynWebserviceService.WSDL_LOCATION;
        ProductDefSynWebserviceService ss = new ProductDefSynWebserviceService(wsdlURL, SERVICE_NAME);
        ProductDefSynWebservice port = ss.getProductDefSynWebservicePort();
        System.out.println("Invoking synProductDefInfo...");
        java.lang.String _synProductDefInfo__return = port.synProductNetValueInfo(_synProductDefInfo_arg0);
        System.out.println("synProductDefInfo.result=" + _synProductDefInfo__return);
        return   _synProductDefInfo__return;
    }
    
    /****调用开放日信息同步至前端****/
    public String  invokeServerProductOpenDateInfo(String _synProductDefInfo_arg0){
        URL wsdlURL = ProductDefSynWebserviceService.WSDL_LOCATION;
        ProductDefSynWebserviceService ss = new ProductDefSynWebserviceService(wsdlURL, SERVICE_NAME);
        ProductDefSynWebservice port = ss.getProductDefSynWebservicePort();  
        System.out.println("Invoking synProductDefInfo...");
        java.lang.String _synProductDefInfo__return = port.synProductOpenDateInfo(_synProductDefInfo_arg0);
        
        System.out.println("synProductDefInfo.result=" + _synProductDefInfo__return);
        return   _synProductDefInfo__return;
    }
    

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ProductDefSynWebserviceService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
/*        ProductDefSynWebserviceService ss = new ProductDefSynWebserviceService(wsdlURL, SERVICE_NAME);
        ProductDefSynWebservice port = ss.getProductDefSynWebservicePort();  
        
        {
        System.out.println("Invoking synProductNetValueInfo...");
        java.lang.String _synProductNetValueInfo_arg0 = "";
        java.lang.String _synProductNetValueInfo__return = port.synProductNetValueInfo(_synProductNetValueInfo_arg0);
        System.out.println("synProductNetValueInfo.result=" + _synProductNetValueInfo__return);


        }
        {
        System.out.println("Invoking synProductOpenDateInfo...");
        java.lang.String _synProductOpenDateInfo_arg0 = "";
        java.lang.String _synProductOpenDateInfo__return = port.synProductOpenDateInfo(_synProductOpenDateInfo_arg0);
        System.out.println("synProductOpenDateInfo.result=" + _synProductOpenDateInfo__return);


        }
        {
        System.out.println("Invoking synProductDefInfo...");
        java.lang.String _synProductDefInfo_arg0 = "";
        java.lang.String _synProductDefInfo__return = port.synProductDefInfo(_synProductDefInfo_arg0);
        System.out.println("synProductDefInfo.result=" + _synProductDefInfo__return);


        }

        System.exit(0);*/
    }

}