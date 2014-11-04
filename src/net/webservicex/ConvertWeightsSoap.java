
package net.webservicex;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ConvertWeightsSoap", targetNamespace = "http://www.webserviceX.NET/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ConvertWeightsSoap {


    /**
     * 
     * @param weight
     * @param fromUnit
     * @param toUnit
     * @return
     *     returns double
     */
    @WebMethod(operationName = "ConvertWeight", action = "http://www.webserviceX.NET/ConvertWeight")
    @WebResult(name = "ConvertWeightResult", targetNamespace = "http://www.webserviceX.NET/")
    @RequestWrapper(localName = "ConvertWeight", targetNamespace = "http://www.webserviceX.NET/", className = "net.webservicex.ConvertWeight")
    @ResponseWrapper(localName = "ConvertWeightResponse", targetNamespace = "http://www.webserviceX.NET/", className = "net.webservicex.ConvertWeightResponse")
    public double convertWeight(
        @WebParam(name = "Weight", targetNamespace = "http://www.webserviceX.NET/")
        double weight,
        @WebParam(name = "FromUnit", targetNamespace = "http://www.webserviceX.NET/")
        WeightUnit fromUnit,
        @WebParam(name = "ToUnit", targetNamespace = "http://www.webserviceX.NET/")
        WeightUnit toUnit);

}
