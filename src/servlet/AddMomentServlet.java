package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Dao.MomentDao;
import Dao.UserDao;
import entity.ResultInfo;
import util.UuidUtil;

/** 
* @author hts
* @version date：2017年11月6日 下午8:19:06 
* 
*/
public class AddMomentServlet extends HttpServlet{
	   MomentDao  monmentDao= new MomentDao();
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String username = request.getParameter("user_id");
	        String tweet_content = request.getParameter("tweets_content");
	        String tweet_id=UuidUtil.get32UUID();
	        Date now = new Date();	       
	        ResultInfo resultInfo =monmentDao.addMoments(tweet_id, username, now, tweet_content);
	        response.setCharacterEncoding("utf-8");
	        PrintWriter printWriter = response.getWriter();
            Gson gson=new Gson();
            String result=gson.toJson(resultInfo,ResultInfo.class);
	        printWriter.write(result);
	        printWriter.flush();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request,response);
	    }
}
