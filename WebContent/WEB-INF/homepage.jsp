<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- include header -->
<jsp:include page="layout/header.jsp"></jsp:include>
<div class="container">
	<c:forEach var="data" items="${datas }">
		<div class="col-md-4">
			<div class="panel panel-info post-body">
				<div class="panel-heading">
					<h4 class="panel-title">${data.post.postName }<a href="#"><i
							class="glyphicon glyphicon-remove pull-right"></i></a>
					</h4>
				</div>
				<div class="panel-body">${data.post.content }...<a href="#">続きを読む</a>
				</div>
				<div class="panel-footer">
					<p>
						<i class="glyphicon glyphicon-user">&nbsp;</i>${data.user.email }</p>
					<c:forEach var="tag" items="${data.tags }">
						<small><a href="#"><i class="glyphicon glyphicon-tag"></i>&nbsp;${tag.tagName }</a></small>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<!-- include footer -->
<jsp:include page="layout/footer.jsp"></jsp:include>