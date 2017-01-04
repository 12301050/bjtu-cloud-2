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
      var stringfortrlist = "<div class='tc-ch wow fadeInDown' data-wow-duration='.8s' data-wow-delay='.2s'> <div class='tch-img'>"+
          "<a href='singlepage.html'><img src='images/t4.jpg' class='img-responsive' alt=''></a> </div>"+
          "   <h3><a href='singlepage.html'>Lorem Ipsum is simply</a></h3>"+
          "<h6>BY <a href='singlepage.html'>ADAM ROSE </a>JULY 10 2016.</h6> <p>Top1</p> <div class='bht1'>"+
          "   <a href='#table-modal-new-task data-toggle='modal' class='reload' id='create_task_button'>查看详情</a> </div> <div class='soci'> </div>"+
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
            "  <a href='table-modal-new-task' data-toggle='modal' class='reload' id='create_task_button'>查看详情</a> </div>"+
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
  $('#table-modal-new-task').modal('show');

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/collect/isCollect",//接口名字
        dataType: "json",
        //contentType: "application/json; charset=utf-8",
        data:{foodId:id},
        success: function (data) {
            var isCollect = data.data;//0是未收藏，1是已收藏
            

            $('#showheatList').html(stringfortrlist);
            //AutoCheckLang();
            //$("#datatableForTask").css("width","100%");
            //$("#CPCEP_id").text(userName+"的节点列表信息");
        }
    });
}
function show_my_favorite(){//获取我收藏的所有餐品数据
    //alert("!!!");
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
                "<a href='singlepage.html'><img src='images/t4.jpg' class='img-responsive' alt=''></a> </div>"+
                "   <h3><a href='singlepage.html'>Lorem Ipsum is simply</a></h3>"+
                "<h6>BY <a href='singlepage.html'>ADAM ROSE </a>JULY 10 2016.</h6> <p>Top1</p> <div class='bht1'>"+
                "   <a  onclick='showtheHisTask(this)' href='#table-modal-new-task' data-toggle='modal' class='reload' id='create_task_button'>查看详情</a> </div> <div class='soci'> </div>"+
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
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/api/food/getFood",//接口名字
    dataType: "json",
    //contentType: "application/json; charset=utf-8",
    data:{foodType:foodType},
    success: function (data) {
      var stringfortrlist = "<div class='tc-ch wow fadeInDown' data-wow-duration='.8s' data-wow-delay='.2s'> <div class='tch-img'>"+
          "<a href='singlepage.html'><img src='images/t4.jpg' class='img-responsive' alt=''></a> </div>"+
          "   <h3><a href='singlepage.html'>Lorem Ipsum is simply</a></h3>"+
          "<h6>BY <a href='singlepage.html'>ADAM ROSE </a>JULY 10 2016.</h6> <p>Top1</p> <div class='bht1'>"+
          "   <a  onclick='showtheHisTask(this)' href='#table-modal-new-task' data-toggle='modal' class='reload' id='create_task_button'>查看详情</a> </div> <div class='soci'> </div>"+
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
})