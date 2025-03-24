<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark py-md-3">
		<!-- logo -->
		<a href="/mcommunity" class="navbar-brand ml-md-5">Mcommunity</a>
		<!-- mobile button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu">
		<i class="ri-menu-line"></i>
		</button>
		<div class="collapse navbar-collapse justify-content-between ml-md-5" id="menu">
			<ul class="navbar-nav">
				<li class="nav-item ml-md-3">
					<a class="nav-link" href="#">공지사항</a>
				</li>
				<li class="nav-item ml-md-3">
					<a class="nav-link" href="#">인기글</a>
				</li>
			</ul>
			<form class="d-flex mr-md-5">
				<input class="form-control mr-2 col-8" type="search" placeholder="search" aria-label="Search">
				<button class="btn btn-outline-success col-4" type="submit">검색</button>
			</form>
		</div>
	</nav>
</header>