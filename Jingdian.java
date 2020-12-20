package com.daowen.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Jingdian
{
@Id
@GeneratedValue(strategy =GenerationType.AUTO)
   private int id ;
   public int getId() 
   {
      return id;
  }
   public void setId(int id) 
   {
      this.id= id;
  }
   private String title ;
   public String getTitle() 
   {
      return title;
  }
   public void setTitle(String title) 
   {
      this.title= title;
  }
   private String pubren ;
   public String getPubren() 
   {
      return pubren;
  }
   public void setPubren(String pubren) 
   {
      this.pubren= pubren;
  }
   private Date pubtime ;
   public Date getPubtime() 
   {
      return pubtime;
  }
   public void setPubtime(Date pubtime) 
   {
      this.pubtime= pubtime;
  }
   private String tel ;
   public String getTel() 
   {
      return tel;
  }
   public void setTel(String tel) 
   {
      this.tel= tel;
  }
   private String kftime ;
   public String getKftime() 
   {
      return kftime;
  }
   public void setKftime(String kftime) 
   {
      this.kftime= kftime;
  }
   private String des ;
   public String getDes() 
   {
      return des;
  }
   public void setDes(String des) 
   {
      this.des= des;
  }
   private String tupian ;
   public String getTupian() 
   {
      return tupian;
  }
   public void setTupian(String tupian) 
   {
      this.tupian= tupian;
  }
   private String menpiao ;
   public String getMenpiao() 
   {
      return menpiao;
  }
   public void setMenpiao(String menpiao) 
   {
      this.menpiao= menpiao;
  }
}
