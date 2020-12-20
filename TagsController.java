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
 * 控制
 *
 */
 @Controller
public class TagsController extends  SimpleController{
     @Autowired
       private TagsService  tagsSrv=null;
       @Override
	@RequestMapping("/admin/tagsmanager.do")
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
		tagsSrv.delete(SQL);
	}
    /*************************************************************
     ****************保存动作监听支持******************************
    **************************************************************/
	public void save() {
			String forwardurl=request.getParameter("forwardurl");
			//验证错误url
			String errorurl=request.getParameter("errorurl");
				        String name=request.getParameter("name");
	    SimpleDateFormat  sdftags=new SimpleDateFormat("yyyy-MM-dd");
             Tags tags=new Tags();
				        tags.setName(name==null?"":name);
		    //产生验证
		     Boolean validateresult=tagsSrv.isExist( "where name='"+name+"'");
		     if(validateresult){
			 try {
					request.setAttribute("errormsg","<label class='error'>已存在的名称</label>");
					request.setAttribute("tags", tags);
					request.setAttribute("actiontype", "save");
					request.getRequestDispatcher(errorurl).forward(request, response);
				} catch (Exception e) {
						e.printStackTrace();
				}
				return;
			}
		  tagsSrv.save(tags);
		 if(forwardurl==null){
			forwardurl="/admin/tagsmanager.do?actiontype=get";
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
		   Tags  tags=tagsSrv.load(new Integer(id));
		    if(tags==null)
			   return;
				       String name=request.getParameter("name");
			  SimpleDateFormat  sdftags=new SimpleDateFormat("yyyy-MM-dd");
					        tags.setName(name);
		    tagsSrv.update(tags);
			 if(forwardurl==null){
				forwardurl="/admin/tagsmanager.do?actiontype=get";
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
			   Tags  tags=tagsSrv.load("where id="+id);
				if(tags!=null) 
				{
				    request.setAttribute("tags", tags);
				}
				actiontype="update";
				request.setAttribute("id", id);
			}
		    request.setAttribute("actiontype", actiontype);
		    String forwardurl=request.getParameter("forwardurl");
		    System.out.println("forwardurl="+forwardurl);
		    if(forwardurl==null){
		     	forwardurl="/admin/tagsadd.jsp";
		   }
		   forward(forwardurl);
	}
   	/******************************************************
	***********************数据绑定内部支持*********************
	*******************************************************/
	public void   get(){
		 String filter="where 1=1 ";
String name=request.getParameter("name");
	              if(name!=null)
	                     filter+="  and name like '%"+name+"%'  ";
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
		List<Tags> listtags = tagsSrv.getPageEntitys( filter,pageindex, pagesize);
		int recordscount = tagsSrv.getRecordCount(filter == null ? "" : filter);
		request.setAttribute("listtags", listtags);
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
			forwardurl="/admin/tagsmanager.jsp";
		}
forward(forwardurl);
	}
}
