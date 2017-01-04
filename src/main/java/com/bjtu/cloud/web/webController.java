package com.bjtu.cloud.web;

import com.bjtu.cloud.common.entity.City;
import com.bjtu.cloud.common.entity.User;
import com.bjtu.cloud.common.entity.UserInfo;
import com.bjtu.cloud.common.webDao.RestResult;
import com.bjtu.cloud.gate.NodeService;
import com.bjtu.cloud.gate.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kafukaaa on 17/1/4.
 */
@RestController
public class webController {

  @Autowired
  private  UserService userService;
  @Autowired
  private NodeService nodeService;

  SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  SimpleDateFormat df2 = new SimpleDateFormat("HHmmss");

  //用户登录
  @RequestMapping(value = "api/user/login", method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                  String userName, String password) {
    try {
      ModelAndView mv = new ModelAndView();
      User user = userService.login(userName, password);
      if (user == null){
        //跳转到错误页面
        mv.setViewName("redirect:/index3.jsp");
      }else if (user.getRole() == 0) {
        //跳转到管理员页面
        mv.setViewName("redirect:/index1.jsp");
      }else if (user.getRole() == 1){
        //跳转到普通用户页面
        mv.setViewName("redirect:/index2.jsp");
      }
      return mv;
    } catch (Exception e) {
      return null;
    }
  }

  //用户登录
  @RequestMapping(value = "api/user/logout", method = RequestMethod.GET)
  public ModelAndView getUserInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                  String userName) {
    try {
      ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/index.jsp");
      return mv;
    } catch (Exception e) {
      return null;
    }
  }

  //获取所有用户信息
  @RequestMapping(value = "api/user/getAllUserInfo", method = RequestMethod.GET)
  public RestResult<List<UserInfo>> modifyStatus() {
    try {
      List<UserInfo> userInfos = userService.getAll();
      return  RestResult.succ().data(userInfos).build();
    }catch (Exception e) {
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }

  //用户新增节点
  @RequestMapping(value = "api/user/addNode", method = RequestMethod.POST)
  public RestResult<List<UserInfo>> addNode(String userName, Integer type) {
    try{
      String nodeId = nodeService.addNodeInNodeInfo(type);
      if(!nodeId.isEmpty()) {
        nodeId = "," + nodeId;
        Integer flag = userService.addNodeInUserInfo(userName, nodeId);
        if (flag == 1) {
          List<UserInfo> userInfos = userService.getAll();
          return RestResult.succ().data(userInfos).build();
        } else {
          return RestResult.fail().build();
        }
      }else {
        return RestResult.fail().build();
      }
    }catch (Exception e){
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }

  //获取所有用户信息
  @RequestMapping(value = "api/notes/getNotesByState", method = RequestMethod.GET)
  public RestResult<List<City>> getNotesByState() {
    try {
//      String state1 =new String(state.getBytes("ISO-8859-1"),"UTF-8");
      String state = "gg";
      List<City> cities = userService.getNotesByState(state);
      return  RestResult.succ().data(cities).build();
    }catch (Exception e) {
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }

  /*
     *采用spring提供的上传文件的方法
     */
  @RequestMapping( value = "api/notes/insertOne")
  public RestResult<Integer>  springUpload(HttpServletRequest request, String state, String notes) throws IllegalStateException, IOException
  {
    City city = new City();

    //设置服务器放文件的目录
    String path="/Users/Kafukaaa/Downloads/file";
    String hostPath = null;
    File f = null;
    long  startTime=System.currentTimeMillis();
    //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
    CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
        request.getSession().getServletContext());
    //检查form中是否有enctype="multipart/form-data"
    if(multipartResolver.isMultipart(request))
    {
      //将request变成多部分request
      MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
      //获取multiRequest 中所有的文件名
      Iterator iter=multiRequest.getFileNames();
      while(iter.hasNext())
      {
        //一次遍历所有文件
        MultipartFile file=multiRequest.getFile(iter.next().toString());
        if(file!=null)
        {
          hostPath = path + "/" + System.currentTimeMillis() + ".jpg";
//          f = new File(path,file.getOriginalFilename());
          f = new File(path,System.currentTimeMillis() + ".jpg");
          if(!f.exists()) {
            // 若文件不存在，先创建文件所在的目录
            System.out.println("Make dir!");
            f.getParentFile().mkdirs();
          }
          //上传
          file.transferTo(f);
        }

      }

    }
    long  endTime=System.currentTimeMillis();
    System.out.println(f.getName());
    System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");

    try {
      city.setState(state);
      city.setNotes(notes);
      city.setPath(hostPath);
      city.setTime(df1.parse(df1.format(new Date())));

      Integer result = userService.insertOne(city);

      return  RestResult.succ().data(result).build();
    }catch (Exception e) {
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }

  /**
   * 获取图片
   * @param request
   * @param response
   */
  @RequestMapping( value = "api/getHomeImage", method = RequestMethod.GET)
  public void GetHomeImage(HttpServletRequest request
      , HttpServletResponse response, String path){
    //获取首页图片显示类
    String filePath = path;
    try {
      byte data[] = readFile(filePath);
      response.setContentType("image/jpg"); //设置返回的文件类型
      OutputStream os = response.getOutputStream();
      os.write(data);
      os.flush();
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * 从文件地址，读取文件的Byte数组
   * @param filename
   * @return
   * @throws IOException
   */
  public static byte[] readFile(String filename) throws IOException {
    if( filename==null || filename.equals("") ){
      throw new NullPointerException("无效的文件路径");
    }
    File file =new File(filename);
    long len = file.length();
    byte[] bytes = new byte[(int)len];

    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
    int r = bufferedInputStream.read( bytes );
    if (r != len)
      throw new IOException("读取文件不正确");
    bufferedInputStream.close();
    return bytes;
  }
}
