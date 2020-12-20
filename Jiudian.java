package com.daowen.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Jiudian
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
   private String brand ;
   public String getBrand() 
   {
      return brand;
  }
   public void setBrand(String brand) 
   {
      this.brand= brand;
  }
   private String jibie ;
   public String getJibie() 
   {
      return jibie;
  }
   public void setJibie(String jibie) 
   {
      this.jibie= jibie;
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
   private String city ;
   public String getCity() 
   {
      return city;
  }
   public void setCity(String city) 
   {
      this.city= city;
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
   private String address ;
   public String getAddress() 
   {
      return address;
  }
   public void setAddress(String address) 
   {
      this.address= address;
  }
}
