var url = location.search; //获取url中"?"符后的字串，只用来控制显示
var theRequest = new Object();
if (url.indexOf("?") != -1) {
    var str = url.substr(1);
    strs = str.split("&");

    if(strs[0].split("=")[1]=="error"){
        alert("您输入的账号密码有误，请重新输入！");
    }
}
function showTheInputForTimerTask(){//按照品类获取该品类的热度排行
    var foodType=$("#tasktype").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/food/getFood",//接口名字
        dataType: "json",
        //contentType: "application/json; charset=utf-8",
        data:{foodType:foodType},
        success: function (data) {
            var stringfortrlist = " <div class='clearfix'></div>";

            for (var i = 0; i < data.data.length; i++)
            {
                var stringfortr = "<div class='wthree'>"+
                    " <div class='col-md-6 wthree-left wow fadeInDown'  data-wow-duration='.8s' data-wow-delay='.2s'> <div class='tch-img'>"+
                    " <a href='singlepage.html'><img src='images/"+data.data[i].imageName+"'class='img-responsive' alt=''></a> </div> </div>"+
                    "<div class='col-md-6 wthree-right wow fadeInDown'  data-wow-duration='.8s' data-wow-delay='.2s'>"+
                    "  <h3><a href='singlepage.html'>"+data.data[i].foodName+"</a></h3>"+
                    " <h6><a href='singlepage.html'>"+data.data[i].address+"</a></h6> <p>人均"+data.data[i].averageMoney+"元</p>"+
                    " <div class='bht1'>"+
                    "  <a onclick='showtheHisTask(this)' class='reload' id='"+data.data[i].id+"'>查看详情</a> </div>"+
                    "  <div class='soci'> <ul>"+
                    "  <li><a  style='border: none' href=''></a></li>"+
                    "</ul> </div> <div class='clearfix'></div> </div> <div class='clearfix'></div> </div>"

                stringfortrlist = stringfortrlist + stringfortr;
            }

            $('#showheatList').html(stringfortrlist);
            //AutoCheckLang();
            //$("#datatableForTask").css("width","100%");
            //$("#CPCEP_id").text(userName+"的节点列表信息");
        }
    });
}
function submitLoginReq(){
    var username=$("#inputEmail").val();
    var password=$("#inputPassword").val();
    //alert(username);
    //alert(password);
    var isready=checkTheInput(username,password);
    if(isready){
        //var hashforpassword = hex_md5(password);
        //$("#inputPassword").val(hashforpassword);
        $("#loginForm").submit();}
}
function checkTheInput(username,password){
    //初始化上次校验的结果
    $("#inputEmail").css("border-color","#cccccc");
    $("#inputPassword").css("border-color","#cccccc");

    //空值校验
    if(username==""){
        $("#inputEmail").css("border-color","red");
        return false;
    }
    if(password==""){
        $("#inputPassword").css("border-color","red");
        return false;
    }
    return true;
}
function showtheHisTask(obj) {//获取此餐品id，并据此请求此用户是否收藏
    var nodeidAndStatus = JSON.stringify({nodeId: obj.id, status: "2"});
    var id = obj.id;
    var detail = obj.name;
    $('#table-modal-new-task').modal('show');
    $('#showNotesInModal').text(detail);

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/collect/isCollect",//接口名字
        dataType: "json",
        //contentType: "application/json; charset=utf-8",
        data:{foodId:id},
        success: function (data) {
            $("#favoriteheart").attr("name",id)
            var isCollect = data.data;//0是未收藏，1是已收藏
            if(isCollect == 1)
                $("#favoriteheart").css("color","red")
            else if(isCollect == 0)
                $("#favoriteheart").css("color","pink")

            //$('#showheatList').html(stringfortrlist);
            //AutoCheckLang();
            //$("#datatableForTask").css("width","100%");
            //$("#CPCEP_id").text(userName+"的节点列表信息");
        }
    });
}
function changeTheFavoriteStatus(){//收藏和取消收藏
    var foodId = $("#favoriteheart").attr("name");

    if($("#favoriteheart").css("color") == "rgb(255, 0, 0)")
    {//执行取消收藏动作
        var dataforUserAndNode= JSON.stringify({
            foodId:foodId,
            type:"0"});
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/collect/doCollect",//接口名字
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data:dataforUserAndNode,
            success: function (data) {
                if(data.data == 1){//操作成功，提示用户
                    alert("您已取消收藏该餐品！");
                    $('#table-modal-new-task').modal('hide');
                    show_my_favorite();
                }else{
                    alert("服务器发生了不可言状的错误！");
                }
            }
        });
    }
    else
    {//执行收藏动作
        var dataforUserAndNode= JSON.stringify({
            foodId:foodId,
            type:"1"});
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/collect/doCollect",//接口名字
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data:dataforUserAndNode,
            success: function (data) {
                if(data.data == 1){//操作成功，提示用户
                    alert("您已收藏该餐品！");
                    $('#table-modal-new-task').modal('hide');
                    show_my_favorite();
                }else{
                    alert("服务器发生了不可言状的错误！");
                }
            }
        });}

}
function show_my_favorite(){//获取我收藏的所有餐品数据

    $("#tasktype").css("display","none")
    $("#favorite").addClass("active act")
    $("#home").removeClass("active act")
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/collect/getCollect",//接口名字
        dataType: "json",
        //contentType: "application/json; charset=utf-8",
        //data:{foodType:foodType},
        success: function (data) {
            var stringfortrlist = "<div class='tc-ch wow fadeInDown' data-wow-duration='.8s' data-wow-delay='.2s'> <div class='tch-img'>"+
                "   <div class='clearfix'></div> </div> <div class='clearfix'></div>";

            for (var i = 0; i < data.data.length; i++)
            {
                var stringfortr = "<div class='wthree'>"+
                    " <div class='col-md-6 wthree-left wow fadeInDown'  data-wow-duration='.8s' data-wow-delay='.2s'> <div class='tch-img'>"+
                    " <a href='singlepage.html'><img src='images/"+data.data[i].imageName+"'class='img-responsive' alt=''></a> </div> </div>"+
                    "<div class='col-md-6 wthree-right wow fadeInDown'  data-wow-duration='.8s' data-wow-delay='.2s'>"+
                    "  <h3><a href='singlepage.html'>"+data.data[i].foodName+"</a></h3>"+
                    " <h6><a href='singlepage.html'>"+data.data[i].address+"</a></h6> <p>人均"+data.data[i].averageMoney+"元</p>"+
                    " <div class='bht1'>"+
                    "  <a onclick='showtheHisTask(this)'  class='reload' id='"+data.data[i].id+"'>查看详情</a> </div>"+
                    "  <div class='soci'> <ul>"+
                    "  <li><a  style='border: none' href=''></a></li>"+
                    "</ul> </div> <div class='clearfix'></div> </div> <div class='clearfix'></div> </div>"

                stringfortrlist = stringfortrlist + stringfortr;
            }

            $('#showheatList').html(stringfortrlist);
            //AutoCheckLang();
            //$("#datatableForTask").css("width","100%");
            //$("#CPCEP_id").text(userName+"的节点列表信息");
        }
    });
}
jQuery(document).ready(function() {	//首先渲染
    var foodType=$("#tasktype").val();
    //alert(foodType);
    $.ajax({//获取用户名，并判断当前用户是否有效用户
        type: "POST",
        url: "http://localhost:8080/api/user/session",//接口名字
        dataType: "json",
        //contentType: "application/json; charset=utf-8",
        data:{foodType:foodType},
        success: function (data) {
            if(data !=null)
                $("#showUserName").text("欢迎您！ "+data.data)
            else
                alert("并没有登录")

        }
    });
    $.ajax({//获取全品类热度排行
        type: "POST",
        url: "http://localhost:8080/api/food/getFood",//接口名字
        dataType: "json",
        //contentType: "application/json; charset=utf-8",
        data:{foodType:foodType},
        success: function (data) {
            var stringfortrlist = " <div class='clearfix'></div>";

            for (var i = 0; i < data.data.length; i++)
            {
                var stringfortr = "<div class='wthree'>"+
                    " <div class='col-md-6 wthree-left wow fadeInDown'  data-wow-duration='.8s' data-wow-delay='.2s'> <div class='tch-img'>"+
                    " <a href='singlepage.html'><img src='images/"+data.data[i].imageName+"'class='img-responsive' alt=''></a> </div> </div>"+
                    "<div class='col-md-6 wthree-right wow fadeInDown'  data-wow-duration='.8s' data-wow-delay='.2s'>"+
                    "  <h3><a href='singlepage.html'>"+data.data[i].foodName+"</a></h3>"+
                    " <h6><a href='singlepage.html'>"+data.data[i].address+"</a></h6> <p>人均"+data.data[i].averageMoney+"元</p>"+
                    " <div class='bht1'>"+
                    "  <a onclick='showtheHisTask(this)' name='"+data.data[i].notes+"' class='reload' id='"+data.data[i].id+"'>查看详情</a> </div>"+
                    "  <div class='soci'> <ul>"+
                    "  <li><a  style='border: none' href=''></a></li>"+
                    "</ul> </div> <div class='clearfix'></div> </div> <div class='clearfix'></div> </div>"

                stringfortrlist = stringfortrlist + stringfortr;
            }

            $('#showheatList').html(stringfortrlist);
            //AutoCheckLang();
            //$("#datatableForTask").css("width","100%");
            //$("#CPCEP_id").text(userName+"的节点列表信息");
        }
    });
    $.ajax({//获取个性推荐列表
        type: "POST",
        url: "http://localhost:8080/api/food/getPersonal",//接口名字
        dataType: "json",
        //contentType: "application/json; charset=utf-8",
        data:{foodType:foodType},
        success: function (data) {
            var stringfortrlist = " <h4>个性推荐</h4>";

            for (var i = 0; i < data.data.length; i++)
            {
                var stringfortr = "<div class='blog-grids wow fadeInDown'  data-wow-duration='.8s' data-wow-delay='.2s'>"+
                    "  <div class='blog-grid-left'> <a><img src='images/"+data.data[i].imageName+"' class='img-responsive' alt=''></a> </div>"+
                    "<div class='blog-grid-right'>"+
                    "   <h5><a onclick='showtheHisTask(this)' id='"+data.data[i].id+"' name='"+data.data[i].notes+"'>"+data.data[i].foodName+"</a> </h5> </div>"+
                    "<div class='clearfix'> </div> </div>"

                stringfortrlist = stringfortrlist + stringfortr;
            }
            $('#showRecommened').html(stringfortrlist);
        }
    });
})
