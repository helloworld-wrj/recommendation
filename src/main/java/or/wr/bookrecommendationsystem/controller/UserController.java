package or.wr.bookrecommendationsystem.controller;

import or.wr.bookrecommendationsystem.entity.ArticleComment;
import or.wr.bookrecommendationsystem.entity.Book;
import or.wr.bookrecommendationsystem.entity.BookComment;
import or.wr.bookrecommendationsystem.impl.MailService;
import or.wr.bookrecommendationsystem.mapper.*;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import or.wr.bookrecommendationsystem.entity.User;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


//控制注册登录、以及用户登录后的页面
@Controller
public class UserController {

    @Resource
    private MailService mailService;
    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ClassicSayingMapper classicSayingMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private RecommendationMapper recommendationMapper;

    @RequestMapping({"/","index"})
    public ModelAndView toIndex(HttpServletRequest request){
        if (request.getSession().getAttribute("user")!=null){
            System.out.println("进行用户登录推荐");
            return getIndexInfoByLogin(request.getSession().getAttribute("user").toString());
        }
        return getIndexInfo();
    }

    @RequestMapping("/checkUsername")
    @ResponseBody
    public String checkUsername(HttpServletRequest request){
        String username = request.getParameter("username");
        if (username.equals(userMapper.findUsername(username))){
            return "true";
        }
        else
            return "false";
    }

    @RequestMapping("/checkEmail")
    @ResponseBody
    public String checkEmail(HttpServletRequest request){
        String email = request.getParameter("email");
        if (email.equals(userMapper.findEmail(email))){
            return "true";
        }
        else
            return "false";
    }

    @RequestMapping("register/getCode")
    @ResponseBody
    public void getCode(HttpServletRequest request){
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        StringBuilder code = new StringBuilder();
        new Thread(()->{
            while (code.length()<6){
                code.append((int) (Math.random() * (10+1) - 1));
            }

            if (tUserMapper.findTUserCode(username,email)==null){
                tUserMapper.addTUser(username,email,code.toString(),System.nanoTime()/1000000L+"");
            }
            else {
                tUserMapper.updateCode(username,email,code.toString(),System.nanoTime()/1000000L+"");
            }
        }).start();

        System.out.println("ok");
    }

    @RequestMapping("register/checkCode")
    @ResponseBody
    public String checkCode(HttpServletRequest request){
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        if(tUserMapper.findTUserCode(username,email).equals(code)){
            return "true";
        }
        else
            return "false";
    }

    @RequestMapping("/handleRegister")
    public ModelAndView handleRegister(String username,String email,String password,String code) throws NoSuchAlgorithmException {
        if ((System.nanoTime()/1000000L-Long.parseLong(tUserMapper.findTUserValidity(username,email)))/1000 < 180){
            if(tUserMapper.findTUserCode(username,email).equals(code)){
                System.out.println(encryptionThree(password));
               if (userMapper.addUser(new User(username,email,encryptionThree(password)))>0){
                   ModelAndView mav = new ModelAndView("login");
                   mav.addObject("tUser", username);
                   mav.addObject("lMsg","Registered successfully!!!");
                   return mav;
               }
            }
        }
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("rMsg","Registered failed, may be wrong code");
        return mav;
    }

    //-u:--- 待优化，同一验证username
    @RequestMapping("/handleLogin")
    public ModelAndView handleLogin(String account,String password,String mark, HttpServletRequest request) throws NoSuchAlgorithmException {
        System.out.println(account + "---" + password + "---" + mark);
        if (mark.equals("email")){
            if (encryptionThree(password).equals(userMapper.checkPasswordByEmail(account))){
                HttpSession session = request.getSession();
                account = userMapper.findUsernameByEmail(account);
//                System.out.println("email 已经设置 session " + account);
                session.setAttribute("user",account);
                return getIndexInfoByLogin(request.getSession().getAttribute("user").toString());
            }
        }else if(mark.equals("username")){
            if (encryptionThree(password).equals(userMapper.checkPasswordByUsername(account))){
                HttpSession session = request.getSession();
//                System.out.println("username 已经设置 session " + account);
                session.setAttribute("user",account);

                return getIndexInfoByLogin(request.getSession().getAttribute("user").toString());
            }
        }
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("tUser", account);
        mav.addObject("lMsg","Wrong account or password");
        return mav;

    }


    /*我的书桌*/
    @RequestMapping("user/myDesk")
    public ModelAndView toMyDesk(HttpServletRequest request){
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        String username = session.getAttribute("user").toString();
        ArrayList<Book> books = findDeskBooks(username);
        modelAndView = new ModelAndView("user/myDesk");
        modelAndView.addObject("books",books);
        modelAndView.addObject("userMark","yes");
        return modelAndView;
    }

    //收藏图书
    @RequestMapping("user/addMyDesk")
    @ResponseBody
    public HashMap<String,String> addMyDesk(HttpServletRequest request){
        HttpSession session = request.getSession();
        HashMap<String,String> map = new HashMap();
        //u:---当前拦截器无法拦截异步请求
        if (session.getAttribute("user")!=null){
            String user = session.getAttribute("user").toString();
            int bId = Integer.parseInt(request.getParameter("bId"));
//            System.out.println(bId+"-----"+user);
            if (userMapper.findBIdByUserFDesk(user,bId)<1){
                userMapper.addBookToDesk(user,bId);
                recommendationMapper.addCBookIndex(user,bookMapper.findCIdByBId(bId));
//                System.out.println("add to desk successfully user is " + user);
            }
            map.put("mark","true");
            map.put("result", "已经加入书桌");
        }else {
            map.put("mark","false");
            map.put("result", "请先登录");
        }

        return map;
    }

    @RequestMapping("/user/removeFromDesk")
    public ModelAndView removeFromDesk(String bId, HttpServletRequest request){
        int ibId = Integer.parseInt(bId);
        String user = request.getSession().getAttribute("user").toString();
        recommendationMapper.deleteCBookIndex(user,bookMapper.findCIdByBId(ibId));
        userMapper.deleteDBookByBIdFDesk(user,ibId);
        ModelAndView modelAndView = new ModelAndView("user/myDesk");
        modelAndView.addObject("books",findDeskBooks(user));
        modelAndView.addObject("userMark","yes");
        return modelAndView;
    }

    /*个人中心*/
    @RequestMapping("/user/person")
    public ModelAndView toPerson(HttpServletRequest request){

        return getPersonInfo(request);
    }

    @RequestMapping(value = "user/up/getCode",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> upGetCode(HttpServletRequest request){
//        HttpSession session = request.getSession();
        HashMap<String,String> map = new HashMap<>();
//        String username = session.getAttribute("user").toString();
        String email = request.getParameter("email");
        System.out.println("user update request ---- pwd --- the email is " + email);
        if (email.equals(userMapper.findEmail(email))){
            StringBuilder code = new StringBuilder();
            while (4 > code.length()) {
                code.append((int) (Math.random() * (10 + 1) - 1));
            }
            new Thread(()-> mailService.sendHtmlMail(email,"更改",code.toString())).start();

            map.put("info","已经发送");
            map.put("result","true");
            map.put("code",code.toString());
        }
        else {
            map.put("info","邮箱有误");
            map.put("result","false");
        }
        return map;
    }

    @RequestMapping(value = "user/up/pwd",method = RequestMethod.POST)
    public ModelAndView handUpdatePwd(String password, HttpServletRequest request) throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView("user/person");
        String username = request.getSession().getAttribute("user").toString();
        System.out.println("the update password is " + password);
        if (  userMapper.updatePasswordByUsername(username,encryptionThree(password))>0){
            modelAndView.addObject("up_result","密码修改此成功");
        }
        return modelAndView;
    }

    @RequestMapping(value = "user/up/password",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> checkPasswordByUp(HttpServletRequest request) throws NoSuchAlgorithmException {
        String username = request.getSession().getAttribute("user").toString();
        String password = request.getParameter("password");
        HashMap<String,String> map = new HashMap();
        if(encryptionThree(password).equals(userMapper.checkPasswordByUsername(username))){
            map.put("result","true");
        }else {
            map.put("result","false");
        }

        return map;
    }

    @RequestMapping(value = "user/up/email",method = RequestMethod.POST)
    public ModelAndView handUpdateEmail(String email, HttpServletRequest request) throws NoSuchAlgorithmException {
        ModelAndView modelAndView = new ModelAndView("user/person");
        String username = request.getSession().getAttribute("user").toString();
        System.out.println("the update email is " + email);
        if (userMapper.updateEmailByUsername(username,email)>0){
            modelAndView.addObject("up_result","邮箱修改此成功");
        }
        return modelAndView;
    }

    //导航栏获取用户名
    @ResponseBody
    @RequestMapping("user/getUsername")
    public HashMap<String,String> getUsername(HttpServletRequest request){
        HashMap<String,String> map = new HashMap<String,String>();
        if(request.getSession().getAttribute("user")!=null){
            map.put("result","true");
            map.put("user",request.getSession().getAttribute("user").toString());
        }else {
            map.put("result","false");
            map.put("user","");
        }
        return map;
    }

    /*退出登录*/
    @RequestMapping("user/LogOut")
    public ModelAndView handleLogOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return getIndexInfo();
    }

    /*图书评论*/
    @RequestMapping("user/addBookComment")
    @ResponseBody
    public HashMap<String,String> addBookComment(HttpServletRequest request){
        HashMap<String,String> map = new HashMap();
        String username = request.getSession().getAttribute("user").toString();
        String cmComment = request.getParameter("cmContent");
        int bId = Integer.parseInt(request.getParameter("bId"));
//        System.out.println("bId is " + bId + " the comment is " + cmComment);
        if (commentMapper.addBookComment(new BookComment(cmComment,bId,username)) > 0){
            map.put("result","评论成功");
            map.put("mark","true");
        }else {
            map.put("result","评论失败");
            map.put("mark","false");
        }
        return map;
    }

    /*文章评论*/
    @RequestMapping("user/addArticleComment")
    @ResponseBody
    public HashMap<String,String> addArticleComment(HttpServletRequest request){
        HashMap<String,String> map = new HashMap();
        String username = request.getSession().getAttribute("user").toString();
        String cmComment = request.getParameter("cmContent");
        int aId = Integer.parseInt(request.getParameter("aId"));
//        System.out.println("bId is " + bId + " the comment is " + cmComment);
        if (commentMapper.addArticleComment(new ArticleComment(cmComment,aId,username)) > 0){
            recommendationMapper.addArCommentsIndexByAid(aId);
            map.put("result","评论成功");
            map.put("mark","true");
        }else {
            map.put("result","评论失败");
            map.put("mark","false");

        }
        return map;
    }

    @RequestMapping("user/deleteBComment")
    public ModelAndView deleteBComment(HttpServletRequest request, String cm_id){
        int id = Integer.parseInt(cm_id);
        ModelAndView modelAndView =  getPersonInfo(request);
        if (commentMapper.deleteBCommentById(id) > 0){
            modelAndView.addObject("up_result","操作成功");
        }
        return modelAndView;
    }
    @RequestMapping("user/deleteAComment")
    public ModelAndView deleteAComment(HttpServletRequest request, String cm_id){
        int id = Integer.parseInt(cm_id);
        ModelAndView modelAndView =  getPersonInfo(request);
        if (commentMapper.deleteACommentById(id) > 0){
            modelAndView.addObject("up_result","操作成功");
        }
        return modelAndView;
    }


    private static String encryptionThree(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        for(int i = 0; i < 3; i++){
            md.update(str.getBytes());
            byte[] output = md.digest();
            str = bytesToHex(output);
        }

        return str;
    }

    private static String bytesToHex(byte[] b) {
        char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder buf = new StringBuilder();
        for (byte value : b) {
            buf.append(hexDigit[(value >> 4) & 0x0f]);
            buf.append(hexDigit[value & 0x0f]);
        }
        return buf.toString();
    }

    /*复用数据提取*/
    //1、游客浏览，用户登录后，首页数据
    public ModelAndView getIndexInfo(){
        setIndexInfoBySystem();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("first",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(1)));
        modelAndView.addObject("second",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(2)));
        modelAndView.addObject("third",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(3)));
        modelAndView.addObject("fourth",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(4)));
        modelAndView.addObject("fifth",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(5)));
        modelAndView.addObject("classicSaying",classicSayingMapper.findClassicSaying());
        modelAndView.addObject("latestBook",bookMapper.findLatestBook());
//        setIndexInfoBySystem();
        return modelAndView;
    }

    //推荐实现，未登录，最热门的一篇，最新的四篇
    public void setIndexInfoBySystem(){
        int mpAid = recommendationMapper.findMostPopularAId();
        int[] a = articleMapper.findFourLastAid(mpAid);
//        System.out.println("the most popular article is " + mpAid);
//        Arrays.stream(a).forEach(i -> System.out.print(i + ", "));
        articleMapper.setReArticle(1,mpAid,1);
        for (int i = 2; i < 6; i++){
            articleMapper.setReArticle(i,a[i-2],i);
        }

    }

    /*推荐实现，针对已经登录用户，推荐三篇，最热门的一篇，最新一篇*/
    //针对用户登录后
    public ModelAndView getIndexInfoByLogin(String username){
        setIndexInfoForUsers(username);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("first",articleMapper.selectToUpdate(recommendationMapper.findReAidByReIndexAndUser(1,username)));
        modelAndView.addObject("second",articleMapper.selectToUpdate(recommendationMapper.findReAidByReIndexAndUser(2,username)));
        modelAndView.addObject("third",articleMapper.selectToUpdate(recommendationMapper.findReAidByReIndexAndUser(3,username)));
        modelAndView.addObject("fourth",articleMapper.selectToUpdate(recommendationMapper.findReAidByReIndexAndUser(4,username)));
        modelAndView.addObject("fifth",articleMapper.selectToUpdate(recommendationMapper.findReAidByReIndexAndUser(5,username)));
        modelAndView.addObject("classicSaying",classicSayingMapper.findClassicSaying());
        modelAndView.addObject("latestBook",bookMapper.findLatestBook());
        return modelAndView;
    }

    public void setIndexInfoForUsers(String username){
//        if (recommendationMapper.checkNumbersOfReAid(username)!=0){
        //有 bug
            for(int i = 1, j = 5; i < 6; i++){
                /* 当删除的文章涉及登录用户推荐表时，重新从默认推荐表获取*/
                recommendationMapper.updateKeepNewReAid(j--,articleMapper.findAIdFromReArticle(i),username);
                //优化 failure
                /*System.out.println(recommendationMapper.findAllReAidByUsername(username).length);
                Set existReAid = Stream.of(recommendationMapper.findAllReAidByUsername(username))
                        .collect(Collectors.toSet());
                existReAid.stream().forEach(System.out::println);
                int existNumbers = existReAid.size();
                while (existNumbers < 5){
                    recommendationMapper.updateReAidByNull(username,articleMapper.findLatestAIdByReLoginUser(username));
                }*/

            }
//        }
//        else if(recommendationMapper.checkNewUser(username)>0){
            int[] cIds = recommendationMapper.findTopCIndexByUsername(username);
            int[] existReAid = recommendationMapper.findAllReAidByUsername(username);
//            Arrays.stream(existReAid).forEach(i -> System.out.print(i + ","));
            for (int i = 0, j = 1; i < cIds.length; i++) {
                if (articleMapper.countArticlesNumsByCid(cIds[i]) != 0){
                    int a = articleMapper.findLatestAidByCid(cIds[i]);
                    int temp = recommendationMapper.findReAidByReIndexAndUser(j,username);
                    if (IntStream.of(existReAid).noneMatch(k -> k == a)){
//                        System.out.println("change");
                        recommendationMapper.updateReAidToRLU(6-j,username,temp);
                        recommendationMapper.updateReAidToRLU(j,username,a);
                    }
                    j++;
                }

            }

//        }
    }
    @Test
    public void test01(){
        int[] a = {1,2,4,5,6};
        if (IntStream.of(a).anyMatch(i -> i !=9)){
            System.out.println("no exist");
        }
        System.out.println(IntStream.of(a).anyMatch(i -> i==1));
        System.out.println(IntStream.of(a).anyMatch(i -> i!=1));
        System.out.println(IntStream.of(a).anyMatch(i -> i==3));
        System.out.println(IntStream.of(a).noneMatch(i -> i==3));
        System.out.println(IntStream.of(a).noneMatch(i -> i!=3));
    }


    //2、每次打开书桌获取的数据
    public ArrayList<Book> findDeskBooks(String user){

        ArrayList<Integer> list = userMapper.findAllBIdByUserFDesk(user);
//        System.out.println(list.size());
        ArrayList<Book> books = new ArrayList<>();
        for (Object o : list) {
            books.add(bookMapper.findBookByBId(Integer.parseInt(o.toString())));
        }
        return books;
    }

    public ModelAndView getPersonInfo(HttpServletRequest request){
        String username = request.getSession().getAttribute("user").toString();
        ModelAndView modelAndView = new ModelAndView("user/person");
        modelAndView.addObject("up_result","");
        modelAndView.addObject("bComments",commentMapper.findAllBCommentByAuthor(username));
        modelAndView.addObject("aComments",commentMapper.findAllACommentByAuthor(username));
        return modelAndView;
    }




}
