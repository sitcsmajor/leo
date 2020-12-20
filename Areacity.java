package com.daowen.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Areacity
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
   private String cityname ;
   public String getCityname() 
   {
      return cityname;
  }
   public void setCityname(String cityname) 
   {
      this.cityname= cityname;
  }
}
