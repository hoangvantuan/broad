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
      <span class="sr-only">Toggle navigation</span>
      <span
        class="icon-bar"></span> <span class="icon-bar"></span> <span
        class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">電子掲示板</a>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle"
            data-toggle="dropdown" role="button" aria-haspopup="true"
            aria-expanded="false">${session.user.user_name }<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href='<s:url value="#"></s:url>'><i
              class="glyphicon glyphicon-user"></i>&nbsp;Profile</a></li>
            <li><a href="#"><i class="glyphicon glyphicon-off"></i>&nbsp;Sign
              out</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>