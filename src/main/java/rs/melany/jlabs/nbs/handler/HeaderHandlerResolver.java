/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.melany.jlabs.nbs.handler;

import java.util.*;
import jakarta.xml.ws.handler.*;
import rs.nbs.communicationoffice.AuthenticationHeader;

/**
 * A resolver class that links a Web Client object to a SOAP message header modifier.
 * Implements the {@link HandlerResolver} interface and injects authentication details
 * into the SOAP header via the {@link HeaderHandler}.
 * This class provides the handler chain required to attach authentication credentials
 * to outbound SOAP requests.
 *
 * @author andjela.djekic
 */
public class HeaderHandlerResolver implements HandlerResolver {

    private final AuthenticationHeader authenticationHeader;

    /**
     * Constructs a {@code HeaderHandlerResolver} with the given authentication credentials.
     *
     * @param authenticationHeader an instance of {@link AuthenticationHeader} containing
     *                             the username, password, and license ID for authentication.
     */
    public HeaderHandlerResolver(AuthenticationHeader authenticationHeader) {
        this.authenticationHeader = authenticationHeader;
    }

    /**
     * Creates a handler chain that includes the {@link HeaderHandler}, responsible for
     * modifying the SOAP header with authentication information.
     *
     * @param portInfo information about the port being used by the client (ignored here).
     * @return a {@link List} of {@link Handler} objects to be applied to outbound requests.
     */
    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {

        List<Handler> handlerChain = new ArrayList<>();
        
        //Wrapping the modifier in the HeaderHandler interface class that will be bound to the Web Client object
        HeaderHandler headerHandler = new HeaderHandler(authenticationHeader);
        handlerChain.add(headerHandler);

        return handlerChain;
    }

}
