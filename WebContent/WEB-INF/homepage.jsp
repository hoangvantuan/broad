<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="layout/header.jsp"></jsp:include>
<jsp:include page="layout/search_post.jsp"></jsp:include>

<div class="container">
	<div class="text-center text-success">${message }</div>
	<div class="text-center text-danger">${error }</div>
	<c:forEach var="data" items="${post_user_tags }">
		<div class="col-md-4">
			<div class="panel panel-info post-body">
				<div class="panel-heading ">
					<div class="panel-title">${data.post.postName }
						<c:if
							test="${sessionScope.user.isRole == true or sessionScope.user.userId == data.user.userId}">
							<div class="btn-group pull-right">
								<a class="dropdown-toggle" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"><i
									class="glyphicon glyphicon-cog"></i></a>
								<ul class="dropdown-menu">
									<c:if test="${sessionScope.user.userId == data.user.userId }">
										<li><a
											href="${pageContext.request.contextPath }/post/edit?post_id=${data.post.postId }"
											class="text-primary">編集</a></li>
									</c:if>
									<li class="text-danger"><a
										href="${pageContext.request.contextPath }/post/delete?post_id=${data.post.postId }&user_id=${user_id }"
										class="delete text-danger">削除</a></li>
								</ul>
							</div>
						</c:if>
					</div>
				</div>
				<div class="panel-body">${data.post.content }...<a
						href="${pageContext.request.contextPath}/post/details?post_id=
						${data.post.postId }">続きを読む</a>
				</div>
				<div class="panel-footer">
					<h5>
						<small><span class="text-primary">作者: </span>${data.user.email }</small>
					</h5>
					<h5 class="date">
						<small> <span class="text-primary">作成日：</span> <fmt:formatDate
								value="${data.post.updateAt }" pattern="M dd, yyyy KK:mm" />
						</small>
					</h5>
					<c:forEach var="tag" items="${data.tags }">
						<small><a
							href="${pageContext.request.contextPath }/tag/search?tag_id=${tag.tagId }"><i
								class="glyphicon glyphicon-tag"></i>&nbsp;${tag.tagName }</a></small>
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