package logic;

import logic.model.IUMImplementation;
import logic.model.IClientServer;

/**
 * 
 * @author dani
 */
public class UserManagerFactory {
   /** * @return an object that implements the interface
    * @see IClientServer
    */
   private static IUMImplementation implementation = new IUMImplementation();
    
   public static IClientServer getAccess() {
        return implementation;
   }
}
