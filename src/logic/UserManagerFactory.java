package logic;

import logic.model.IUMImplementation;
import logic.model.IClientServer;

/**
 * 
 * @author dani
 */
public class UserManagerFactory {
    private static IClientServer implementation = new IUMImplementation();
   /** * @return an object that implements the interface
    * @see IClientServer
    */
   public static IClientServer getAccess() {
        return implementation;
   }
}
