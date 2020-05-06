package or.wr.bookrecommendationsystem.controller;

import or.wr.bookrecommendationsystem.entity.Article;
import or.wr.bookrecommendationsystem.entity.Book;
import or.wr.bookrecommendationsystem.entity.Classification;
import or.wr.bookrecommendationsystem.mapper.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//负责对不需要拦截的页面进行跳转
@Controller
public class PageController {

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





    @RequestMapping("/discover")
    public ModelAndView toDiscover(){

        List list = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Map map = new HashMap();
            map.put("article", articleMapper.findArticleDynamic(i));
            map.put("books",bookMapper.findBookByDynamic(i*2));
            list.add(map);
        }
        ModelAndView modelAndView = new ModelAndView("discover");
        modelAndView.addObject("firstList",list);
        return modelAndView;
    }


    @RequestMapping("/classification")
    public String toClassification(Model model){
        List<Classification> classifications = bookMapper.findAllClassification();
        model.addAttribute("classifications",classifications);
        return "classification";
    }

    @RequestMapping("/search")
    public String toSearch(){

        return "search";
    }

    @RequestMapping("/register")
    public String toRegister(){

        return "register";
    }

    @RequestMapping("/login")
    public String toLogin(){

        return "login";
    }




    /**根据bId显示文章**/
    @RequestMapping("/browserArticle")
    public ModelAndView toBrowserArticle(String aId){
        int iaId = Integer.parseInt(aId);
        new Thread(()->{
            recommendationMapper.addArHisByAid(iaId);
        }).start();
        Article article = articleMapper.findTitleByAId(iaId);
        System.out.println(article);
        System.out.println("click the article wit id  " + iaId );
        ModelAndView modelAndView = new ModelAndView("browserArticle");
        modelAndView.addObject("article",article);
        modelAndView.addObject("aComments",commentMapper.findAllACommentByAId(iaId));

        return modelAndView;
    }
    @RequestMapping("/getContent")
    @ResponseBody
    public byte[] getContent(HttpServletRequest request) {
        int aId = Integer.parseInt(request.getParameter("aId"));
        System.out.println("异步请求获取aId是： " + aId);

        String contentPath = articleMapper.findContentPathByAId(aId);
        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(contentPath)));
            ByteArrayOutputStream bOut = new ByteArrayOutputStream()){
            byte[] buffer = new byte[1024*10];
            int len;
            while ((len = inputStream.read(buffer))!=-1){
                bOut.write(buffer,0,len);
            }
            return bOut.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "加载失败或文章不存在".getBytes();

    }


    @RequestMapping("/dynamicLoading")
    @ResponseBody
    public Map dynamicLoading(String page) throws InterruptedException {
        int p = Integer.parseInt(page);
        int articles = articleMapper.countArticles();
        int books = bookMapper.countBooks();
//        System.out.println(p + "----" + articles + "----" + books);
        Map map = new HashMap<String,Object>();
        if(p<articles && 2*p < books){
            map.put("check","true");
            map.put("article",articleMapper.findArticleDynamic(p));
            map.put("books",bookMapper.findBookByDynamic(2*p));
        }else {
            map.put("check","false");

        }


//        Thread.sleep(2_000);
        return map;
    }


    @RequestMapping("/browserBook")
    public ModelAndView browserBook(HttpServletRequest request){
        String sBId = request.getParameter("bId");
        int b = Integer.parseInt(sBId);
//        System.out.println("查看的图书bId是：" + b);
        Book book = bookMapper.findBookByBId(b);

        if (request.getSession().getAttribute("user")!=null){
            String username = request.getSession().getAttribute("user").toString();
            recommendationMapper.addCHits(username,bookMapper.findCIdByBId(b));
        }

        ModelAndView modelAndView = new ModelAndView("/browserBook");
        modelAndView.addObject("book",book);
        //只有从书桌转来的链接才携带userMark参数，表示该bId在收藏系列
        String userMark = request.getParameter("userMark");
//        System.out.println(userMark);
        modelAndView.addObject("userMark", userMark);
        modelAndView.addObject("bComments",commentMapper.findAllBCommentByBId(b));
        return modelAndView;
    }


    @RequestMapping("/toBrowserBooksByCId")
    public ModelAndView browserBooksByCId(String cId){

//        System.out.println(cId);
        int icId = Integer.parseInt(cId);
        List<Book> list = bookMapper.findBooksByCId(icId);
        ModelAndView modelAndView = new ModelAndView("browserBooksByCId");
        if(list.size()<100){
            modelAndView.addObject("books",list);
        }
        return modelAndView;
    }

    /*搜索*/
    @RequestMapping("searchBook")
    public ModelAndView searchBook(String key){
        System.out.println(key);
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("books",bookMapper.findBooksByLikeKey(key));
        return modelAndView;
    }
    @RequestMapping("searchArticle")
    public ModelAndView searchArticle(String key){
        System.out.println(key);
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("articles",articleMapper.findATCDByKey(key));
        return modelAndView;
    }



}
