package servlet;

import Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddNewFriendServlet")
public class AddNewFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user1 = request.getParameter("hostId");
        String user2 = request.getParameter("friendId");
        UserDao userDao = new UserDao();
        String statusCode = userDao.addNewFriend(user1,user2);
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        System.out.println(statusCode);
        printWriter.write(statusCode);
        printWriter.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
