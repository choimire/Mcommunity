<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   <div class="panel panel-default p-5 mr-2">
              <div class="panel-body p-1">
              	<sec:authorize access="isAuthenticated()">
              	<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
              	<h3 class="text-center">${user.username }님</h3>
              	<div class="text-center">
              	<a href="#">mypage</a>
              	</sec:authorize>
              	<sec:authorize access="hasRole('ROLE_ADMIN')">
              		<a href="admin">[관리자모드]</a>
              	</sec:authorize>
              	</div>
              	<p class="text-center"><a href="logout">LOGOUT</a></p>
              	</sec:authorize>
              	
              	<sec:authorize access="!isAuthenticated()">
                <form accept-charset="UTF-8" action="./login" method="post" role="form">
                    <fieldset>
	                     <div class="form-group">
	                       <input class="form-control" placeholder="Id" name="userid" type="text">
	                   </div>
	                   <div class="form-group">
	                      <input class="form-control" placeholder="Password" name="userpass" type="password" value="">
	                   </div>
	                   <div class="checkbox">
	                       <label>
	                          <input name="remember" type="checkbox" value="ok">자동 로그인
	                       </label>
	                    </div>
	                   <input class="btn btn-lg btn-dark btn-block" type="submit" value="Login">
	                   <sec:csrfInput />
	                </fieldset>
                  </form>
                  </sec:authorize>
             </div>
   </div>
   <ul class="list-group list-group-flush mt-5">
   	<li class="list-group-item d-flex justify-content-between align-items-center">
   	    <a href="#">공지사항</a>
            <span class="badge badge-primary badge-pill">15</span>
   	</li>
   </ul>
   <ul class="list-group list-group-flush mt-5">
   	<li class="list-group-item d-flex justify-content-between align-items-center">
   	    <a href="#">자유게시판</a>
            <span class="badge badge-primary badge-pill">15</span>
   	</li>
   </ul>
   <ul class="list-group list-group-flush mt-5">
   	<li class="list-group-item d-flex justify-content-between align-items-center">
   	    <a href="#">정보게시판</a>
            <span class="badge badge-primary badge-pill">15</span>
   	</li>
   </ul>
   <ul class="list-group list-group-flush mt-5">
   	<li class="list-group-item d-flex justify-content-between align-items-center">
   	    <a href="#">갤러리</a>
            <span class="badge badge-primary badge-pill">15</span>
   	</li>
   </ul>
        