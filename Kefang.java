package com.daowen.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Kefang
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
   private String name ;
   public String getName() 
   {
      return name;
  }
   public void setName(String name) 
   {
      this.name= name;
  }
   private Double price ;
   public Double getPrice() 
   {
      return price;
  }
   public void setPrice(Double price) 
   {
      this.price= price;
  }
   private int jdid ;
   public int getJdid() 
   {
      return jdid;
  }
   public void setJdid(int jdid) 
   {
      this.jdid= jdid;
  }
}
