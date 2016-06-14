<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="sp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<br>

<div class="row">
	<div class="span12">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Title</th>
					<th>Artist</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="album"
					varStatus="loopStatus">
					<tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
						<td><c:out value="${album.title}" /></td>
						<td><c:out value="${album.artist.name}" /></td>
					</tr>
				</c:forEach>
				<tr>
			</tbody>
		</table>
	</div>
	<util:pagination page="${page}"></util:pagination>
</div>



