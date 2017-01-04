var url = location.search; //获取url中"?"符后的字串，只用来控制显示
var theRequest = new Object();
if (url.indexOf("?") != -1) {
  var str = url.substr(1);
  strs = str.split("&");

  if(strs[0].split("=")[1]=="error"){
    alert("您输入的账号密码有误，请重新输入！");
  }
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
jQuery(document).ready(function() {	//首先渲染
  var foodType=$("#tasktype").val();
  alert(foodType);
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
        "  <li><a  style='border: none' href=''><i class='glyphicon glyphicon-heart'></i></a></li>"+
        "</ul> </div> <div class='clearfix'></div> </div> <div class='clearfix'></div> </div>"

        stringfortrlist = stringfortrlist + stringfortr;
        //var idforlog=i+1;//加了1的，要减去
        //var textforOperateButton = (data.data[i].status==0)?"开启":"关闭";
        //var nodeStatus;
        //var nodeType;
        //if(data.data[i].status==0){
        //  nodeStatus="关闭";
        //}else if(data.data[i].status==1){
        //  nodeStatus="活跃";
        //}else{
        //  nodeStatus="空闲";
        //}
        //if(data.data[i].type==0){
        //  nodeType="Binary";
        //}else if(data.data[i].type==1){
        //  nodeType="Java";
        //}else{
        //  nodeType="Python";
        //}
        //var stringForConvert=data.data[i].nodeId+"&"+data.data[i].taskAmount+"&"+idforlog+"&"+textforOperateButton;
        //var stringfortr ="<tr class=\"gradeX\">"+
        //    "<td class=\"center\"><input type=\"checkbox\" name=\"checkList\"></td>"+
        //    "<td class=\"center\">"+idforlog+"</td>"+
        //    "<td class=\"center\">"+data.data[i].nodeId+"</td>"+
        //    "<td class=\"center\">"+nodeType+"</td>"+
        //    "<td class=\"center\">"+data.data[i].nodeName+"</td>"+
        //    "<td class=\"center\">"+userName+"</td> <td class=\"center\" id='"+data.data[i].nodeId+"'>"+nodeStatus+"</td>"+
        //    "<td class=\"hidden-xs\"><a onclick='changeToTaskView(this)' id='"+data.data[i].nodeId+"' class=\"btn btn-info\" style=\"font-size:4px;padding:0px 8px;\">"+data.data[i].taskAmount+"</a></td>"+
        //    "<td class=\"center\"><a onclick='showtheHisTask(this)'  id='"+data.data[i].nodeId+"' class=\"btn btn-info\" style=\"font-size:4px;padding:0px 8px;\">"+data.data[i].historyTaskAmount+"</a></td>"+
        //    "<td class=\"center hidden-xs\"><a href=\"#table-modal-showVelocity\" data-toggle=\"modal\" class=\"btn btn-info\" style=\"font-size:4px;padding:0px 8px;\">详情</a></td>"+
        //    "<td class=\"center hidden-xs\"><a href=\"#table-modal-showVelocity\" data-toggle=\"modal\" class=\"btn btn-info\" style=\"font-size:4px;padding:0px 8px;\">详情</a></td>"+
        //    "<td class=\"center hidden-xs\"><a href=\"#table-modal-showVelocity\" data-toggle=\"modal\" class=\"btn btn-info\" style=\"font-size:4px;padding:0px 8px;\">详情</a></td>"+
        //    "<td class=\"center hidden-xs\"><a onclick='showTheWarnModal(this)' id="+stringForConvert+" class=\"btn btn-info\" style=\"font-size:4px;padding:0px 8px;\">"+textforOperateButton+"</a></td>"+
        //    "</tr>";
        //stringfortrlist = stringfortrlist + stringfortr;
      }

      $('#showheatList').html(stringfortrlist);
      //AutoCheckLang();
      //$("#datatableForTask").css("width","100%");
      //$("#CPCEP_id").text(userName+"的节点列表信息");
    }
  });
})
