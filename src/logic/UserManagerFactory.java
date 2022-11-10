package logic;

import logic.model.IUMImplementation;
import logic.model.IClientServer;

public class UserManagerFactory {
   /** * @return an object that implements the interface
    * @see IClientServer
    */
   public static IClientServer getAccess() {
        return new IUMImplementation();
   }
}
