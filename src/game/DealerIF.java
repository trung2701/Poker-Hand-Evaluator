/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreakfromaithepokergame;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Shine
 */
public interface DealerIF extends Remote{
    
    void register(PlayerIF player) throws RemoteException;
}
