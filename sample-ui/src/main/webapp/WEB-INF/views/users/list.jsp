<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
    <jsp:body>
        <div class="card shadow p-3 mb-5 bg-white rounded">
            <div class="row">
                <div class="col-sm-6"><h4>User List:</h4></div>
            </div>
            <hr>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>S.N.</th>
                        <th>Full Name</th>
                        <th>User ID</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${users}" var="user" varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td>${user.name}</td>
                            <td>${user.userId}</td>
                            <td>
                                <a href="${mvc.basePath}/users/${user.userId}">
                                    Edit
                                </a> ||
                                <a href="${mvc.basePath}/users/${user.userId}/delete">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:template>


