package or.wr.bookrecommendationsystem.controller;



import or.wr.bookrecommendationsystem.mapper.ArticleMapper;
import or.wr.bookrecommendationsystem.mapper.BookMapper;
import or.wr.bookrecommendationsystem.mapper.ClassicSayingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.*;


@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ClassicSayingMapper classicSayingMapper;

    @Resource
    private BookMapper bookMapper;

    @org.junit.Test
    public void test(){


    }

    @RequestMapping("/test")
    @ResponseBody
    public String toTest() {

        return "change";
    }

    @ResponseBody
    @RequestMapping("/test00")
    public Map test00(String page, String limit){
        System.out.println(page+"==="+limit);
        Map map = new HashMap();
        map.put("id",1);
        map.put("name","kiki");
        map.put("author","kiki");
        map.put("publication","人民出版社");
        map.put("publicationDate","2019-12-17");

        Map map2 = new HashMap();
        map2.put("id",2);
        map2.put("name","我与地坛");
        map2.put("author","史铁生");
        map2.put("publication","人民出版社");
        map2.put("publicationDate","2019-12-18");

        Map map3 = new HashMap();
        map3.put("id",3);
        map3.put("name","软件工程");
        map3.put("author","saf");
        map3.put("publication","人民出版社");
        map3.put("publicationDate","2019-12-11");

        List list = new ArrayList();
        list.add(map);
        list.add(map2);
        list.add(map3);
        list.add(map);
        list.add(map2);
        list.add(map3);
        list.add(map);
        list.add(map2);
        list.add(map3);
        list.add(map);
        list.add(map2);
        list.add(map3);
        list.add(map);
        list.add(map2);
        list.add(map3);


        Map map1 = new HashMap();
        map1.put("code", 0);
        map1.put("count",15);
        map1.put("data",list);



        return map1;

    }

    @RequestMapping("/test01")
    public String  toTest01(){
        logger.trace("this is trace");
        logger.debug("this is debug");
        logger.info("this is infor");
    /*    logger.warn("this is warm");
        logger.error("this is error");*/
        return "test/test01";
    }

    @RequestMapping(value = "/test01AddBooks",method = RequestMethod.POST)
    @ResponseBody
    public String test01AddBooks(HttpServletRequest request,@RequestParam("coverPhotos") MultipartFile[] file,String books){
        /*JSONArray jsonObject = JSON.parseArray(books);
        System.out.println(jsonObject);
        System.out.println(jsonObject.size());
        for (int i = 0; i < jsonObject.size(); i++) {
            System.out.println(JSON.toJavaObject((JSON) jsonObject.get(i),Book.class).toString());
        }
        System.out.println(file.length);*/
        return "ok";
    }
    @RequestMapping("/test02")
    public String  toTest02(){
        return "test/test02";
    }
    @RequestMapping("/upload")
    public String  upload(@RequestParam("files") MultipartFile[] file,String username,String arrays){
        System.out.println(username);
        System.out.println(arrays);
        System.out.println(file[0].getSize());
        return "test/test01";
    }



    @RequestMapping("/setRedis")
    @ResponseBody
    public String setRedis(){
        /*System.out.println(redisTemplate.hasKey("testKey"));
        redisTemplate.opsForValue().set("testKey",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(1)));
        Article article = (Article) redisTemplate.opsForValue().get("testKey");
        System.out.println(article);
       *//* Map map = new HashMap();
        redisTemplate.opsForValue().set("test01",new byte[1024*1024]);
        redisTemplate.opsForStream().add("testStreamKey",map);*/


        redisTemplate.opsForValue().set("first", articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(1)));

        redisTemplate.opsForValue().set("second",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(2)));

        redisTemplate.opsForValue().set("third",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(3)));

        redisTemplate.opsForValue().set("fourth",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(4)));

        redisTemplate.opsForValue().set("fifth",articleMapper.selectToUpdate(articleMapper.findAIdFromReArticle(5)));

        redisTemplate.opsForValue().set("classicSaying",classicSayingMapper.findClassicSaying());

        redisTemplate.opsForValue().set("latestBook",bookMapper.findLatestBook());

        return "ok";
    }


    @RequestMapping("/getRedis")
    public ModelAndView getRedis(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("first",redisTemplate.opsForValue().get("first"));
        modelAndView.addObject("second",redisTemplate.opsForValue().get("second"));
        modelAndView.addObject("third",redisTemplate.opsForValue().get("third"));
        modelAndView.addObject("fourth",redisTemplate.opsForValue().get("fourth"));
        modelAndView.addObject("fifth",redisTemplate.opsForValue().get("fifth"));
        modelAndView.addObject("classicSaying",redisTemplate.opsForValue().get("classicSaying"));
        modelAndView.addObject("latestBook",redisTemplate.opsForValue().get("latestBook"));


        return modelAndView;

    }




}
