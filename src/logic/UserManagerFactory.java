package logic;

import logic.model.IUMImplementation;
import logic.model.IUserManager;

public class UserManagerFactory {
   /**@return an object that implements the interface
    * @see IUserManager
    */
   public static IUserManager getAccess() {
        return new IUMImplementation();
   }
}
