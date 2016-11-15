<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<jsp:include page="../layout/search_user.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<div class="row">
		<h3 class="text-center text-primary">一覧ユーザー</h3>
		<hr>
		<div class="col-md-10 col-md-offset-1">
			<div class="text-center text-danger">${error }</div>
			<div class="text-center text-primary">${message }</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<td>役割</td>
						<td>ユーザー名</td>
						<td>メール</td>
						<td>投稿数量</td>
						<td>コメント数量</td>
						<td>参加日付</td>
						<td>情報閲覧</td>
						<c:if test="${sessionScope.user.isRole == true }">
							<td>削除</td>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${user_post_comments }">
						<tr>
							<td><c:if test="${data.user.isRole == true }">
									<span class="text-primary">管理者</span>
								</c:if> <c:if test="${data.user.isRole == false }">
									<span class="text-info">一般</span>
								</c:if></td>

							<td>${data.user.userName }</td>
							<td>${data.user.email }</td>
							<td class="text-center">${data.posts.size() }</td>
							<td class="text-center">${data.comments.size() }</td>
							<td><fmt:formatDate value="${data.user.createAt }"
									pattern="MMM dd, yyyy" /></td>
							<td><a
								href="${pageContext.request.contextPath }/user/profile?user_id=${data.user.userId }"
								class="btn btn-primary btn-block">閲覧</a></td>
							<c:if test="${sessionScope.user.isRole == true }">
								<td><a
									href="${pageContext.request.contextPath }/user/delete?user_id=${data.user.userId}"
									class="text-danger delete"><i
										class="glyphicon glyphicon-trash"></i></a></td>
							</c:if>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>