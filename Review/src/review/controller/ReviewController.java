package review.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inter.Command;
import inter.CommandCateList;
import inter.CommandException;
import inter.CommandNull;

public class ReviewController extends HttpServlet{
      
       private HashMap commandMap;
       private String jspDir = "/review/";
       private String  error = "error.jsp";

       public ReviewController() {
               initCommand();
       }
      
       private void initCommand(){
               commandMap = new HashMap();
               commandMap.put("start-page", new CommandNull("start.jsp"));
               commandMap.put("list-page", new CommandCateList("review.jsp"));
               // ����� CommandNull�� Ȯ�� �� �Ŀ� CommandExercise.java �����
               commandMap.put("exercise", new CommandNull("exercise.jsp"));
               commandMap.put("exercise2", new CommandNull("exercise2.jsp"));
       }
       
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               processRequest(request, response);
       }
        
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               processRequest(request, response);
       }

       private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               request.setCharacterEncoding("utf-8");

               String nextPage = "";
               String cmdKey  = request.getParameter("cmd");
               if( cmdKey == null ){
                      cmdKey = "start-page";
               }
             
               Command cmd = null;

               try{                  
                      if( commandMap.containsKey( cmdKey) ){
                             cmd = (Command)commandMap.get( cmdKey); //���� �𸣰���.
                             										// commandMap.get( cmdKey)�� ��� Command Ÿ������ ����ȯ �� �� �ִ���
                             										// �׷� �ᱹ cmd �ȿ��� ���� ���� ������.
                             System.out.println(cmd); // test
                      }else{
                             throw new CommandException("������ ��ɾ �������� ����");
                      }
                      nextPage = cmd.execute(request);

               }catch( CommandException e ){
                      nextPage = error;
                      System.out.println("���� : " + e.getMessage() );
               }
 
               // forwarding
               // <jsp:forward>
               RequestDispatcher reqDp = getServletContext().getRequestDispatcher( jspDir + nextPage );
               reqDp.forward( request, response );         
       }
}