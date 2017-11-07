package servlet;

import Dao.MomentDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetCommentServlet")
public class GetCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String friendId = request.getParameter("friendId");
        System.out.println("friendID" + friendId);
        Gson gson = new Gson();
        MomentDao momentDao = new MomentDao();
        String data =gson.toJson(momentDao.getMoments(friendId));
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        System.out.println(data);
        printWriter.write(data);
        printWriter.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
