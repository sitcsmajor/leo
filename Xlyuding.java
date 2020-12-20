package com.daowen.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Xlyuding
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
   private int xlid ;
   public int getXlid() 
   {
      return xlid;
  }
   public void setXlid(int xlid) 
   {
      this.xlid= xlid;
  }
   private String xltitle ;
   public String getXltitle() 
   {
      return xltitle;
  }
   public void setXltitle(String xltitle) 
   {
      this.xltitle= xltitle;
  }
   private Date yddate ;
   public Date getYddate() 
   {
      return yddate;
  }
   public void setYddate(Date yddate) 
   {
      this.yddate= yddate;
  }
   private int renshu ;
   public int getRenshu() 
   {
      return renshu;
  }
   public void setRenshu(int renshu) 
   {
      this.renshu= renshu;
  }
   private String hyname ;
   public String getHyname() 
   {
      return hyname;
  }
   public void setHyname(String hyname) 
   {
      this.hyname= hyname;
  }
   private String lianxiren ;
   public String getLianxiren() 
   {
      return lianxiren;
  }
   public void setLianxiren(String lianxiren) 
   {
      this.lianxiren= lianxiren;
  }
   private String mobile ;
   public String getMobile() 
   {
      return mobile;
  }
   public void setMobile(String mobile) 
   {
      this.mobile= mobile;
  }
   private Double jine ;
   public Double getJine() 
   {
      return jine;
  }
   public void setJine(Double jine) 
   {
      this.jine= jine;
  }
   private int fangjiashu ;
   public int getFangjiashu() 
   {
      return fangjiashu;
  }
   public void setFangjiashu(int fangjiashu) 
   {
      this.fangjiashu= fangjiashu;
  }
   private String status ;
   public String getStatus() 
   {
      return status;
  }
   public void setStatus(String status) 
   {
      this.status= status;
  }
}
