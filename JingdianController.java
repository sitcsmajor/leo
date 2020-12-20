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
public class JingdianController extends SimpleController {
	@Autowired
	private JingdianService jingdianSrv = null;

	@Override
	@RequestMapping("/admin/jingdianmanager.do")
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
		jingdianSrv.delete(SQL);
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String errorurl = request.getParameter("errorurl");
		String title = request.getParameter("title");
		String pubren = request.getParameter("pubren");
		String pubtime = request.getParameter("pubtime");
		String tel = request.getParameter("tel");
		String kftime = request.getParameter("kftime");
		String des = request.getParameter("des");
		String tupian = request.getParameter("tupian");
		String menpiao = request.getParameter("menpiao");
		SimpleDateFormat sdfjingdian = new SimpleDateFormat("yyyy-MM-dd");
		Jingdian jingdian = new Jingdian();
		jingdian.setTitle(title == null ? "" : title);
		jingdian.setPubren(pubren == null ? "" : pubren);
		if (pubtime != null) {
			try {
				jingdian.setPubtime(sdfjingdian.parse(pubtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			jingdian.setPubtime(new Date());
		}
		jingdian.setTel(tel == null ? "" : tel);
		jingdian.setKftime(kftime == null ? "" : kftime);
		jingdian.setDes(des == null ? "" : des);
		jingdian.setTupian(tupian == null ? "" : tupian);
		jingdian.setMenpiao(menpiao == null ? "" : menpiao);
		jingdianSrv.save(jingdian);
		if (forwardurl == null) {
			forwardurl = "/admin/jingdianmanager.do?actiontype=get";
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
		Jingdian jingdian = jingdianSrv.load(new Integer(id));
		if (jingdian == null)
			return;
		String title = request.getParameter("title");
		String pubren = request.getParameter("pubren");
		String pubtime = request.getParameter("pubtime");
		String tel = request.getParameter("tel");
		String kftime = request.getParameter("kftime");
		String des = request.getParameter("des");
		String tupian = request.getParameter("tupian");
		String menpiao = request.getParameter("menpiao");
		SimpleDateFormat sdfjingdian = new SimpleDateFormat("yyyy-MM-dd");
		jingdian.setTitle(title);
		jingdian.setPubren(pubren);
		if (pubtime != null) {
			try {
				jingdian.setPubtime(sdfjingdian.parse(pubtime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		jingdian.setTel(tel);
		jingdian.setKftime(kftime);
		jingdian.setDes(des);
		jingdian.setTupian(tupian);
		jingdian.setMenpiao(menpiao);
		jingdianSrv.update(jingdian);
		if (forwardurl == null) {
			forwardurl = "/admin/jingdianmanager.do?actiontype=get";
		}
		redirect(forwardurl);
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
			Jingdian jingdian = jingdianSrv.load("where id=" + id);
			if (jingdian != null) {
				request.setAttribute("jingdian", jingdian);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/jingdianadd.jsp";
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
		List<Jingdian> listjingdian = jingdianSrv.getPageEntitys(filter,
				pageindex, pagesize);
		int recordscount = jingdianSrv.getRecordCount(filter == null ? ""
				: filter);
		request.setAttribute("listjingdian", listjingdian);
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
			forwardurl = "/admin/jingdianmanager.jsp";
		}
		forward(forwardurl);
	}
}
