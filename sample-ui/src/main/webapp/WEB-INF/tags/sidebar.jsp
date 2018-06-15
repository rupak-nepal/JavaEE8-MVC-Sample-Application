<div id="mySidenav" class="sidenav" style="width:225px">
    <div id="accordion" style="cursor: pointer;">
        <div>
            <div id="headingOne">
                <span class="mb-0 header-menu">
                    <a class="${pageHelper.isActiveGroupResource(fn:split('home', ',')) ? 'header-color-active':''}"" href="${mvc.basePath}/home">
                        <i class="fa fa-home"></i>
                        <span class="sdnam">Home</span>
                    </a>
                </span>
            </div>
        </div>
        <!--User Management-->
        <div>
            <div id="headingTwo" class="">
                <span class="mb-0  header-menu">
                    <a class="collapsed ${pageHelper.isActiveGroupResource(fn:split('users', ',')) ? 'header-color-active':''}" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false"
                       aria-controls="collapseTwo" href="#">
                        <i class="fa fa-user"></i> 
                        <span class="sdnam">User </span>
                    </a>
                </span>
            </div>
            <div id="collapseTwo" class="collapse submenu-border ${pageHelper.isActiveGroupResource(fn:split('users', ',')) ? 'show':''}" aria-labelledby="headingTwo" data-parent="#accordion">
                <div class="submenu ${pageHelper.isActiveResource(fn:split('users/new', ',')) ? 'header-menu-active':''}">
                    <a class="" href="${mvc.basePath}/users/new">
                        <i class="fa fa-plus"></i>
                        <span class="subnam">New</span>
                    </a>
                </div>
                <div class="submenu ${pageHelper.isActiveResource(fn:split('users', ',')) ? 'header-menu-active':''}"">
                    <a class="" href="${mvc.basePath}/users">
                        <i class="fa fa-search"></i>
                        <span class="subnam">List</span>
                    </a>
                </div>
            </div>
        </div>
        </div>

    </div>
</div>
<script>

    function nav() {

        navSize = document.getElementById("mySidenav").style.width;
        console.log(navSize);
        if (navSize === '0px') {
            return openNav();
        }
        return closeNav();

    }

    function openNav() {
        document.getElementById("mySidenav").style.width = "225px";
        //  document.getElementById("main").style.marginLeft = "250px";
        document.getElementById("container").style.marginLeft = "240px";
        document.getElementById("mySidenav").style.display = "block";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("mySidenav").style.transition = "0.2s";
        document.getElementById("mySidenav").style.display = "none";
        //   document.getElementById("main").style.marginLeft = "0";
        document.getElementById("container").style.marginLeft = "18px";
    }
</script>
