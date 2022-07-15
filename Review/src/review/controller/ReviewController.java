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
               // 현재는 CommandNull로 확인 한 후에 CommandExercise.java 만들기
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
                             cmd = (Command)commandMap.get( cmdKey); //레알 모르겠음.
                             										// commandMap.get( cmdKey)를 어떻게 Command 타입으로 형변환 할 수 있는지
                             										// 그럼 결국 cmd 안에는 무슨 값이 들어가는지.
                             System.out.println(cmd); // test
                      }else{
                             throw new CommandException("지정할 명령어가 존재하지 않음");
                      }
                      nextPage = cmd.execute(request);

               }catch( CommandException e ){
                      nextPage = error;
                      System.out.println("오류 : " + e.getMessage() );
               }
 
               // forwarding
               // <jsp:forward>
               RequestDispatcher reqDp = getServletContext().getRequestDispatcher( jspDir + nextPage );
               reqDp.forward( request, response );         
       }
}