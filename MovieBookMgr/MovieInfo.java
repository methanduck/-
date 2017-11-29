/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieBookMgr;
import java.util.ArrayList;
/**
 *
 * @author dhdms
 */
public class MovieInfo extends AbsMovieInfo 
{
     public boolean InitBefore;
     ArrayList<ShowingRoom> Data = new ArrayList<>(); //현재 이 영화의 상영관 데이터
     public MovieInfo()
     {
         super();
         this.InitBefore=false;
     }
    public MovieInfo(String MovieName, String ReleaseDay, int RunTime,int MovieNum)//parameter name)영화제목 Month)출시월 Day)출시일 
    {
        super(MovieName,ReleaseDay,RunTime,MovieNum);
        this.InitBefore = true;
    }
}