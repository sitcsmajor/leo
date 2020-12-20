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
 * 线路控制
 *
 */
@Controller
public class XianluController extends SimpleController {
	@Autowired
	private XianluService xianluSrv = null;
	@Autowired
	private  XltypeService xltypeSrv=null;
	
	@Autowired
	private  CystyleService cystyleSrv=null;
	@Autowired
	private  TagsService tagsSrv=null;

	@Override
	@RequestMapping("/admin/xianlumanager.do")
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
		xianluSrv.delete(SQL);
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String errorurl = request.getParameter("errorurl");
		String chufadi = request.getParameter("chufadi");
		String mudidi = request.getParameter("mudidi");
		String shijiananpai = request.getParameter("shijiananpai");
		String fatuanriqi = request.getParameter("fatuanriqi");
		String jiaotongxinxi = request.getParameter("jiaotongxinxi");
		String shichangjia = request.getParameter("shichangjia");
		String hyjia = request.getParameter("hyjia");
		String des = request.getParameter("des");
		String tupian = request.getParameter("tupian");
		String title = request.getParameter("title");
		String typeid=request.getParameter("typeid");
	    String typename=request.getParameter("typename");
	    String cyid=request.getParameter("cyid");
        String cyname=request.getParameter("cyname");
        String tagid=request.getParameter("tagid");
        String tagname=request.getParameter("tagname");
		SimpleDateFormat sdfxianlu = new SimpleDateFormat("yyyy-MM-dd");
		Xianlu xianlu = new Xianlu();
		xianlu.setChufadi(chufadi == null ? "" : chufadi);
		xianlu.setMudidi(mudidi == null ? "" : mudidi);
		xianlu.setShijiananpai(shijiananpai == null ? "" : shijiananpai);
		xianlu.setFatuanriqi(fatuanriqi == null ? "" : fatuanriqi);
		xianlu.setJiaotongxinxi(jiaotongxinxi == null ? "" : jiaotongxinxi);
		xianlu.setShichangjia(shichangjia == null ? (double) 0 : new Double(
				shichangjia));
		xianlu.setHyjia(hyjia == null ? (double) 0 : new Double(hyjia));
		xianlu.setDes(des == null ? "" : des);
		xianlu.setTupian(tupian == null ? "" : tupian);
		xianlu.setTitle(title == null ? "" : title);
		xianlu.setTypeid(typeid==null?0:new Integer(typeid));
	    xianlu.setTypename(typename==null?"":typename);
	    xianlu.setCyid(cyid==null?0:new Integer(cyid));
        xianlu.setCyname(cyname==null?"":cyname);
       xianlu.setTagid(tagid==null?0:new Integer(tagid));
        xianlu.setTagname(tagname==null?"":tagname);
		xianluSrv.save(xianlu);
		
		if (forwardurl == null) {
			forwardurl = "/admin/xianlumanager.do?actiontype=get";
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
		Xianlu xianlu = xianluSrv.load(new Integer(id));
		if (xianlu == null)
			return;
		String chufadi = request.getParameter("chufadi");
		String mudidi = request.getParameter("mudidi");
		String shijiananpai = request.getParameter("shijiananpai");
		String fatuanriqi = request.getParameter("fatuanriqi");
		String jiaotongxinxi = request.getParameter("jiaotongxinxi");
		String shichangjia = request.getParameter("shichangjia");
		String hyjia = request.getParameter("hyjia");
		String des = request.getParameter("des");
		String tupian = request.getParameter("tupian");
		String title = request.getParameter("title");
		String typeid=request.getParameter("typeid");
	    String typename=request.getParameter("typename");
	    String cyid=request.getParameter("cyid");
        String cyname=request.getParameter("cyname");
        String tagid=request.getParameter("tagid");
        String tagname=request.getParameter("tagname");
		SimpleDateFormat sdfxianlu = new SimpleDateFormat("yyyy-MM-dd");
		xianlu.setChufadi(chufadi);
		xianlu.setMudidi(mudidi);
		xianlu.setShijiananpai(shijiananpai);
		xianlu.setFatuanriqi(fatuanriqi);
		xianlu.setJiaotongxinxi(jiaotongxinxi);
		xianlu.setShichangjia(shichangjia == null ? (double) 0 : new Double(
				shichangjia));
		xianlu.setHyjia(hyjia == null ? (double) 0 : new Double(hyjia));
		xianlu.setDes(des);
		xianlu.setTupian(tupian);
		xianlu.setTitle(title);
		xianlu.setTypeid(typeid==null?0:new Integer(typeid));
	    xianlu.setTypename(typename==null?"":typename);
	    xianlu.setCyid(cyid==null?0:new Integer(cyid));
        xianlu.setCyname(cyname==null?"":cyname);
       xianlu.setTagid(tagid==null?0:new Integer(tagid));
        xianlu.setTagname(tagname==null?"":tagname);
		xianluSrv.update(xianlu);

		if (forwardurl == null) {
			forwardurl = "/admin/xianlumanager.do?actiontype=get";
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
			Xianlu xianlu = xianluSrv.load("where id=" + id);
			if (xianlu != null) {
				request.setAttribute("xianlu", xianlu);
			}
			actiontype = "update";
			request.setAttribute("id", id);
			
		}
		request.setAttribute("actiontype", actiontype);
		List<Object> typeid_datasource=xltypeSrv.getEntity("");
        request.setAttribute("typeid_datasource",typeid_datasource);
        List<Object> cyid_datasource=cystyleSrv.getEntity("");
        request.setAttribute("cyid_datasource",cyid_datasource);
        List<Object> tagid_datasource=tagsSrv.getEntity("");
        request.setAttribute("tagid_datasource",tagid_datasource);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/xianluadd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void get() {
		String filter = "where 1=1 ";
		String chufadi = request.getParameter("chufadi");
		if (chufadi != null)
			filter += "  and chufadi like '%" + chufadi + "%'  ";
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
		List<Xianlu> listxianlu = xianluSrv.getPageEntitys( filter,
				pageindex, pagesize);
		int recordscount = xianluSrv.getRecordCount(filter == null ? ""
				: filter);
		request.setAttribute("listxianlu", listxianlu);
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
			forwardurl = "/admin/xianlumanager.jsp";
		}
		forward(forwardurl);
	}
}
