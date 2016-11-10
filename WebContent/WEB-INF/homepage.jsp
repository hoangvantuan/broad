<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- include header -->
<jsp:include page="layout/header.jsp"></jsp:include>
<div class="container">
	<div class="text-center text-success">${message }</div>
	<div class="text-center text-danger">${error }</div>
	<c:forEach var="data" items="${datas }">
		<div class="col-md-4">
			<div class="panel panel-info post-body">
				<div class="panel-heading">
					<h4 class="panel-title">${data.post.postName }</h4>
				</div>
				<div class="panel-body">${data.post.content }...<a
						href="${pageContext.request.contextPath}/post/details?post_id=
						${data.post.postId }">続きを読む</a>
				</div>
				<div class="panel-footer">
					<p>
						<small><span class="text-primary">作者: </span>${data.user.email }</small></p>
					<c:forEach var="tag" items="${data.tags }">
						<small><a href="#"><i class="glyphicon glyphicon-tag"></i>&nbsp;${tag.tagName }</a></small>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:forEach>
	<c:if test="${sessionScope.user == null }">
		<div class="text-center text-primary">
			<h1>こんにちは！</h1>
		</div>
	</c:if>
</div>
<!-- include footer -->
<jsp:include page="layout/footer.jsp"></jsp:include>