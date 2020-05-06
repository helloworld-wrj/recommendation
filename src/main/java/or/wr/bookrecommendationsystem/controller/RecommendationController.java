package or.wr.bookrecommendationsystem.controller;

import or.wr.bookrecommendationsystem.mapper.RecommendationMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RecommendationController {

    @Resource
    private RecommendationMapper recommendationMapper;

    @RequestMapping("/recommend/addArBrowseIndex")
    @ResponseBody
    public void handleArBrowseIndex(int aId){
        recommendationMapper.addArBrowseIndexByAid(aId);
    }


    @RequestMapping("/recommend/addCBrowseIndex")
    @ResponseBody
    public void handleCBrowseIndex(HttpServletRequest request){
        if (request.getSession().getAttribute("user")!=null){
            int cId = Integer.parseInt(request.getParameter("cId"));
//            System.out.println(cId);
            String username = request.getSession().getAttribute("user").toString();
//            System.out.println(username);
            recommendationMapper.addCBrowseIndex(username,cId);
        }
    }
}
