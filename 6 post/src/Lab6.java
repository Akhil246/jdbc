import java.io.*;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Lab6 extends HttpServlet
{
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
           ServletContext sc = getServletContext();
     try
     {
      Class.forName(sc.getInitParameter("driver"));
      
      Connection con = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("user"), sc.getInitParameter("pwd"));
      System.out.println("Connection Established");
      String word= req.getParameter("word");
      PreparedStatement pstmt=con.prepareStatement("select * from keywords where keyword=?");
      pstmt.setString(1,word);
      ResultSet rs=pstmt.executeQuery();
   
      if(rs.next())
      {
          res.sendRedirect("http://www.google.com/search?q="+word+"");
      }
      else
      {
          out.println("Word not in database");
      }
     }
     catch(Exception e)
     {
        out.println(e);  
     }
       
    }
}