package logic;

import logic.model.IUMImplementation;
import logic.model.IClientServer;

/**
 * @author dani
 */
public abstract class UserManagerFactory {    
    private static IClientServer ics;
    
    /** * @return an object that implements the interface
    * @see IClientServer
    */
    
   public static IClientServer getAccess() {
        if (ics == null)
            ics = new IUMImplementation();
        return ics;
   }
}
