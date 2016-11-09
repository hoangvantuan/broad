<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<h3 class="text-center text-primary">${post_user_tag_comment.post.postName }</h3>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<p>
				<span class="text-primary">作者：</span>${post_user_tag_comment.user.email }
			</p>
			<p>
				<span class= "text-primary">日時：</span><fmt:formatDate value="${post_user_tag_comment.post.createAt }" pattern="yyyy年MM月dd日（E） a KK時mm分ss秒" />
			</p>
			<br>
			<p class = "text-center">${post_user_tag_comment.post.content }</p>
			<br><br>
			<p>
			<span class="text-primary">タグ：</span>
			<c:forEach var = "tag" items = "${post_user_tag_comment.tags }">
				<a href="#"><i class="glyphicon glyphicon-tag"></i>&nbsp;${tag.tagName }</a>
			</c:forEach>
			</p>

		</div>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>