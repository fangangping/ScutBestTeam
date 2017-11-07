package servlet;

import Dao.UserDao;
import com.google.gson.Gson;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "servlet.GetFriendServlet")
public class GetFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        System.out.println(userName);
        UserDao userDao = new UserDao();
        List<User> userList = userDao.getUserFriend(userName);
        Gson gson = new Gson();
        String data = gson.toJson(userList);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        System.out.println(data);
        printWriter.write(data);
        printWriter.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
