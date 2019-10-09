<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
      <h2>博主信息</h2>
      <img src="${pageContext.request.contextPath}/static/img/head1.png"/>
      <p><span><strong>博主昵称:</strong>&nbsp;</span><span id="nickName">${blogger.nickName}</span></p>
      <p><span><strong>个性签名:</strong>&nbsp;</span><span id="sign">${blogger.sign}</span></p>
      <p><span><strong>个人简介:</strong></span></p>
      <p id="profile">${blogger.profile}</p>
      </div>
    </div>
  </div>