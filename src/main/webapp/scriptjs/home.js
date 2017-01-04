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
