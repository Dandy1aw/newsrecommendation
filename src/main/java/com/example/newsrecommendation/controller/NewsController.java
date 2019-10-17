package com.example.newsrecommendation.controller;

import com.example.newsrecommendation.aop.annotation.Log;
import com.example.newsrecommendation.config.UserContext;
import com.example.newsrecommendation.entity.News;
import com.example.newsrecommendation.entity.User;
import com.example.newsrecommendation.intercepter.AcessLimiter;
import com.example.newsrecommendation.service.NewsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * @Author 11103882
 * @Date 2019/8/27
 * @Version 1.0
 */

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

/*	@RequestMapping("/index")
	public String showMainPage(Model model) {

		List<News> newsList = newsService.showAllNews();
		model.addAttribute("newsList", newsList);
		return "index";
	}*/

	@RequestMapping(value = "/newsDetail_{newsId}") // 前端传入的参数 newsId
	public String showDetailsById(Model model, @PathVariable("newsId") Integer newsId,User user) {
		News news = newsService.getById(newsId);
		model.addAttribute("news", news);
		model.addAttribute("user", user);
		return "detail";
	}


	/**
	 *        使用分页插件 默认请求形式：http://localhost:8080/getNewsList?pageNum=1&pageSize=2
	 * @param pageNum  请求的第几页
	 * @param pageSize   显示的数量
	 * @return
	 */

		@Log("访问列表")
		@RequestMapping(value = "/getNewsList",method= RequestMethod.GET)
		@AcessLimiter(clickLimit = 2,isLogin = true)
		public String getAllByPage(HttpServletRequest request,Model model,
								   @RequestParam(defaultValue = "1")Integer pageNum,
								   @RequestParam(defaultValue = "25")Integer pageSize,
								   User user){
		PageHelper.startPage(pageNum, pageSize);
		Page<News>  newsList= newsService.getNewsList();
		model.addAttribute("newsList",newsList);
		model.addAttribute("user",user);
		// 分页相关
		PageInfo<News> pageInfo = new PageInfo<News>(newsList);

		//获得当前页
		request.setAttribute("pageNum", pageInfo.getPageNum());
		//获得一页显示的条数
		request.setAttribute("pageSize", pageInfo.getPageSize());
		//是否是第一页
		request.setAttribute("isFirstPage", pageInfo.isIsFirstPage());
		//获得总页数
		request.setAttribute("totalPages", pageInfo.getPages());
		//是否是最后一页
		request.setAttribute("isLastPage", pageInfo.isIsLastPage());
		//所有导航页号
		request.setAttribute("naviPageNums", pageInfo.getNavigatepageNums());

		request.setAttribute("list", newsList);
		return "index";
	}


	@RequestMapping("/newsDetail")
	@AcessLimiter(clickLimit = 3,isLogin = true)
	public String showNewsDetail(@RequestParam("newsId") Integer newsId,Model model,User user){
		News news = newsService.getById(newsId);
		//每当用户点击该新闻看一次，给这个新闻的评分+1
		boolean ratingUpdated = newsService.updateRatingByUserClick(user.getId(),newsId);
		//如果没成功可以跑抛个异常，后续做
		// todo
		if (!ratingUpdated){
			throw new RuntimeException();
		}
		model.addAttribute("newsDetail",news);
		model.addAttribute("user",user);
		return "details";
	}

}
