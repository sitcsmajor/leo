package com.daowen.controller;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.daowen.entity.*;
import com.daowen.service.*;
import com.daowen.ssm.simplecrud.SimpleController;
import com.daowen.webcontrol.PagerMetal;
/**************************
 *  
 * 地区城市控制
 *
 */
 @Controller
public class AreacityController extends  SimpleController{
     @Autowired
       private AreacityService  areacitySrv=null;
       @Override
	@RequestMapping("/admin/areacitymanager.do")
	public void mapping(HttpServletRequest request, HttpServletResponse response) {
		mappingMethod(request,response);
	}
    /********************************************************
    ******************信息注销监听支持*****************************
    *********************************************************/
	public void delete() {
		String[] ids = request.getParameterValues("ids");
		if (ids == null)
			return;
		String spliter = ",";
		String SQL = " where id in(" + join(spliter, ids)
				+ ")";
		System.out.println("sql=" + SQL);
		areacitySrv.delete(SQL);
	}
    /*************************************************************
     ****************保存动作监听支持******************************
    **************************************************************/
	public void save() {
			String forwardurl=request.getParameter("forwardurl");
			//验证错误url
			String errorurl=request.getParameter("errorurl");
				        String cityname=request.getParameter("cityname");
	    SimpleDateFormat  sdfareacity=new SimpleDateFormat("yyyy-MM-dd");
             Areacity areacity=new Areacity();
				        areacity.setCityname(cityname==null?"":cityname);
		    //产生验证
		     Boolean validateresult=areacitySrv.isExist( "where cityname='"+cityname+"'");
		     if(validateresult){
			 try {
					request.setAttribute("errormsg","<label class='error'>已存在的城市</label>");
					request.setAttribute("areacity", areacity);
					request.setAttribute("actiontype", "save");
					request.getRequestDispatcher(errorurl).forward(request, response);
				} catch (Exception e) {
						e.printStackTrace();
				}
				return;
			}
		  areacitySrv.save(areacity);
		 if(forwardurl==null){
			forwardurl="/admin/areacitymanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}
   	/******************************************************
	***********************更新内部支持*********************
	*******************************************************/
	public void update() {
           String forwardurl=request.getParameter("forwardurl");
		   String id=request.getParameter("id");
		   if(id==null)
			   return;
		   Areacity  areacity=areacitySrv.load(new Integer(id));
		    if(areacity==null)
			   return;
				       String cityname=request.getParameter("cityname");
			  SimpleDateFormat  sdfareacity=new SimpleDateFormat("yyyy-MM-dd");
					        areacity.setCityname(cityname);
		    areacitySrv.update(areacity);
			 if(forwardurl==null){
				forwardurl="/admin/areacitymanager.do?actiontype=get";
			}
			redirect(forwardurl);
	}
   	/******************************************************
	***********************加载内部支持*********************
	*******************************************************/
	public void load() {
	        //
		    String id=request.getParameter("id");
		    String actiontype="save";
		    dispatchParams(request, response);
			if(id!=null) 
			{
			   Areacity  areacity=areacitySrv.load("where id="+id);
				if(areacity!=null) 
				{
				    request.setAttribute("areacity", areacity);
				}
				actiontype="update";
				request.setAttribute("id", id);
			}
		    request.setAttribute("actiontype", actiontype);
		    String forwardurl=request.getParameter("forwardurl");
		    System.out.println("forwardurl="+forwardurl);
		    if(forwardurl==null){
		     	forwardurl="/admin/areacityadd.jsp";
		   }
		   forward(forwardurl);
	}
   	/******************************************************
	***********************数据绑定内部支持*********************
	*******************************************************/
	public void   get(){
		 String filter="where 1=1 ";
String cityname=request.getParameter("cityname");
	              if(cityname!=null)
	                     filter+="  and cityname like '%"+cityname+"%'  ";
		 //
		int pageindex = 1;
		int pagesize = 10;
		// 获取当前分页
		String currentpageindex = request.getParameter("currentpageindex");
		// 当前页面尺寸
		String currentpagesize = request.getParameter("pagesize");
		// 设置当前页
		if (currentpageindex != null)
			pageindex = new Integer(currentpageindex);
		// 设置当前页尺寸
		if (currentpagesize != null)
			pagesize = new Integer(currentpagesize);
		List<Areacity> listareacity = areacitySrv.getPageEntitys( filter,pageindex, pagesize);
		int recordscount = areacitySrv.getRecordCount(filter == null ? "" : filter);
		request.setAttribute("listareacity", listareacity);
		PagerMetal pm = new PagerMetal(recordscount);
		//设置尺寸
		pm.setPagesize(pagesize);
		//设置当前显示页
		pm.setCurpageindex(pageindex);
		// 设置分页信息
		request.setAttribute("pagermetal", pm);
		//分发请求参数
		dispatchParams(request, response);
		String forwardurl=request.getParameter("forwardurl");
		System.out.println("forwardurl="+forwardurl);
		if(forwardurl==null){
			forwardurl="/admin/areacitymanager.jsp";
		}
forward(forwardurl);
	}
}
