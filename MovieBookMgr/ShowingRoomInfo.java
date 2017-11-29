/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieBookMgr;

/**
 *
 * @author dhdms
 */
public interface ShowingRoomInfo 
{
    int getEmpseat();
    int getUseseat();
    boolean addEmpseat();
    boolean addUseseat();
    boolean delEmpseat();
    boolean delUseseat();
}
