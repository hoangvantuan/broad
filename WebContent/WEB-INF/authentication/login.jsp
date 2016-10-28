<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- Form signin -->
<div class="container">
	<div class="row">
		<h3 class="text-center">登録</h3>
		<div class="col-md-8 col-md-offset-2">
			<form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/login">
				<div class="form-group">
					<label for="email" class="col-md-3 control-label">メール<span class="text-danger">*</span></label>
					<div class="col-md-9">
						<input type="text" class="form-control" id="email" name="email"
							placeholder="メール" required="required" autocomplete="on"
							autofocus="autofocus" pattern=".{6,30}">
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-3 control-label">パスワード<span class="text-danger">*</span></label>
					<div class="col-md-9">
						<input type="password" class="form-control" id="password"
							name="password" placeholder="パスワード" required="required"
							pattern=".{6,16}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-md-9">
						<button type="submit" class="btn btn-primary btn-block">登録</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- END Form -->
<jsp:include page="../layout/footer.jsp"></jsp:include>