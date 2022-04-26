package com.dorm.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PageTag extends TagSupport{

	private Integer totalNum;
	private Integer pageSize;
	private Integer pageIndex;
	private String submitUrl;
	
	
	@Override
	public int doEndTag() throws JspException {
		
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		
		JspWriter writer = pageContext.getOut();
//		System.out.println(totalNum+"    "+pageSize+"    "+pageIndex+"    "+submitUrl);
		
		try {
			
			StringBuffer page = new StringBuffer();
			Integer totalPage = totalNum % pageSize == 0 ?  totalNum / pageSize : totalNum / pageSize+1;
//			System.out.println("totalPage: "+totalPage);
			
			if(totalNum > pageSize) {
				//只有当查询出来的数据量大于每一页展示的数据量时才进行分页
				if(pageIndex == 1) {
					//当前页码是首页
					page.append("<a href='#'>首页</a>&nbsp;&nbsp;");
					page.append("<a href='#'>上一页</a>&nbsp;&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageIndex+1)+"'>下一页</a>&nbsp;&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+totalPage+"'>尾页</a>");
					
				}else if(pageIndex == totalPage) {
					//当前页码是尾页
					page.append("<a href='"+submitUrl+"&pageIndex="+1+"'>首页</a>&nbsp;&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageIndex-1)+"'>上一页</a>&nbsp;&nbsp;");
					page.append("<a href='#'>下一页</a>&nbsp;&nbsp;");
					page.append("<a href='#'>尾	页</a>");

				}else {
					//当前页码位于中间
					page.append("<a href='"+submitUrl+"&pageIndex="+1+"'>首页</a>&nbsp;&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageIndex-1)+"'>上一页</a>&nbsp;&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+(pageIndex+1)+"'>下一页</a>&nbsp;&nbsp;");
					page.append("<a href='"+submitUrl+"&pageIndex="+totalPage+"'>尾页</a>");
					
				}
				page.append("<br> 当前是第 "+pageIndex+" 页&nbsp;&nbsp;/共"+totalPage+"页 ，&nbsp;&nbsp;共 "+totalNum+" 条数据");
			}
			
			writer.print(page.toString());
			writer.flush();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
			
		return super.doStartTag();
	}

	
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	
}
