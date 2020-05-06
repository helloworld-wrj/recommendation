package or.wr.bookrecommendationsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import or.wr.bookrecommendationsystem.entity.Article;
import or.wr.bookrecommendationsystem.entity.BatchBook;
import or.wr.bookrecommendationsystem.entity.Book;
import or.wr.bookrecommendationsystem.mapper.*;
import or.wr.bookrecommendationsystem.server.FileService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ClassicSayingMapper classicSayingMapper;

    @Resource
    private RecommendationMapper recommendationMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private RedisTemplate redisTemplate;

    FileService fileService = new FileService();

    /*一、manager页面跳转*/


    @RequestMapping(value = "/test")
    public String mangerTest(){

        return "manager/test";
    }

    @RequestMapping({"/","index"})
    public String toIndex(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user","test01");
        return "manager/index";
    }

    @RequestMapping("/mBooks")
    public String tomBooks(){

        return "manager/mBooks";
    }

    @RequestMapping("/toAddArticle")
    public ModelAndView toAddArticle(HttpServletRequest request){
        HttpSession session = request.getSession();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式 14
        String l = df.format(new Date());
        String articleIdentification = session.getAttribute("user").toString()+l;
        ModelAndView modelAndView = new ModelAndView("manager/addArticle");
        modelAndView.addObject("articleIdentification",articleIdentification);
        return modelAndView;
    }

    @RequestMapping("/toSeDeUpArticle")
    public String toSeDeUpArticle(){

        return "manager/seDeUpArticle";
    }

    @RequestMapping("/toUpdateArticle")
    public ModelAndView toUpdateArticle(String aId){
        int a = Integer.parseInt(aId);
        ModelAndView modelAndView = new ModelAndView("manager/updateArticle");
        Article article = articleMapper.selectToUpdate(a);
        modelAndView.addObject("article",article);
        return modelAndView;
    }

    @RequestMapping("/mRecommendation")
    public String toMRecommendation(){

        return "manager/mRecommendation";
    }


    /*二、图书管理：增删改查*/

    @RequestMapping(value = "/addBook",method = RequestMethod.POST)
    public ModelAndView handleAddBook(String name, String author, String cId, String publicationDate, String publication, MultipartFile file, String digest){
     /*   System.out.println(name);
        System.out.println(author);
        System.out.println(cId);
        System.out.println(publication);
        System.out.println(publicationDate);
        System.out.println(digest);
        System.out.println(file.getOriginalFilename());*/

        String oldImgName = file.getOriginalFilename();
        assert oldImgName != null;
        String[] str = oldImgName.split("\\.");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String l = df.format(new Date());
        String newImgName = l+"." + str[1];

        String whoAdd = "no";
        String[] str1 = fileService.saveBookCoverPhoto(file,newImgName);
        String coverPhotoUrl= str1[1];
        bookMapper.addBook(new Book(name,author,Integer.parseInt(cId),publication,publicationDate,str1[0],coverPhotoUrl,digest,whoAdd));
        ModelAndView modelAndView = new ModelAndView("manager/mBooks");
        modelAndView.addObject("result","添加成功");
        return modelAndView;
    }

    @RequestMapping("/deleteBook")
    public ModelAndView deleteOneBook(String bId, String name, String author){
        String result = "已经删除bId = "+bId+ " 的图书";
        System.out.println(bId+"= name="+name+"= author="+author);
        if(bId.isEmpty()){

            bId = bookMapper.findBidByNameAndAuthor(name,author)+"";
            System.out.println("bId is empty " + bId);
            result = "已经删除 "+ name;
        }
            System.out.println("bId is no empty" + bId);
            String path = bookMapper.findCoverPhotoPath(Integer.parseInt(bId));
            System.out.println(path);
            File file = new File(path);
            System.out.println(file.delete());
            System.out.println(bookMapper.deleteOneBookByBId(Integer.parseInt(bId)));


        ModelAndView modelAndView = new ModelAndView("manager/mBooks");
        modelAndView.addObject("result",result);
        return modelAndView;

    }


    @RequestMapping(value = "/updateBook",method = RequestMethod.POST)
    public ModelAndView handleUpdateBook(int bId,String name, String author, String cId, String publicationDate, String publication, MultipartFile file, String digest){
        /*System.out.println(bId);
        System.out.println(name);
        System.out.println(author);
        System.out.println(publication);
        System.out.println(publicationDate);
        System.out.println(digest);
        System.out.println(file.getOriginalFilename());*/
        String coverPhotoPath = bookMapper.findCoverPhotoPath(bId);
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(coverPhotoPath))) {
            outputStream.write(file.getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("图片更新失败");
        }
        System.out.println(bookMapper.updateByBId(new Book(bId,name,author,Integer.parseInt(cId),publication,publicationDate,digest,"no")));
        ModelAndView modelAndView = new ModelAndView("manager/mBooks");
        modelAndView.addObject("result","修改成功");
        return modelAndView;
    }

    @RequestMapping("/queryBook")
    public ModelAndView findBooks(int bId,String name, String author, String cId, String publicationDate, String publication){
        List<Book> list = bookMapper.findBooksByFive(new Book(bId,name,author,Integer.parseInt(cId),publication,publicationDate));
        System.out.println(list.size());
        ModelAndView modelAndView = new ModelAndView("manager/mBooks");
        modelAndView.addObject("ifShow","true");
        modelAndView.addObject("books",list);
        return modelAndView;
    }



    @RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
    @ResponseBody
    public String batchAdd( @RequestParam("coverPhotos") MultipartFile[] file, String books){
        JSONArray jsonObject = JSON.parseArray(books);
        System.out.println(jsonObject);
        System.out.println(jsonObject.size());
        System.out.println(file.length);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String l = df.format(new Date());

        for (int i = 0; i < jsonObject.size(); i++) {

            String oldImgName = file[i].getOriginalFilename();
            assert oldImgName != null;
            String[] str = oldImgName.split("\\.");
            String newImgName = l+i+"." + str[1];

            String[] str1 = fileService.saveBookCoverPhoto(file[i],newImgName);
            String coverPhotoUrl= str1[1];

            BatchBook batchBook = JSON.toJavaObject((JSON) jsonObject.get(i), BatchBook.class);
            Book book = new Book();
            book.setName(batchBook.getName());
            book.setAuthor(batchBook.getAuthor());
            book.setcId(Integer.parseInt(batchBook.getcId()));
            book.setPublication(batchBook.getPublication());
            book.setPublicationDate(batchBook.getPublicationDate());
            book.setDigest(batchBook.getDigest());
            book.setCoverPhotoPath(str1[0]);
            book.setCoverPhotoUrl(coverPhotoUrl);
            bookMapper.addBook(book);

        }


        return "后台批量上传中";
    }


    /*三、文章管理：增删改查*/

    //1.1 文章添加
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public String addArticle(String title,String articleIdentification, String digest, String cId,  @RequestParam("coverPhoto") MultipartFile file ){
        System.out.println(title);
        System.out.println(articleIdentification);
        System.out.println(digest);
        System.out.println(cId);
        System.out.println(file.getSize());

        String oldImgName = file.getOriginalFilename();
        assert oldImgName != null;
        String[] str = oldImgName.split("\\.");

        String  fileName = articleIdentification+"."+str[1];

        String[] saveStr = fileService.saveArticleCoverPhoto(file,fileName);

//        System.out.println(saveStr[0].length());
        articleMapper.addArticle(new Article(title,Integer.parseInt(cId), fileService.getArContentFileDir()
                +articleIdentification+".html",saveStr[0],saveStr[1],digest,articleIdentification));
        return "manager/index";
    }
    //1.2 正文保存
    @RequestMapping(value = "/saveContentAsHtml", method = RequestMethod.POST)
    @ResponseBody
    public String saveContentAsHtml(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        int le = session.getAttribute("user").toString().length()+14;
        System.out.println(le);

        ServletInputStream inputStream = request.getInputStream();

        String articleIdentification = new String(inputStream.readNBytes(le));
        System.out.println("标识为：" + articleIdentification + " 的文章的正文准备保存");

        fileService.saveContentAsHtmlFile(inputStream.readAllBytes(),articleIdentification);
//        System.out.println(articleIdentification);
//        System.out.println(new String(inputStream.readAllBytes()));
        System.out.println("正文保存成功");
        return "ok";
    }

    //2 文章修改


    @RequestMapping(value = "/selectArticleByAId",method = RequestMethod.POST)
    public ModelAndView selectArticleByAId(String aId){

        System.out.println("查询的aId是："+aId);
        Article article = articleMapper.findATCDByAId(Integer.parseInt(aId));
//        System.out.println("findATCDByAId：" + article);
        ModelAndView modelAndView = new ModelAndView("manager/seDeUpArticle");
        if (article==null){
            modelAndView.addObject("noDate",true);

        }else {
            modelAndView.addObject("noDate",false);
        }
        modelAndView.addObject("articles",article);
        return modelAndView;
    }

    @RequestMapping("/selectArticleByKey")
    public ModelAndView selectArticleByKey(String key){
        System.out.println("the word of fuzzy querying is : " + key);
        ModelAndView modelAndView = new ModelAndView("manager/seDeUpArticle");
        List<Article> articles = articleMapper.findATCDByKey(key);
//        System.out.println(articles.size());
        if (articles.isEmpty()){
            modelAndView.addObject("noDate",true);
        }else {
            modelAndView.addObject("articles",articles);
        }
        return modelAndView;
    }

    //文章删除
    @RequestMapping(value = "/deleteArticle",method = RequestMethod.POST)
    @ResponseBody
    public String deleteArticle(HttpServletRequest request){
        String aId = request.getParameter("aId");
        int a = Integer.parseInt(aId);
        System.out.println(aId);
        if (articleMapper.checkAId(a)==0){
            System.out.println("not exist");
            return "该文章不存在";
        }
        String coverPhotoPath = articleMapper.findCoverPhotoPath(a);
        String contentPath = articleMapper.findContentPathByAId(a);
        System.out.println("要删除的文章封面图片路径是："+ coverPhotoPath);
        System.out.println("要删除的文章封面正文文件路径是："+ contentPath);
        fileService.deleteFileByPath(contentPath);
        fileService.deleteFileByPath(coverPhotoPath);
        recommendationMapper.deleteArCommentsIndexByAid(a);
        recommendationMapper.deleteReAid(a);
        commentMapper.deleteACommentById(a);
        articleMapper.deleteArticleByAId(a);

        return "操作成功";
    }

    //2、文章更新

    //2.1 更新数据库
    @ResponseBody
    @RequestMapping("/setArticleIdentification")
    public String setArticleIdentification(HttpServletRequest request){
        String identification = request.getParameter("identification");
        System.out.println(identification + "临时identification设置");
        HttpSession session = request.getSession();
        session.setAttribute("identification",identification);
        return "setArticleIdentification is ok";
    }
    @RequestMapping(value = "updateArticle",method = RequestMethod.POST)
    public String updateArticle(String aId,String title, String digest, String cId, String identification,  @RequestParam("coverPhoto") MultipartFile file)  {
        int iaId = Integer.parseInt(aId);
        System.out.println("update aId :" + aId);

        String oldImgName = file.getOriginalFilename();
        String[] saveStr = {articleMapper.findCoverPhotoPath(iaId),articleMapper.findCoverPhotoUrl(iaId)};
        if (!file.isEmpty()){
            assert oldImgName != null;
            String[] str = oldImgName.split("\\.");

            String  fileName = identification+"."+str[1];
            fileService.deleteFileByPath(articleMapper.findCoverPhotoPath(Integer.parseInt(aId)));

            saveStr = fileService.saveArticleCoverPhoto(file,fileName);
        }



        System.out.println(saveStr[0].length());
        System.out.println("update info : aid " + aId + "--title: " + title + "---cId: " + "---coverPhotoPath: " + saveStr[0]
            + "---coverPhotoUrl: " + saveStr[1] + "---digest: " + digest);
        Article article = new Article(iaId,title,Integer.parseInt(cId),saveStr[0],saveStr[1],digest);
        System.out.println(article);

        articleMapper.updateArticle(article);
        return "manager/seDeUpArticle";
    }

    //2.2更新正文文件
    @RequestMapping(value = "/updateContentFile", method = RequestMethod.POST)
    @ResponseBody
    public void updateContentFile(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        fileService.saveContentAsHtmlFile(request.getInputStream().readAllBytes(),session.getAttribute("identification").toString());
    }

    /*四、设置推荐*/

    //4.1 aId检查
    @ResponseBody
    @RequestMapping("/checkAId")
    public String checkAId(HttpServletRequest request){
        int aId = Integer.parseInt(request.getParameter("aId"));
        if (articleMapper.checkAId(aId)>0){
            return "true";
        }
        else {
            return "false";
        }
    }

    @RequestMapping("/setHeadlines")
    public ModelAndView setHeadlines(String firstArticle, String secondArticle){
        int first = Integer.parseInt(firstArticle);
        int second = Integer.parseInt(secondArticle);
        articleMapper.setReArticle(1,first,1);
        articleMapper.setReArticle(2,second,2);
        ModelAndView modelAndView = new ModelAndView("manager/mRecommendation");
        modelAndView.addObject("operatingResult","Setting headline has finished");
        return modelAndView;
    }

    @RequestMapping("/setClassicSaying")
    public ModelAndView setClassicSaying(String content, String provenance){
        classicSayingMapper.setClassicSayingMapper(content,provenance);
        ModelAndView modelAndView = new ModelAndView("manager/mRecommendation");
        modelAndView.addObject("operatingResult","Setting classicSaying has finished");
        return modelAndView;
    }

    @RequestMapping("/setCReArticle")
    public ModelAndView setCReArticle(String reIndex,String aId){
        articleMapper.setReArticle(Integer.parseInt(reIndex),Integer.parseInt(aId),Integer.parseInt(reIndex));
        ModelAndView modelAndView = new ModelAndView("manager/mRecommendation");
        modelAndView.addObject("operatingResult","Setting cReArticle has finished");
        return modelAndView;
    }
}
