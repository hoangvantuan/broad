<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- body -->
<div class="container">
	<div class="row">
		<h3 class="text-center">ログイン</h3>
		<div class="text-danger text-center">${error}</div>
		<div class="col-md-7 col-md-offset-2">
			<form class="form-horizontal" method="POST"
				action="${pageContext.request.contextPath}/login">
				<div class="form-group">
					<label for="email" class="col-md-3 control-label">メール<span
						class="text-danger">*</span></label>
					<div class="col-md-9">
						<input type="email" class="form-control" id="email" name="email"
							placeholder="メール" value="${user.email }" required="required"
							autocomplete="on" autofocus="autofocus" pattern=".{6,30}">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-3 control-label">パスワード<span
						class="text-danger">*</span></label>
					<div class="col-md-9">
						<input type="password" value="${user.password }"
							class="form-control" id="password" name="password"
							placeholder="パスワード" required="required" pattern=".{6,16}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-md-9">
						<button type="submit" class="btn btn-primary btn-block">ログイン</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>