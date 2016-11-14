<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<div class="row">
		<h3 class="text-center text-primary">ユーザ情報</h3>
		<hr>
		<div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
			<div class="text-center text-danger">${error }</div>
			<div class="text-center text-primary">${message }</div>
			<div class="profile">
				<div class="col-sm-12">
					<div class="col-xs-12 col-sm-8">
						<h2>${user.userName }</h2>
						<p>
							<strong>メール: </strong> ${user.email }
						</p>
						<p>
							<strong>デイ参加: </strong>
							<fmt:formatDate value="${user.createAt }" pattern="yyyy年MM月dd日" />
						</p>
						<p>
							<strong>役割: </strong>
							<c:if test="${user.isRole }">
								管理者
							</c:if>
							<c:if test="${user.isRole == false}">
								ユーザー
							</c:if>
						</p>
					</div>
					<div class="col-xs-12 col-sm-4">
						<c:if test="${sessionScope.user.email == user.email }">
							<a href="${pageContext.request.contextPath }/user/edit"
								class="pull-right"><i class="glyphicon glyphicon-edit"></i></a>
						</c:if>
					</div>
				</div>
				<div class="col-xs-12 divider text-center">
					<div class="col-xs-12 col-sm-6 emphasis">
						<h2>
							<strong> ${num_of_post } </strong>
						</h2>
						<button class="btn btn-success btn-block">
							<span class="fa fa-plus-circle"></span> 投稿
						</button>
					</div>
					<div class="col-xs-12 col-sm-6 emphasis">
						<h2>
							<strong>${num_of_comment }</strong>
						</h2>
						<button class="btn btn-info btn-block">
							<span class="fa fa-user"></span> コメント
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>