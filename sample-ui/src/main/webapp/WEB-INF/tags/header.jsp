<div id="main">
    <nav class="navbar navbar-expand-lg navbar-dark header-color">
        <a class="navbar-brand" href="#" >Sample </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse nav-toggle" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto ">
                <li><span  onclick="nav()">&#9776 </span></li>
            </ul>
        </div>
        <button type="button" class="btn admin-btn" >
            <span><a type="button" class="btn admin-btn" href="${mvc.contextPath}/app/profile"  >
                                ${current_user.fullName}
                            </a></span>
        </button>
        <button type="button" class="btn logout-btn" data-toggle="modal" data-target="#mymodal">
            <span  ><i class="fa fa-power-off logout"  ></i></span>
        </button>
    </nav>
    <div class="modal fade" id="mymodal"  role="dialog"  >
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">

                    <div class="modal-header"style="background-color:#4CAF50;height: 42px">
                        <h5 style="color:white">Logout!</h5>

                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span style="padding-bottom:5px">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to logout?
                    </div>
                    <div class="modal-footer" style="margin-bottom:-19px">
                        <div style="margin:0px 0px 13px 65px;" >
                            <a type="button" href="${mvc.contextPath}/app/logout"  style="padding:0px 18px 0px 18px;margin-right: 5px;border-radius: .25rem;">
                                Yes
                            </a>
                            <button type="button" class="btnno" data-dismiss="modal" style="padding:0px 18px 0px 18px;margin-left: 8px;border-radius: .25rem; ">No</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    