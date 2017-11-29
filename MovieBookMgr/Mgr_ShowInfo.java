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
public class Mgr_ShowInfo 
{
    protected int Sales;
    protected Theater TheaterRef = null;
    public Mgr_ShowInfo(Theater TheaterRef) //parameter (정의되어있는 영화관리 객체) : 
    {
        this.TheaterRef = TheaterRef;
    }
    public  String getMovieInfo(int movieNum)//parameter (영화구분키) : string(영화제목, 영화개봉일, 상영시간)
    {
        StringBuffer buff = new StringBuffer();
        String result;
        for(MovieInfo MTemp : this.TheaterRef.MovieData)
        {
            if(MTemp.MovieNum == movieNum)
            {
                buff.append(MTemp.MovieName).append("\t").append(MTemp.ReleaseDay).append("\t").append(Integer.toString(MTemp.RunTime));
                break;
            }
        }
        result = new String(buff);
        return result;
    }
    public String getTheaterInfo(int MovieNum, int Menu) //parameter (영화기본키, 키값영화기준 0:상영관 할당정보  1:가장 가까운 상영시간  2: 가장 늦은 상영시간) : String (상영횟수, 상영관 빈 좌석, 상영관 사용 좌석)
    {
         int EmpSeat=0,UseSeat=0;
        StringBuffer Stemp = new StringBuffer();
        String result = null;
        
        
        switch(Menu)
        {
            case 0:
            for(MovieInfo MTemp : TheaterRef.MovieData)
                {
                    if(MTemp.MovieNum == MovieNum)
                    {
                        Stemp.append(Integer.toString(MTemp.Data.size())).append("\t");
                        for(ShowingRoom TTemp : MTemp.Data )
                        {
                            UseSeat+=TTemp.getUseseat();
                            EmpSeat+=TTemp.getEmpseat();
                            //Stemp.append(TTemp.getTime(0, 1)).append("\t");
                        }
                    }
                }
            Stemp.append(Integer.toString(EmpSeat)).append("\t").append(Integer.toString(UseSeat));
            result = new String(Stemp);
            break;
            
            case 1:
                for(MovieInfo MTemp : TheaterRef.MovieData)
                {
                    if(MTemp.MovieNum == MovieNum)
                    {
                        Stemp.append(MTemp.Data.get(0).getTime(0, 0));
                    }
                }
                result = new String(Stemp);
                        break;
                        
            case 2:
                  for(MovieInfo MTemp : TheaterRef.MovieData)
                {
                    if(MTemp.MovieNum == MovieNum)
                    {
                        Stemp.append(MTemp.Data.get(MTemp.Data.size()-1).getTime(0, 0));
                    }
                }
                result = new String(Stemp);
                break;
        }
   
    return result;
    }
   
}