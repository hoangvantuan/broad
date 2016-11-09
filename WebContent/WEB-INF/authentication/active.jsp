<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<div class="col-md-6 col-md-offset-3">
		<h3 class="text-center">アクティブ</h3>
		<div class="text-danger text-center">${error}</div>
		<div class="text-info text-center">${message}</div>
		<p class="text-info">こんにちは ${email }</p>
		<form method="post" action="${pageContext.request.contextPath}/active">
			<input type="hidden" name="email" value="${email }" />
			<div class="form-group">
				<label for="password">パスワード</label> <input type="text"
					class="form-control" name="password" id="password"
					placeholder="パスワード">
			</div>
			<button type="submit" class="btn btn-primary btn-block">確認</button>
		</form>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>