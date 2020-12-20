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
 * 线路预订控制
 *
 */
@Controller
public class XlyudingController extends SimpleController {
	@Autowired
	private XlyudingService xlyudingSrv = null;
	@Autowired
	private HuiyuanService huiyuanSrv=null;

	@Override
	@RequestMapping("/admin/xlyudingmanager.do")
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
		xlyudingSrv.delete(SQL);
	}
	private void payfor() {

		String ddid = request.getParameter("xlydid");

		String accountname = request.getParameter("accountname");
		String paypwd = request.getParameter("paypwd");
		String errorurl = request.getParameter("errorurl");

		if (ddid == null || accountname == null)
			return;

		Huiyuan hy = huiyuanSrv.load("where accountname='"
				+ accountname + "'");
		if ((paypwd != null && !paypwd.equals(hy.getPaypwd()))
				|| paypwd == null) {
			request.setAttribute("errormsg",
					"<label class='error'>支付密码不正确请重新输入</label>");
			forward(errorurl);
			return;
		}

		Xlyuding dingdan = xlyudingSrv.load("where id="
				+ ddid);

		if (hy.getYue() < dingdan.getJine()) {

			request.setAttribute("errormsg",
					"<label class='error'>账户余额不足于支付订单,请充值</label>");
			forward(errorurl);

		} else {

			hy.setYue((float) (hy.getYue() - dingdan.getJine()));
			huiyuanSrv.update(hy);
			dingdan.setStatus("已付款");
			xlyudingSrv.update(dingdan);
			request.getSession().setAttribute("huiyuan", hy);
		}

		String forwardurl = request.getParameter("forwardurl");
		redirect(forwardurl);

	}

	

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		// 验证错误url
		String errorurl = request.getParameter("errorurl");
		String xlid = request.getParameter("xlid");
		String xltitle = request.getParameter("xltitle");
		String yddate = request.getParameter("yddate");
		String renshu = request.getParameter("renshu");
		String hyname = request.getParameter("hyname");
		String lianxiren = request.getParameter("lianxiren");
		String mobile = request.getParameter("mobile");
		String jine = request.getParameter("jine");
		String fangjiashu = request.getParameter("fangjiashu");
		String status = request.getParameter("status");
		SimpleDateFormat sdfxlyuding = new SimpleDateFormat("yyyy-MM-dd");
		Xlyuding xlyuding = new Xlyuding();
		xlyuding.setXlid(xlid == null ? 0 : new Integer(xlid));
		xlyuding.setXltitle(xltitle == null ? "" : xltitle);
		if (yddate != null) {
			try {
				xlyuding.setYddate(sdfxlyuding.parse(yddate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			xlyuding.setYddate(new Date());
		}
		xlyuding.setRenshu(renshu == null ? 0 : new Integer(renshu));
		xlyuding.setHyname(hyname == null ? "" : hyname);
		xlyuding.setLianxiren(lianxiren == null ? "" : lianxiren);
		xlyuding.setMobile(mobile == null ? "" : mobile);
		xlyuding.setJine(jine == null ? (double) 0 : new Double(jine));
		xlyuding.setFangjiashu(fangjiashu == null ? 0 : new Integer(fangjiashu));
		xlyuding.setStatus(status == null ? "" : status);
		xlyudingSrv.save(xlyuding);
		
		if (forwardurl == null) {
			forwardurl = "/admin/xlyudingmanager.do?actiontype=get";
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
		Xlyuding xlyuding = xlyudingSrv.load(new Integer(id));
		if (xlyuding == null)
			return;
		String xlid = request.getParameter("xlid");
		String xltitle = request.getParameter("xltitle");
		String yddate = request.getParameter("yddate");
		String renshu = request.getParameter("renshu");
		String hyname = request.getParameter("hyname");
		String lianxiren = request.getParameter("lianxiren");
		String mobile = request.getParameter("mobile");
		String jine = request.getParameter("jine");
		String fangjiashu = request.getParameter("fangjiashu");
		String status = request.getParameter("status");
		SimpleDateFormat sdfxlyuding = new SimpleDateFormat("yyyy-MM-dd");
		xlyuding.setXlid(xlid == null ? 0 : new Integer(xlid));
		xlyuding.setXltitle(xltitle);
		if (yddate != null) {
			try {
				xlyuding.setYddate(sdfxlyuding.parse(yddate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		xlyuding.setRenshu(renshu == null ? 0 : new Integer(renshu));
		xlyuding.setHyname(hyname);
		xlyuding.setLianxiren(lianxiren);
		xlyuding.setMobile(mobile);
		xlyuding.setJine(jine == null ? (double) 0 : new Double(jine));
		xlyuding.setFangjiashu(fangjiashu == null ? 0 : new Integer(fangjiashu));
		xlyuding.setStatus(status);
		xlyudingSrv.update(xlyuding);
		
		if (forwardurl == null) {
			forwardurl = "/admin/xlyudingmanager.do?actiontype=get";
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
			Xlyuding xlyuding = xlyudingSrv.load("where id="+ id);
			if (xlyuding != null) {
				request.setAttribute("xlyuding", xlyuding);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/xlyudingadd.jsp";
		}
		forward(forwardurl);
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void get() {
		String filter = "where 1=1 ";
		String xltitle = request.getParameter("xltitle");
		String accountname = request.getParameter("accountname");
		if (xltitle != null)
			filter += "  and xltitle like '%" + xltitle + "%'  ";
		if (accountname != null)
			filter += "  and hyname='" + accountname + "'";
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
		List<Xlyuding> listxlyuding = xlyudingSrv.getPageEntitys(filter,
				pageindex, pagesize);
		int recordscount = xlyudingSrv.getRecordCount(filter == null ? "" : filter);
		request.setAttribute("listxlyuding", listxlyuding);
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
			forwardurl = "/admin/xlyudingmanager.jsp";
		}
		forward(forwardurl);
	}
}
