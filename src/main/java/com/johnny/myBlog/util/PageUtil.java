package com.johnny.myBlog.util;
/**
 * 翻页工具类
 * @author johnny
 *
 */
public class PageUtil {
	//BootStrap4分页样式
	//<li class="page-item"><a class="page-link" href="#">Previous</a></li>
	//<li class="page-item"><a class="page-link" href="#">1</a></li>
	//<li class="page-item active"><a class="page-link" href="#">2</a></li>
	//<li class="page-item"><a class="page-link" href="#">3</a></li>
	//<li class="page-item"><a class="page-link" href="#">Next</a></li>
	/**
	 * 翻页方法
	 */
	public static String getPagination(
			String targetUrl,
			long totalNum,
			int pageSize,
			StringBuffer param,
			int currentPage
			) {
		//总页数
		if(totalNum==0) {
			return "未查询到任何数据";
		}
		long totalPage = 1;
		if(totalNum%pageSize==0) {
			totalPage = totalNum/pageSize;
		}else {
			totalPage = totalNum/pageSize+1L;
		}
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li class='page-item'><a class='page-link' href='"+targetUrl+"?page=1&"+param+"'>首页</a></li>");
		//判断当前页不是第一页,则上一页可以点击
		if(currentPage>1) {
			pageCode.append("<li class='page-item'><a class='page-link' href='"+targetUrl+"?page="+(currentPage-1)+"&"+param+"'>上一页</a></li>");
		}else {
			pageCode.append("<li class='page-item disabled'><a class='page-link' href='#'>上一页</a></li>");
		}
		//显示页数
		for(int i=1;i<=totalPage;i++) {
			if(i==currentPage) {
				pageCode.append("<li class='page-item active'><a class='page-link' href='"+targetUrl+"?page="+i+"&"+param+"'>"+i+"</a></li>");
			}else {
				pageCode.append("<li class='page-item'><a class='page-link' href='"+targetUrl+"?page="+i+"&"+param+"'>"+i+"</a></li>");
			}
		}
		//判断下一页
		if(currentPage<totalPage) {
			pageCode.append("<li class='page-item'><a class='page-link' href='"+targetUrl+"?page="+(currentPage+1)+"&"+param+"'>下一页</a></li>");
		}else {
			pageCode.append("<li class='page-item disabled'><a class='page-link' href='#'>下一页</a></li>");
		}
		//尾页
		pageCode.append("<li class='page-item'><a class='page-link' href='"+targetUrl+"?page="+totalPage+"&"+param+"'>尾页</a></li>");
		return pageCode.toString();
	}
}
