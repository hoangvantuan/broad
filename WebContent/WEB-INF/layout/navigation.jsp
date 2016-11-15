<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/broad/">電子掲示板</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<c:if test="${sessionScope.user != null }">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath }/post/add"
						class="add-post"><i class="glyphicon glyphicon-plus"></i></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">${sessionScope.user.email }<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/broad/user/profile"><i
									class="glyphicon glyphicon-user"></i>&nbsp;個人情報</a></li>
							<li><a href="/broad/user/posts"><i
									class="glyphicon glyphicon-list"></i>&nbsp;マイ投稿</a></li>
							<li><a href="/broad/user/users"><i
									class="glyphicon glyphicon-list-alt"></i>&nbsp;一覧ユーザー</a></li>
							<li><a href="/broad/logout"><i
									class="glyphicon glyphicon-off"></i>&nbsp;ログアウト</a></li>
						</ul></li>
				</ul>
			</c:if>
			<c:if test="${sessionScope.user == null }">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/login">ログイン</a></li>
					<li><a href="${pageContext.request.contextPath}/register">登録</a></li>
				</ul>
			</c:if>
		</div>
	</div>
	<!-- /.navbar-collapse -->
	<!-- /.container-fluid -->
</nav>