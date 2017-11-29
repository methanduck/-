/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieBookMgr;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 *
 * @author dhdms
 */
public class ShowingRoom implements ShowingRoomInfo
{
    private LocalTime StartTime;//상영시작시간
    private LocalTime EndTime;//상영종료시간
    private ArrayList<Integer> Empseat;//빈자리
    private ArrayList<Integer> Useseat;//사용중자리

    /*
    public ShowingRoom() 
    {
        StartTime.of(0, 0);
        EndTime.of(0, 0);
        Empseat = new ArrayList<>();
        Useseat = new ArrayList<>();
       // Theaternum =0;
        for (int i = 0; i < 10; i++) 
        {
            Empseat.add(0);
        }
    }
*/ //파라미터 없이 초기화되는 생성자 삭제
     public ShowingRoom(LocalTime STime, LocalTime ETime)
     {
         this.StartTime = STime;
         this.EndTime = ETime;
         Empseat = new ArrayList<>();
         Useseat = new ArrayList<>();
          for (int i = 0; i < 10; i++) 
        {
            Empseat.add(1);
        }
     }
    
    @Override
    public int getEmpseat() 
    {
        return this.Empseat.size();
    }
    @Override
    public int getUseseat() 
    {
        return this.Useseat.size();
    }
    @Override
    public boolean addEmpseat() 
    {
        try {
             this.Empseat.add(1);
            this.Useseat.remove(Useseat.size());
            return true;
        } catch (IndexOutOfBoundsException e) 
        {
            return false;
        }
       
    }
    @Override
    public boolean addUseseat() 
    {
        this.Useseat.add(0);
        try 
        {
            this.Empseat.remove(Empseat.size());
            return true;
        } catch (IndexOutOfBoundsException e) 
        {
            return false;
        }
    }
    @Override
    public boolean delEmpseat()
    {
        try 
        {
             this.Empseat.remove(Empseat.size());
            this.Useseat.add(1);
            return true;
        } catch (IndexOutOfBoundsException e) 
        {
            return false;
        }
       
    }
    @Override
    public boolean delUseseat() 
    {
        try {
            this.Useseat.remove(Useseat.size());
            this.Empseat.add(1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
            
    }
   
    public Object getTime(int Menu,int distinguish) //parameter Menu)string출력 0 ,LocalTime출력 1 distinguish ==0)시작시간 ==1)종료시간
    {
        StringBuffer buff = new StringBuffer();
        Object result = null;
        switch(Menu)
        {
            case 0:
                if(distinguish == 0)
                {
                buff.append(this.StartTime.getHour());
                buff.append(":");
                buff.append(this.StartTime.getMinute());
                result = new String(buff);
                }
                else
                {
                buff.append(this.EndTime.getHour());
                buff.append(":");
                buff.append(this.EndTime.getMinute());
                result = new String(buff);
                }
               break;
               
            case 1:
                if(distinguish == 0)
                    result = this.StartTime;
                else
                    result=this.EndTime;
        }
        return result;
    }
}
