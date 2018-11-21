/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registry;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import remoteInterface.RemoteInterface;

/**
 *
 * @author wnncr
 */
public class Register {
    public static RemoteInterface registry() throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry("localhost",Registry.REGISTRY_PORT);
        RemoteInterface stub = (RemoteInterface) registry.lookup("remoteInterface");
        return stub;
    }
}
