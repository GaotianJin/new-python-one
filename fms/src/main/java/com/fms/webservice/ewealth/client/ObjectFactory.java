
package com.fms.webservice.ewealth.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.fms.webservice.ewealth.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SynProductOpenDateInfoResponse_QNAME = new QName("http://webservice.web.product.ewealth.sinosoft.com/", "synProductOpenDateInfoResponse");
    private final static QName _SynProductDefInfo_QNAME = new QName("http://webservice.web.product.ewealth.sinosoft.com/", "synProductDefInfo");
    private final static QName _SynProductDefInfoResponse_QNAME = new QName("http://webservice.web.product.ewealth.sinosoft.com/", "synProductDefInfoResponse");
    private final static QName _SynProductNetValueInfo_QNAME = new QName("http://webservice.web.product.ewealth.sinosoft.com/", "synProductNetValueInfo");
    private final static QName _SynProductOpenDateInfo_QNAME = new QName("http://webservice.web.product.ewealth.sinosoft.com/", "synProductOpenDateInfo");
    private final static QName _SynProductNetValueInfoResponse_QNAME = new QName("http://webservice.web.product.ewealth.sinosoft.com/", "synProductNetValueInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.fms.webservice.ewealth.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SynProductDefInfo }
     * 
     */
    public SynProductDefInfo createSynProductDefInfo() {
        return new SynProductDefInfo();
    }

    /**
     * Create an instance of {@link SynProductOpenDateInfoResponse }
     * 
     */
    public SynProductOpenDateInfoResponse createSynProductOpenDateInfoResponse() {
        return new SynProductOpenDateInfoResponse();
    }

    /**
     * Create an instance of {@link SynProductDefInfoResponse }
     * 
     */
    public SynProductDefInfoResponse createSynProductDefInfoResponse() {
        return new SynProductDefInfoResponse();
    }

    /**
     * Create an instance of {@link SynProductOpenDateInfo }
     * 
     */
    public SynProductOpenDateInfo createSynProductOpenDateInfo() {
        return new SynProductOpenDateInfo();
    }

    /**
     * Create an instance of {@link SynProductNetValueInfo }
     * 
     */
    public SynProductNetValueInfo createSynProductNetValueInfo() {
        return new SynProductNetValueInfo();
    }

    /**
     * Create an instance of {@link SynProductNetValueInfoResponse }
     * 
     */
    public SynProductNetValueInfoResponse createSynProductNetValueInfoResponse() {
        return new SynProductNetValueInfoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynProductOpenDateInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.product.ewealth.sinosoft.com/", name = "synProductOpenDateInfoResponse")
    public JAXBElement<SynProductOpenDateInfoResponse> createSynProductOpenDateInfoResponse(SynProductOpenDateInfoResponse value) {
        return new JAXBElement<SynProductOpenDateInfoResponse>(_SynProductOpenDateInfoResponse_QNAME, SynProductOpenDateInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynProductDefInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.product.ewealth.sinosoft.com/", name = "synProductDefInfo")
    public JAXBElement<SynProductDefInfo> createSynProductDefInfo(SynProductDefInfo value) {
        return new JAXBElement<SynProductDefInfo>(_SynProductDefInfo_QNAME, SynProductDefInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynProductDefInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.product.ewealth.sinosoft.com/", name = "synProductDefInfoResponse")
    public JAXBElement<SynProductDefInfoResponse> createSynProductDefInfoResponse(SynProductDefInfoResponse value) {
        return new JAXBElement<SynProductDefInfoResponse>(_SynProductDefInfoResponse_QNAME, SynProductDefInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynProductNetValueInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.product.ewealth.sinosoft.com/", name = "synProductNetValueInfo")
    public JAXBElement<SynProductNetValueInfo> createSynProductNetValueInfo(SynProductNetValueInfo value) {
        return new JAXBElement<SynProductNetValueInfo>(_SynProductNetValueInfo_QNAME, SynProductNetValueInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynProductOpenDateInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.product.ewealth.sinosoft.com/", name = "synProductOpenDateInfo")
    public JAXBElement<SynProductOpenDateInfo> createSynProductOpenDateInfo(SynProductOpenDateInfo value) {
        return new JAXBElement<SynProductOpenDateInfo>(_SynProductOpenDateInfo_QNAME, SynProductOpenDateInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynProductNetValueInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.web.product.ewealth.sinosoft.com/", name = "synProductNetValueInfoResponse")
    public JAXBElement<SynProductNetValueInfoResponse> createSynProductNetValueInfoResponse(SynProductNetValueInfoResponse value) {
        return new JAXBElement<SynProductNetValueInfoResponse>(_SynProductNetValueInfoResponse_QNAME, SynProductNetValueInfoResponse.class, null, value);
    }

}
