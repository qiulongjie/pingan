/**
 * YSW2ICareSaveServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package cn.com.metlife.icare.webservice;

public class YSW2ICareSaveServiceLocator extends org.apache.axis.client.Service implements cn.com.metlife.icare.webservice.YSW2ICareSaveService {

    public YSW2ICareSaveServiceLocator() {
    }


    public YSW2ICareSaveServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public YSW2ICareSaveServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for YSW2ICareSave
    private java.lang.String YSW2ICareSave_address = "http://icare-uat.metlife.com.cn/services/YSW2ICareSave";

    public java.lang.String getYSW2ICareSaveAddress() {
        return YSW2ICareSave_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String YSW2ICareSaveWSDDServiceName = "YSW2ICareSave";

    public java.lang.String getYSW2ICareSaveWSDDServiceName() {
        return YSW2ICareSaveWSDDServiceName;
    }

    public void setYSW2ICareSaveWSDDServiceName(java.lang.String name) {
        YSW2ICareSaveWSDDServiceName = name;
    }

    public cn.com.metlife.icare.webservice.YSW2ICareSave_PortType getYSW2ICareSave() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(YSW2ICareSave_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getYSW2ICareSave(endpoint);
    }

    public cn.com.metlife.icare.webservice.YSW2ICareSave_PortType getYSW2ICareSave(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cn.com.metlife.icare.webservice.YSW2ICareSaveSoapBindingStub _stub = new cn.com.metlife.icare.webservice.YSW2ICareSaveSoapBindingStub(portAddress, this);
            _stub.setPortName(getYSW2ICareSaveWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setYSW2ICareSaveEndpointAddress(java.lang.String address) {
        YSW2ICareSave_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cn.com.metlife.icare.webservice.YSW2ICareSave_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                cn.com.metlife.icare.webservice.YSW2ICareSaveSoapBindingStub _stub = new cn.com.metlife.icare.webservice.YSW2ICareSaveSoapBindingStub(new java.net.URL(YSW2ICareSave_address), this);
                _stub.setPortName(getYSW2ICareSaveWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("YSW2ICareSave".equals(inputPortName)) {
            return getYSW2ICareSave();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://icare-uat.metlife.com.cn/services/YSW2ICareSave", "YSW2ICareSaveService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://icare-uat.metlife.com.cn/services/YSW2ICareSave", "YSW2ICareSave"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("YSW2ICareSave".equals(portName)) {
            setYSW2ICareSaveEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
