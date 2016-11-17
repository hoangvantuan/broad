<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
	<div class="pull-right">
		<form class="form-inline" method="get"
			action="${pageContext.request.contextPath }/post/search">
			<div class="form-group">
				<div class="input-group">
					<input type="text" class="form-control" id="search"
						placeholder="検索" name="key_word">
				</div>
			</div>
			<button type="submit" class="btn btn-default">検索</button>
		</form>
	</div>
</div>
<br>