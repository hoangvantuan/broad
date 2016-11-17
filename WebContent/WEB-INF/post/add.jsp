<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- include header -->
<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- body -->
<div class="container">
	<h3 class="text-center text-primary">新しい投稿を作成</h3>
	<hr>
	<div class="text-primary text-center">${message }</div>
	<div class="text-danger text-center">${error }</div>
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/post/add">
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">題名</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" placeholder="題名"
					name="post_name" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">内容</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="content" rows="10" name="content"
					required="required"></textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="tags" class="col-sm-2 control-label">タグ</label>
			<div class="col-sm-10">
				<input id="tags" name="tags" class="form-control" type="text"
					value="" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-block">投稿</button>
			</div>
		</div>
	</form>
</div>
<!-- include footer -->
<jsp:include page="../layout/footer.jsp"></jsp:include>