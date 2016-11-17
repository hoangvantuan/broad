<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<div class="col-md-6 col-md-offset-3">
		<h3 class="text-center">パスワードを忘れてしまった</h3>
		<div class="text-danger text-center">${error}</div>
		<div class="text-info text-center">${message}</div>
		<div class="alert alert-info" role="alert">メールにパスワードを送信します</div>
		<form method="post"
			action="${pageContext.request.contextPath}/fogetpassword">
			<div class="form-group">
				<label for="email">メールアドレス</label> <input type="email"
					class="form-control" name="email" id="email" value="${user.email }"
					placeholder="メール">
			</div>
			<button type="submit" class="btn btn-primary btn-block">送信</button>
		</form>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>