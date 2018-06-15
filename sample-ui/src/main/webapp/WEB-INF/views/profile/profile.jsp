<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-sm-6" style="display:inline-block" >
                <form action="${pageContext.request.contextPath}/app/profile" method="post">
                    <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
                    <h6 >Profile Update</h6>
                    <label>Full Name</label><br>
                    <input class="uname" type="text" id="name" name="name" placeholder="name" value="${user.name}"><br>

                    <label>User Name</label><br>
                    <input class="uid" type="text" id="userId" name="userId" readonly="true" placeholder="username" value="${user.userId}"><br>
                    <button class="profile-btn" type="submit">Update profile</button>
                </form>
            </div>

            <div class="col-sm-6" style="display:inline-block">
                <form action="${pageContext.request.contextPath}/app/profile/change-password" method="post">
                    <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
                    <input type="hidden" id="fullName" name="fullName" value="${user.name}"/>
                    <input type="hidden" id="userId" name="userId" value="${user.userId}"/>
                    <h6>Password Update</h6>
                    <br>
                    <input class="cpass" type="password" id="currentPass" name="currentPass"
                           placeholder="Current password *" ><br>
                    <br>
                    <input class="newpass" type="password" id="password" name="password"
                           placeholder="New password *" ><br>
                    <br>
                    <input class="conpass" type="password" id="confirmPassword" name="confirmPassword"
                           placeholder="Confirm new password" ><br>
                    <button class="password-btn" type="submit">Update password</button>
                </form>
            </div>
        </div>


        <div class="col-sm-1" style="display:inline-block">
            <form action="${pageContext.request.contextPath}/app/profile/reset-password" method="post">
                <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
                <button class="profile-btn" type="submit">Reset Password</button>
            </form>
        </div>




    </jsp:body>
</t:template>
