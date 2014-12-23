/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreakfromaithepokergame;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Shine
 */
public interface PlayerIF extends Remote{
    
    void receiveCard(Card card) throws RemoteException;
    Pair evaluateHand(ArrayList<Card> l) throws RemoteException;
}
