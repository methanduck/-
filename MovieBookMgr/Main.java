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
public class Main 
{ 
    public static void main(String[] args) 
    {
        Theater MovieBookSet = new Theater();
       Manager Mgr = new Manager(MovieBookSet);
    boolean set = false;
        
       set = Mgr.AddMovieInfo("sexy", "7월2일", 120, 0);
       System.out.print(set);      System.out.print("\n");
       set = Mgr.AddMovieInfo("girl", "8월13일", 50, 1);
       System.out.print(set);      System.out.print("\n");
       set = Mgr.AddShowingRoom(0, "8:21");
       System.out.print(set);      System.out.print("\n");
       
       System.out.print(Mgr.getMovieInfo(0));
       System.out.print("\n");
       System.out.print(Mgr.getTheaterInfo(0,0));
       

    }       
}