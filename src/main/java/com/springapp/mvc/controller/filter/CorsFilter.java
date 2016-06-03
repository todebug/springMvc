/*
 * 类名 CORSFilter.java
 *
 * 版本信息 
 *
 * 日期 2015-8-25
 */
/**
 * Class description goes here.
 *
 * @version  
 * @author  dongyang
 * @date  2015-8-25 下午03:24:40
 *
 */
package com.springapp.mvc.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class description goes here.
 *
 * @version  
 * @author  dongyang
 * @date  2015-8-25 下午03:24:40
 *
 */

/**
 * @author dongyang
 *
 */
public class CorsFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}
}

