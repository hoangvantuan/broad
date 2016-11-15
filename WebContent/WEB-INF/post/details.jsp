<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<h3 class="text-center text-primary">${post_user_tag.post.postName }</h3>
	<hr>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="text-center text-danger">${error }</div>
			<div class="text-center text-primary">${message }</div>
			<h5>
				<small> <span class="text-primary">作者：</span>${post_user_tag.user.email }
				</small>
			</h5>
			<h5 class="date">
				<small> <span class="text-primary">日時：</span> <fmt:formatDate
						value="${post_user_tag.post.createAt }"
						pattern="MMM dd, yyyy KK:mm:ss a" />
				</small>
			</h5>
			<br>
			<p>${post_user_tag.post.content }</p>
			<br> <br>
			<p>
				<span class="text-primary">タグ：</span>
				<c:forEach var="tag" items="${post_user_tag.tags }">
					<a
						href="${pageContext.request.contextPath }/tag/search?tag_id=${tag.tagId }"><i
						class="glyphicon glyphicon-tag"></i>&nbsp;${tag.tagName }</a>
				</c:forEach>
			</p>
			<hr>
			<h3 class="text-primary">
				コメント(<span class="num_of_comment">${num_of_comments }</span>)
			</h3>
			<div>
				<form method="post">
					<div class="form-group">
						<textarea class="form-control comment-content" rows="3"
							name="content" placeholder="コメント"></textarea>
					</div>
					<input type="hidden" value="${post_user_tag.post.postId }"
						name="post_id" />
					<button type="submit"
						class="btn btn-primary pull-right add-comment">コメント</button>
				</form>
			</div>
			<br> <br> <br>
			<div>
				<div class="list_comment"></div>
				<c:forEach var="userComment" items="${user_comment }">
					<div class="row ${userComment.comment.commentId }">
						<div class="col-sm-1">
							<div class="thumbnail">
								<img class="img-responsive user-photo"
									src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
							</div>
						</div>
						<div class="col-sm-11">
							<div class="panel comment panel-info">
								<div class="panel-heading">
									<strong>${userComment.user.email }</strong><span
										class="text-muted">コメント <span class="date"> <small
											class=""> <fmt:formatDate
													value="${userComment.comment.createAt }"
													pattern="MMM dd, yyyy KK:mm:ss a"></fmt:formatDate>
										</small> <c:if
												test="${sessionScope.user.isRole == true or userComment.user.userId == sessionScope.user.userId or sessionScope.user.userId == post_user_tag.user.userId}">
												<a href="javascript:void(0);" class="pull-right"
													onClick="deleteComment(${userComment.comment.commentId } );return false;"><i
													class="glyphicon glyphicon-remove"></i></a>
											</c:if>
									</span>
									</span>
								</div>
								<div class="panel-body">${userComment.comment.content }</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>