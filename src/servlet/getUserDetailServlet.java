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

@WebServlet(name = "getUserDetailServlet")
public class getUserDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userListStr = request.getParameter("userList");
//        String userListStr = "[\"18689234486\",\"18600000003\"]";
        Gson gson=new Gson();
        UserDao userDao = new UserDao();
        String[] userList = gson.fromJson(userListStr, String[].class);
        System.out.println("usetList"+userList);
        List<User> userList1 = userDao.getUserDeatial(userList);
        String data = gson.toJson(userList1);
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
