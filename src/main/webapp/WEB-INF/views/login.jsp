<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
    <form action="./login" id="loginForm" method="post">
    	<table>
    		<tr>
    			<td>id : </td>
    			<td>
    				<input type="text" name="userid" class="form-control" id="userid" placeholder="ID"/>
    			</td>
    		</tr>
    		<tr>
    			<td>password : </td>
    			<td>
    				<input type="password" name="userpass" class="form-control" id="userpass" placeholder="PASSWORD"/>
    			</td>
    		</tr>
    		<tr>
    		<td colspan="2">
    			<div class="text-right">
    				<label><input type="checkbox" name="ok" value="ok">자동로그인</label>
    			</div>
    			<div class="text-center">
    				<sec:csrfInput />
    				<input type="submit" value="LOGIN"/>
    			</div>
    		</td>
    		</tr>
    	</table>	
    	
    </form>