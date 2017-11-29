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
abstract public class AbsMovieInfo 
{
    protected int MovieNum;
    protected String MovieName;
    protected String ReleaseDay;
    protected int RunTime;
    
    public AbsMovieInfo()//parameter default값 초기화
    {
        this.MovieNum = -1; 
        this.RunTime =0;
        this.MovieName =null;
        this.ReleaseDay=null;
    }
    public AbsMovieInfo(String MovieName,String ReleaseDay, int RunTime,int MovieNum) //parameter (영화제목,개봉일,상영길이,영화기본키)
    {
        this.MovieNum = MovieNum;
        this.MovieName = MovieName;
        this.RunTime=RunTime;
        this.ReleaseDay = ReleaseDay;
    }
    
    /* 구현된 메서드
    public String getReleaseDay()
    {
         StringBuffer buff = new StringBuffer();
         String result;
        
        buff.append(this.MovieName).append("\t").append(this.ReleaseDay);
        result = new String(buff);
        
        return result;
    }
    */
    public String getName()
    {
         return this.MovieName;
    }
    public void setName(String Name)
    {
        this.MovieName = Name;
    }
}
