package com.daowen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daowen.entity.Xianlu;
import com.daowen.entity.Xinxi;
import com.daowen.service.XianluService;
import com.daowen.service.XinxiService;
import com.daowen.ssm.simplecrud.SimpleController;

@Controller
public class SitesearchController extends SimpleController {

	@Autowired
	private XinxiService xinxiSrv=null;
	@Autowired
	private XianluService xianluSrv=null;
	
	@Override
	@RequestMapping("/admin/sitesearch.do")
	public void mapping(HttpServletRequest request, HttpServletResponse response) {
		mappingMethod(request,response);

	}
	
	private void find() {

		String searchtype = request.getParameter("searchtype");
        String  title=request.getParameter("title");
        if(searchtype!=null&&searchtype.equals("1")){
			xianlulist(request, response);
		}
		if(searchtype!=null&&searchtype.equals("2")){
	            List<Xinxi> listXinxi=null;
	            if(title!=null){
	            	listXinxi=xinxiSrv.getEntity("where title like '%"+title+"%'");
	                 request.setAttribute("listXinxi", listXinxi);
	            }
			
			forward("/e/searchnews.jsp");
		}
	}
	public void xianlulist(HttpServletRequest request, HttpServletResponse response) {
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
		List<Xianlu> listXianlu = xianluSrv.getEntity(filter);
			
		request.setAttribute("listXianlu", listXianlu);
		
		// 分发请求参数
		dispatchParams(request, response);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/e/searchxianlu.jsp";
		}
		forward(forwardurl);
	}

	

	

}
