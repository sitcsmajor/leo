package com.daowen.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Jdyuding
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
   private String jdtitle ;
   public String getJdtitle() 
   {
      return jdtitle;
  }
   public void setJdtitle(String jdtitle) 
   {
      this.jdtitle= jdtitle;
  }
   private String kfname ;
   public String getKfname() 
   {
      return kfname;
  }
   public void setKfname(String kfname) 
   {
      this.kfname= kfname;
  }
   private Date begindate ;
   public Date getBegindate() 
   {
      return begindate;
  }
   public void setBegindate(Date begindate) 
   {
      this.begindate= begindate;
  }
   private Date enddate ;
   public Date getEnddate() 
   {
      return enddate;
  }
   public void setEnddate(Date enddate) 
   {
      this.enddate= enddate;
  }
   private int tianshu ;
   public int getTianshu() 
   {
      return tianshu;
  }
   public void setTianshu(int tianshu) 
   {
      this.tianshu= tianshu;
  }
   private String ruzhuren ;
   public String getRuzhuren() 
   {
      return ruzhuren;
  }
   public void setRuzhuren(String ruzhuren) 
   {
      this.ruzhuren= ruzhuren;
  }
   private int shuliang ;
   public int getShuliang() 
   {
      return shuliang;
  }
   public void setShuliang(int shuliang) 
   {
      this.shuliang= shuliang;
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
   private int status ;
   public int getStatus() 
   {
      return status;
  }
   public void setStatus(int status) 
   {
      this.status= status;
  }
   private String ydren ;
   public String getYdren() 
   {
      return ydren;
  }
   public void setYdren(String ydren) 
   {
      this.ydren= ydren;
  }
   private Double totalprice ;
   public Double getTotalprice() 
   {
      return totalprice;
  }
   public void setTotalprice(Double totalprice) 
   {
      this.totalprice= totalprice;
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
}
