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
			<h5>
				<small> <span class="text-primary">作者：</span>${post_user_tag.user.email }
				</small>
			</h5>
			<h5 class="date">
				<small> <span class="text-primary">日時：</span> <fmt:formatDate
						value="${post_user_tag.post.createAt }"
						pattern="yyyy年MM月dd日（E） a KK時mm分" />
				</small>
			</h5>
			<br>
			<p>${post_user_tag.post.content }</p>
			<br> <br>
			<p>
				<span class="text-primary">タグ：</span>
				<c:forEach var="tag" items="${post_user_tag.tags }">
					<a href="#"><i class="glyphicon glyphicon-tag"></i>&nbsp;${tag.tagName }</a>
				</c:forEach>
			</p>
			<hr>
			<h3 class="text-primary">コメント(${num_of_comments })</h3>
			<div>
				<form method="post">
					<div class="form-group">
						<textarea class="form-control" rows="3" name="comment"
							placeholder="コメント"></textarea>
					</div>
					<button type="submit" class="btn btn-primary pull-right">コメント</button>
				</form>
			</div>
			<br> <br> <br>
			<div>
				<c:forEach var="userComment" items="${user_comment }">
					<div class="panel panel-default">
						<div class="panel-body">
							<p class="text-primary">${userComment.user.email }:</p>
							<p class="date">
								<small class=""> <fmt:formatDate
										value="${userComment.comment.createAt }"
										pattern="yyyy年MM月dd日（E） a KK時mm分" />
								</small>
							</p>
							${userComment.comment.content }
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>