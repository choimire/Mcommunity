<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/summernote-bs4.min.css" />
<link rel="stylesheet" href="/mcommunity/res/css/skin/board/style.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/summernote-bs4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/lang/summernote-ko-KR.min.js"></script>
<script>
$(function(){
	
	let upload = ${badmin.upload == 1 ? 'true' : 'false'};
	let uploadTag = upload ? ['insert', ['link','picture','video']]:['insert', ['link','video']];
	
	
	$("#content").summernote({
		height:300,
		lang: 'ko-KR',
		toolbar: [
	          ['style', ['style']],
	          ['font', ['bold', 'underline', 'clear']],
	          ['color', ['color']],
	          ['para', ['ul', 'ol', 'paragraph']],
	          ['table', ['table']],
	          uploadTag,
	          ['view', ['fullscreen', 'codeview', 'help']]
	        ],
	        callbacks:{
	        	onImageUpload : function(files){
	        		uploadFile(files[0],true);
	        	},
				onMediaDelete : function(target) {
			        const imageUrl = target[0].src;
			        console.log(imageUrl);
			        deleteFile(imageUrl);
			     }
	        }
	});


		function uploadFile(file, insertIntoEditor){
			const formData = new FormData();
			const bbsid =$("#bbsid").val();
			const tempBid = $("#tempBid").val();
			console.log(tempBid);
			const addFileSize = $("#addFileSize").val();
			formData.append('bbsid' ,bbsid);
			formData.append('tempBid' ,tempBid);
			formData.append('addFileSize', addFileSize);
			formData.append('files' ,file);
			
			$.ajax({
				url:"/mcommunity/file/upload",
				type:"POST",
				data: formData,
				processData: false,
				contentType: false,
				enctype: 'multipart/form-data',
				beforeSend: function(xhr){
					xhr.setRequestHeader("${_csrf.headerName}","${_csrf.token}");
				},
				success: function(data){
					const imgUrl = "/mcommunity/res/upload/" +bbsid+"/"+data[0].nfilename;
					console.log(data[0])
					console.table(data);
					//summernote에 insertIntoEditor 조건이 true  이면 이미지를 보여줌
					if(insertIntoEditor){
						$("#content").summernote('insertImage', imgUrl);
					}
				},
				error: function(error){
					alert("문제가 발생하였습니다.");
				}
			})
		}
		
		function deleteFile(imageUrl) {
		      alert("떠야");
		      /* 파일명 추출 */
		      const fileName = imageUrl.split("/").pop();
		      const bbsid = $("#bbsid").val();
		      $.ajax({
		         url: "/mcommunity/file/delete"
		         , type: 'POST'
		         , data: {fileName, bbsid}
		         , beforeSend: function(xhr){
		            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
		         }
		         , success: function(data) {
		            if(data == 1) {
		               console.log("삭제완료");
		            } else {
		               console.log("삭제실패");
		            }
		         }
		         , error: function(error) {
		            alert("삭제중 오류 발생");
		         }
		      });
		      
		   }
});
		
		</script>
		<c:set var="tempBid" value="<%=System.currentTimeMillis() %>"/>
		<c:if test="${boardAdmin.rgrade > 0 }">
		   <sec:authorize access="!isAuthenticated()">
		      <script>
		         <alert("회원전용 게시판입니다. 로그인하세요.");
		         location.href = "/mcommunity";
		      </script>
		   </sec:authorize>
		   <c:if test="${boardAdmin.RGrade > user.grade}">
		      <script>
		         alert("이 게시판을 볼 권한이 없습니다.");
		         location.href = "/mcommunity";
		      </script>
		   </c:if>
		</c:if>
		
		<div class="container py-5">
		        <form name="writeform" class="writeform" action="write" method="post" onsubmit="return validateForm();">
		            <div class="row">
		                <label class="col-1 mb-3">작성자</label>
		                <input type="text" name="writer" class="col-3 form-control mb-3" placeholder="작성자" value="${ user.username}"/>
		                <c:choose>
		                <c:when test="${user.userid == null || user.userid.isEmpty() }">
		                <label class="col-1 offset-1 mb-3 p-0">비밀번호</label>
		                <input type="password" name="pass" class="col-3 form-control mb-3" placeholder="비밀번호" />
		                </c:when>
		                <c:otherwise>
		                	<div class="col-5"></div>
		                </c:otherwise>
		                </c:choose>
		              
		               	
		                <div class="col-3 mb-3"></div>
		                
		                <!-- 카테고리 -->
		                <c:if test="${badmin.category==1 }">
		                	<label class="col-1 mb-3 p-0">카테고리</label>
		                	<select name="category" class="col-3 form-control mb-3">
		                	<c:forEach var="category" items="${categories }">
		                	<option value="${ category.categoryText}">${ category.categoryText}</option>
		                	</c:forEach>
		                	</select>
		                	<div class="col-8"></div>
		                </c:if>
		                <input type="hidden" name="userid" value="guest">
		                <label class="col-1 mb-3">제목</label>
		                <input type="text" name="title" class="form-control col-11 mb-3" placeholder="제목" />
		
		                <label class="col-1 mb-3">내용</label>
		                <div class="col-11 mb-3 p-0">
		                   <textarea name="content" id="content"></textarea> 
		                </div>
						
						<c:if test="${badmin.sec ==1 }">
						<div class="col-12 text-right">
							<label><input type="checkbox" name="sec" value="1"/>비밀글${user.userid }</label>
						</div>
						</c:if>
		            </div>
		             <c:if test="${user.userid==null || user.userid.isEmpty() }">
		            <div class="my-4 d-flex justify-content-end">
		           <div class="g-recaptcha mx-auto" data-sitekey="6LcxGQcrAAAAAJwat3u52R03uXiieHd0M6pdYkW3"></div>
		           </div>
		            </c:if> 
		            <div class="text-center my-4">
		                <button class="btn btn-danger" type="reset">취소</button>
		                <button class="btn btn-dark" type="submit">전송</button>
		            </div>
		            <c:choose>
		            	<c:when test="${user.userid == null || user.userid.isEmpty() }">
		            		<input type="hidden" name="userid" value="guest">
		            	</c:when>
		            	<c:otherwise>
		            		<input type="hidden" name="userid" value="${user.userid }">
		            	
		            	</c:otherwise>
		            </c:choose>
		           <input type="hidden" name="bbsid" id="bbsid" value="${param.bid }">
		           <input type="hidden" name="tempBid" id="tempBid" value="${tempBid } ">
		           <input type="hidden" name="addFileSize" id="addFileSize" value="0"/>
		           <sec:csrfInput/>
		        </form>
		        <script>
		            function validateForm() {
		                //제목, 내용
		                const title = document.writeform.title.value.trim();
		                const content = document.writeform.content.value.trim();
		                const writer = document.writeform.writer.value.trim();
		                const pass = document.writeform.pass.value.trim();
		
		                if (writer === "") {
		                    alert("이름을 입력 하세요.");
		                    document.writeform.writer.focus();
		                    return false;
		                }
		                if (pass === "") {
		                    alert("비밀번호를 입력 하세요.");
		                    document.writeform.pass.focus();
		                    return false;
		                }
		                if (title === "") {
		                    alert("제목을 입력하세요.");
		                    document.writeform.title.focus();
		                    return false;
		                }
		                if (content === "") {
		                    alert("내용을 입력하세요.");
		                    document.writeform.content.focus();
		                    return false;
		                }
		                const captcah = grecaptcha.getResponse();
		                if(!captcha){
		                	
		                }
		
		                return true;
		            }
		        </script>
		    </div>
		    <script src="https://www.google.com/recaptcha/api.js" async defer></script>