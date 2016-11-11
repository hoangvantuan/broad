<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<div class="row">
		<h3 class="text-center text-primary">ユーザ情報編集</h3>
		<hr>
	</div>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
		<div class="text-center text-danger">${error }</div>
		<div class="text-center text-primary">${message }</div>
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/edit">
				<div class="control-group">
					<label class="control-label" for="username">ユーザ名</label>
					<div class="controls">
						<input id="username" name="user_name" value="${user.userName }"
							type="text" placeholder="" class="form-control">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="password">パスワード</label>
					<div class="controls">
						<input id="password" name="password" type="password"
							placeholder="" value="${user.password }" class="form-control" required="required">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="confirmpassword">確認パスワード</label>
					<div class="controls">
						<input id="confirmpassword" name="confirmpassword" type="password"
							placeholder="" value=${user.password } class="form-control" required="required">

					</div>
				</div>
				<br>
				<div class="control-group">
					<input type="submit" value="更新" class="btn btn-primary btn-block form-control"/>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>