package com.siwen.imperial.court.filter;

import com.siwen.imperial.court.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @projectName: pro01-all-in-one-demo
 * @package: com.siwen.imperial.court.filter
 * @className: TransactionFilter
 * @author: 749291
 * @description: TODO
 * @date: 4/10/2023 7:58 PM
 * @version: 1.0
 */

@WebFilter(urlPatterns = {"/*"})
public class TransactionFilter implements Filter {
    // 声明集合保存静态资源扩展名
    private static Set<String> staticResourceExtNameSet;

    static {
        staticResourceExtNameSet = new HashSet<>();
        staticResourceExtNameSet.add(".png");
        staticResourceExtNameSet.add(".jpg");
        staticResourceExtNameSet.add(".css");
        staticResourceExtNameSet.add(".js");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 前置操作：排除静态资源
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        if (servletPath.contains(".")) {
            String extName = servletPath.substring(servletPath.lastIndexOf("."));

            if (staticResourceExtNameSet.contains(extName)) {

                // 如果检测到当前请求确实是静态资源，则直接放行，不做事务操作
                filterChain.doFilter(servletRequest, servletResponse);

                // 当前方法立即返回
                return ;
            }

        }
        Connection conn = null;
        try {

            // 1、获取数据库连接
            // 重要：要保证参与事务的多个数据库操作（SQL 语句）使用的是同一个数据库连接
            conn = JDBCUtils.getConnection();

            // 重要操作：关闭自动提交功能
            conn.setAutoCommit(false);

            // 2、核心操作：通过 chain 对象放行当前请求
            // 这样就可以保证当前请求覆盖的 Servlet 方法、Service 方法、Dao 方法都在同一个事务中。
            // 同时各个请求都经过这个 Filter，所以当前事务控制的代码在这里只写一遍就行了，
            // 避免了代码的冗余。
            filterChain.doFilter(servletRequest, servletResponse);

            // 3、核心操作成功结束，可以提交事务
            conn.commit();

        } catch (Exception e) {
            // 4、核心操作抛出异常，必须回滚事务
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            // 页面显示：将这里捕获到的异常发送到指定页面显示
            // 获取异常信息
            String message = e.getMessage();

            // 将异常信息存入请求域
            request.setAttribute("systemMessage", message);

            // 将请求转发到指定页面
            request.getRequestDispatcher("/").forward(request, servletResponse);

        } finally {
            // 5、释放数据库连接
            JDBCUtils.releaseConnection(conn);
        }
    }

    @Override
    public void destroy() {

    }
}
