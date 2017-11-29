/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieBookMgr;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author dhdms
 */
public class Manager extends Mgr_ShowInfo
{
    boolean result;
    public Manager(Theater TheaterRef) 
    {
        super(TheaterRef);
    }
    public boolean AddMovieInfo(String MovieName, String ReleseDay, int RunTime, int MovieNum)//parameter (영화제목, 개봉일, 상영길이, 영화기본키)  //영화추가메서드
    {
        boolean Result; //추가연산 성공여부 확인 불린
        if(this.TheaterRef.MovieData.size() == 3)
          Result = false;//추가 실패
        else
        {
        MovieInfo Temp = new MovieInfo(MovieName, ReleseDay, RunTime, MovieNum);
        this.TheaterRef.MovieData.add(Temp);
        Result = true; //추가성공
        }
        return Result;
    }
    public void SetMovieInfo(String FindName,String MovieName, String ReleseDay, int RunTime, int MovieNum)//parameter (바꿀영화제목, 설정할 개봉일, 설정할 상영길이, 설정할 영화기본키)  //영화변경메서드
    {
        this.TheaterRef.MovieData.stream().filter((MTemp) -> (MTemp.MovieName.equals(FindName))).forEachOrdered((MTemp) ->
        {
            if(MovieNum == -1)
            {
                MTemp.MovieName = MovieName;
                MTemp.ReleaseDay = ReleseDay;
                MTemp.RunTime = RunTime;
            }
            else
            {
                MTemp.MovieName = MovieName;
                MTemp.ReleaseDay = ReleseDay;
                MTemp.RunTime = RunTime;
                MTemp.MovieNum=MovieNum;
            }
        });
    }
    public boolean AddShowingRoom(int MovieNum,String STime) //parameter (영화기본키, 상영시작시간)
    {
        boolean CheckResult = false;
        String Stemp[] = STime.split(":");
        LocalTime inputSTime = LocalTime.of(Integer.parseInt(Stemp[0]), Integer.parseInt(Stemp[1])); 
        LocalTime inputETime = LocalTime.of(Integer.parseInt(Stemp[0]), Integer.parseInt(Stemp[1]));

             for(MovieInfo MTemp : this.TheaterRef.MovieData)
            {
                if(MTemp.MovieNum == MovieNum)
                {
                    inputETime.plusMinutes(MTemp.RunTime);
                }
                
               if(this.CkShowingRoom(MTemp, inputSTime, inputETime))
               {
                   ShowingRoom NewFace = new ShowingRoom(inputSTime, inputETime);
                   MTemp.Data.add(NewFace);
                   CheckResult = true; // 연산 성공 시
                   Compare CompObject = new Compare();
                   Collections.sort(MTemp.Data,CompObject);
               }
            }
             
      return CheckResult;
    }
     public void SetShowingRoom(int MovieNum, String FindTime,String STime) //parameter (영화기본키, 찾을 상영시작시간, 설정할 상영시작시간)
    {
        boolean CheckResult = false;
        int index=0;
        String Stemp[] = STime.split(":");
        LocalTime inputSTime = LocalTime.of(Integer.parseInt(Stemp[0]), Integer.parseInt(Stemp[1])); 
        LocalTime inputETime = LocalTime.of(Integer.parseInt(Stemp[0]), Integer.parseInt(Stemp[1]));
        ShowingRoom Input;
        for(MovieInfo MTemp : this.TheaterRef.MovieData)
        {
            if(MTemp.MovieNum == MovieNum)
            {
                for(ShowingRoom STemp : MTemp.Data)
                {
                    if(STemp.getTime(0, 0).equals(FindTime))
                    {
                        break;
                    }
                    index++;
                }
                
                inputETime.plusMinutes(MTemp.RunTime);
               if(this.CkShowingRoom(MTemp, inputSTime, inputETime))
               {
                   Input = new ShowingRoom(inputSTime, inputETime);
                   MTemp.Data.set(index,Input);
               }
            }
        }
    }
     public boolean CkShowingRoom(MovieInfo Path,LocalTime inputSTime, LocalTime inputETime) //parameter (연산할 영화컬렉션, 비교할 영화시작시간, 비교할 영화종료시간)//함수 내 연산메서드
    {
        boolean Check = false;
        if(Path.Data.isEmpty())
           {
               Check = true;
           }
           else if(Path.Data.size() == 1)
           {
               if(inputSTime.isAfter((LocalTime) Path.Data.get(0).getTime(1, 1))&&inputETime.isBefore((LocalTime) Path.Data.get(0).getTime(1, 0)))
               {
                   Check = true;
               }
           }
            else
           {
                for (int i = 0; i < Path.Data.size()-1; i++) 
                {
                    if(inputSTime.isAfter((LocalTime) Path.Data.get(i).getTime(1, 1))&&inputETime.isBefore((LocalTime) Path.Data.get(i+1).getTime(1, 0)))
                    {
                    Check = true;
                    }
                }
           }
        return Check;
    }
     public boolean AddSeat(int Menu, int MovieNum, String STime)//parameter (Menu( 0: EmpSeat 1: UseSeat), 영화키값, 상영시작시간) :boolean 연산성공여부 리턴(True: 성공 False : 실패)
     {
         this.result = false;
         switch(Menu)
        {
                case 0:
                     this.TheaterRef.MovieData.stream().filter((Mtemp) -> {
                return Mtemp.MovieNum == MovieNum;
            })
                    .forEachOrdered((MovieInfo temp) -> {
                       temp.Data.stream().filter((ShowingRoom STemp) -> {
                           return STime.equals(STemp.getTime(0, 0));
                       }).forEachOrdered((ShowingRoom STemp) -> {
                       this.result=STemp.addEmpseat();

                       });
                    });
                     break;

                case 1:
                    this.TheaterRef.MovieData.stream().filter((Mtemp) -> {
                return Mtemp.MovieNum == MovieNum;
            })
                    .forEachOrdered((MovieInfo temp) -> {
                       temp.Data.stream().filter((ShowingRoom STemp) -> {
                           return STime.equals(STemp.getTime(0, 0));
                       }).forEachOrdered((ShowingRoom STemp) -> {
                       this.result=STemp.addUseseat();
                       });
                    });
         }
         return this.result;
     }
     public boolean DelSeat(int Menu, int MovieNum, String STime)//parameter (Menu( 0: EmpSeat 1: UseSeat), 영화키값, 상영시작시간) :boolean 연산성공여부 리턴(True: 성공 False : 실패)
     {
         this.result = false;
         switch(Menu)
         {
             case 0:
                 this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
                 return MTemp.MovieNum == MovieNum;
                 }).forEachOrdered((MovieInfo MTemp) -> {
                 MTemp.Data.stream().filter((ShowingRoom STemp) -> {
                   return STime.equals(STemp.getTime(0, 0));
                 }).forEachOrdered((ShowingRoom STemp) -> {
                 this.result = STemp.delEmpseat();
                 });
                 });
                 break;
                 
             case 1:
                  this.TheaterRef.MovieData.stream().filter((MovieInfo MTemp) -> {
                 return MTemp.MovieNum == MovieNum;
                 }).forEachOrdered((MovieInfo MTemp) -> {
                 MTemp.Data.stream().filter((ShowingRoom STemp) -> {
                   return STime.equals(STemp.getTime(0, 0));
                 }).forEachOrdered((ShowingRoom STemp) -> {
                 this.result = STemp.delUseseat();
                 });
                 });
                 break;
         }
         return this.result;
     }
     
     
     class Compare implements Comparator<MovieBookMgr.ShowingRoom>
    {
        @Override
        public int compare(ShowingRoom t, ShowingRoom t1) 
        {
            if(((LocalTime)t.getTime(1, 0)).isBefore((LocalTime)t1.getTime(1, 0)))
            return 1;
            else if(((LocalTime)t1.getTime(1, 0)).isBefore((LocalTime)t.getTime(1, 0)))
            return -1;
            else
             return 0;
        }
    }
     
}