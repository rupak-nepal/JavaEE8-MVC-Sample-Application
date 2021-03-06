<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
    <jsp:body>
        <div class="card shadow p-3 mb-5 bg-white rounded">
            <form action="<c:url value=''/>" method="post">
                <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
                <h4>View User:</h4>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Full Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name"
                               placeholder="Enter full name" name="name" value="${user.name}">
                        <c:if test="${error.error}">
                            <span>
                                <label class="alert alert-danger">${error.fields['name']}</label>
                            </span>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="userId" class="col-sm-2 col-form-label">User ID:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="userId"
                               placeholder="Enter User ID" readonly="true" name="userId"  value="${user.userId}">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btnyes">Submit</button>
                <a href="${mvc.basePath}/users" class="btn btn-default">Cancel</a>
            </form>
        </div>
    </jsp:body>
</t:template>