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
 * 酒店控制
 *
 */
@Controller
public class JiudianController extends SimpleController {
	@Autowired
	private JiudianService jiudianSrv = null;
	@Autowired
	private AreacityService areacitySrv = null;

	@Override
	@RequestMapping("/admin/jiudianmanager.do")
	public void mapping(HttpServletRequest request, HttpServletResponse response) {
		mappingMethod(request, response);
	}

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String[] ids = request.getParameterValues("ids");
		if (ids == null)
			return;
		String spliter = ",";
		String SQL = " where id in(" + join(spliter, ids) + ")";
		System.out.println("sql=" + SQL);
		jiudianSrv.delete(SQL);
	}


	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String errorurl = request.getParameter("errorurl");
		String title = request.getParameter("title");
		String brand = request.getParameter("brand");
		String jibie = request.getParameter("jibie");
		String tupian = request.getParameter("tupian");
		String city = request.getParameter("city");
		String des = request.getParameter("des");
		String address = request.getParameter("address");
		SimpleDateFormat sdfjiudian = new SimpleDateFormat("yyyy-MM-dd");
		Jiudian jiudian = new Jiudian();
		jiudian.setTitle(title == null ? "" : title);
		jiudian.setBrand(brand == null ? "" : brand);
		jiudian.setJibie(jibie == null ? "" : jibie);
		jiudian.setTupian(tupian == null ? "" : tupian);
		jiudian.setCity(city == null ? "" : city);
		jiudian.setDes(des == null ? "" : des);
		jiudian.setAddress(address == null ? "" : address);
		jiudianSrv.save(jiudian);
		if (forwardurl == null) {
			forwardurl = "/admin/jiudianmanager.do?actiontype=get";
		}
		redirect(forwardurl);
	}

	
	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String forwardurl = request.getParameter("forwardurl");
		String id = request.getParameter("id");
		if (id == null)
			return;
		Jiudian jiudian = jiudianSrv.load(new Integer(id));
		if (jiudian == null)
			return;
		String title = request.getParameter("title");
		String brand = request.getParameter("brand");
		String jibie = request.getParameter("jibie");
		String tupian = request.getParameter("tupian");
		String city = request.getParameter("city");
		String des = request.getParameter("des");
		String address = request.getParameter("address");
		SimpleDateFormat sdfjiudian = new SimpleDateFormat("yyyy-MM-dd");
		jiudian.setTitle(title);
		jiudian.setBrand(brand);
		jiudian.setJibie(jibie);
		jiudian.setTupian(tupian);
		jiudian.setCity(city);
		jiudian.setDes(des);
		jiudian.setAddress(address);
		jiudianSrv.update(jiudian);
		if (forwardurl == null) {
			forwardurl = "/admin/jiudianmanager.do?actiontype=get";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 加载内部支持*********************
	 *******************************************************/
	public void load() {
		//
		String id = request.getParameter("id");
		
		String actiontype = "save";
		dispatchParams(request, response);
		if (id != null) {
			Jiudian jiudian = jiudianSrv.load("where id="
					+ id);
			if (jiudian != null) {
				request.setAttribute("jiudian", jiudian);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		List<Object> city_datasource = areacitySrv.getEntity("");
		request.setAttribute("city_datasource", city_datasource);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/jiudianadd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void get() {
		String filter = "where 1=1 ";
		String title = request.getParameter("title");
		if (title != null)
			filter += "  and title like '%" + title + "%'  ";
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
		List<Jiudian> listjiudian = jiudianSrv.getPageEntitys(filter,
				pageindex, pagesize);
		int recordscount = jiudianSrv.getRecordCount(filter == null ? "" : filter);
		request.setAttribute("listjiudian", listjiudian);
		PagerMetal pm = new PagerMetal(recordscount);
		// 设置尺寸
		pm.setPagesize(pagesize);
		// 设置当前显示页
		pm.setCurpageindex(pageindex);
		// 设置分页信息
		request.setAttribute("pagermetal", pm);
		// 分发请求参数
		dispatchParams(request, response);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/jiudianmanager.jsp";
		}
		forward(forwardurl);
	}
}
