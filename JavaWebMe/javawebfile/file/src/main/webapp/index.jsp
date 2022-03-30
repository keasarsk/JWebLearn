<%--index.jsp--%>
<%--<%@page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>$upload-file$</title>
</head>
<body>

<%--index.jsp--%>
<%--通过白哦但上传文件
  get：上传文件大小有限制
  post：无限制
  --%>
<form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
    上传用户：<input type="text" name="username"><br/>
    上传文件1：<input type="file" name="file1"><br/>
    上传文件2：<input type="file" name="file2"><br/>
    <p><input type="submit" value="提交"> | <input type="reset" name="重置"></p>
</form>

</body>
</html>
