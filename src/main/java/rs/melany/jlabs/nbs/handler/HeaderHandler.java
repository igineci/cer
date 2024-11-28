/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.melany.jlabs.nbs.handler;

import java.util.*;
import javax.xml.namespace.QName;

import jakarta.xml.soap.Name;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPHeader;
import jakarta.xml.soap.SOAPHeaderElement;
import jakarta.xml.ws.handler.*;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import rs.nbs.communicationoffice.AuthenticationHeader;


/**
 * A SOAP handler for injecting authentication credentials into the SOAP header
 * when communicating with the NBS Web Service.
 * This class is linked to the Web Service via the HeaderHandlerResolver interface (HandlerResolver).
 *
 * @author andjela.djekic
 */
public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {

    private final AuthenticationHeader auth;
    private static String NBS_NAMESPACE = "NBS_NAMESPACE_EXAMPLE";

    /**
     * Constructor for HeaderHandler.
     *
     * @param auth AuthenticationHeader object containing user credentials and licence information.
     */
    public HeaderHandler(AuthenticationHeader auth) {
        this.auth = auth;
    }

    /**
     * This method is called when the SOAP message header is processed.
     * It adds the authentication details to the SOAP header for outbound messages.
     *
     * @param smc The SOAPMessageContext.
     * @return true if the message is outbound, false otherwise.
     */
    @Override
    public boolean handleMessage(SOAPMessageContext smc) {

        // Check if the message is outbound (i.e., being sent to the Web Service)
        // We are interested in a specific moment - right after the creation of the default SOAP header.
        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (outboundProperty) {
            try {

                // Retrieve the SOAP envelope from the message context
                SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();

                // Retrieve the existing header, or create a new one if it doesn't exist
                SOAPHeader header = envelope.getHeader();
                if (header == null) header = envelope.addHeader();

                // Add AuthenticationHeader with credentials
                SOAPFactory soapFactory = SOAPFactory.newInstance();
                Name headerName = soapFactory.createName("AuthenticationHeader", "c", NBS_NAMESPACE);
                SOAPHeaderElement headerElement = header.addHeaderElement(headerName);

                addTextElement(soapFactory, headerElement, "UserName", auth.getUserName());
                addTextElement(soapFactory, headerElement, "Password", auth.getUserName());
                addTextElement(soapFactory, headerElement, "LicenceID", auth.getUserName());

            } catch (SOAPException e) {
                e.printStackTrace();
                //throw new CERException(CERStatus.AUTHENTICATION_ERROR, "localhost", this.);
            }
        }
        return outboundProperty;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    /**
     * Helper method to create and add a text element to the header.
     *
     * @param soapFactory   SOAPFactory instance for creating elements.
     * @param headerElement The parent header element to which the child element will be added.
     * @param name          The name of the element.
     * @param value         The value of the element.
     */
    private void addTextElement(SOAPFactory soapFactory, SOAPHeaderElement headerElement, String name, String value) {
        try {
            Name elementName = soapFactory.createName(name, "c", NBS_NAMESPACE);
            SOAPElement element = headerElement.addChildElement(elementName);
            element.addTextNode(value);
        } catch (SOAPException e) {
            System.out.println("Error creating SOAP element for " + name + ": " + e.getMessage());
        }
    }
}
