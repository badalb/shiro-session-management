<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.apache.shiro.session.mgt.OnlineSession" %>
<%@ page import="com.test.shiro.example.session.dao.MySessionDAO" %>
<%@ page import="java.io.Serializable" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
<shiro:guest>
    Login<a href="${pageContext.request.contextPath}/login.jsp">here</a><br/>
</shiro:guest>
<shiro:user>
   hello [<shiro:principal/>]to logout，<a href="${pageContext.request.contextPath}/logout">here</a><br/>
</shiro:user>

<shiro:user>
    <%
        SecurityUtils.getSubject().getSession().setAttribute("key", 123);
        out.print(SecurityUtils.getSubject().getSession().getAttribute("key"));
    %>
    <br/>
    <%
        MySessionDAO sessionDAO = new MySessionDAO();
        Serializable sessionId = SecurityUtils.getSubject().getSession().getId();
        OnlineSession onlineSession = (OnlineSession)sessionDAO.readSession(sessionId);
        out.print(onlineSession.getStatus().getInfo());
    %>
</shiro:user>

</body>
</html>
