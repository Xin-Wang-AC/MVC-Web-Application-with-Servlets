package viewlayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import businesslayer.AuthorsBusinessLogic;
import transferobjects.AuthorDTO;

/**
 * This class as the special Servlet gets an author by its AuthorID from the Books database.
 * 
 * @author Stanley Pieda
 * @author Xin Wang
 * @version 1.0
 * @see java.io.IOException
 * @see java.io.PrintWriter
 * @see java.util.List
 * @see javax.servlet.ServletException
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.http.HttpServletRequest
 * @see javax.servlet.http.HttpServletResponse
 * @see businesslayer.AuthorsBusinessLogic
 * @see transferobjects.AuthorDTO
 * @since JDK 21 (Default)
 */
public class GetAuthorByAuthorId extends HttpServlet {

    /**
     * Call a new AuthorsBusinessLogic instance
     */
    AuthorsBusinessLogic logic = new AuthorsBusinessLogic();
    
    /**
     * Declare a AuthorDTO
     */
    AuthorDTO author;
    
    /**
     * This is the default constructor for GetAuthorByAuthorId.
     */
    public GetAuthorByAuthorId() {
    
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>Get An Author By AuthorID</TITLE>");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#FDF5E6\">");
            out.println("<CENTER>");
            
            out.println("<H1>Get An Author by AuthorID</H1>");
            
            out.println("<FORM ACTION=\"" + request.getContextPath() + "/GetAuthorByAuthorId-URL\" METHOD=\"POST\">");
            
            out.println("<P>AuthorID: <INPUT TYPE=\"TEXT\" NAME=\"authorID\"></P>");
            out.println("<INPUT TYPE=\"SUBMIT\">");
            
            out.println("</FORM>");
            
            out.println("</CENTER>");
            out.println("</BODY>");
            out.println("</HTML>");
        }
        
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Integer authorID = Integer.valueOf(request.getParameter("authorID"));
        List<String[]> metaData = logic.getMetaData();
        
        author = logic.getAuthor(authorID);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>Get An Author By AuthorID</TITLE>");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#FDF5E6\">");
            out.println("<CENTER>");
            
            out.println("<H1>Get An Author by AuthorID</H1>");
            
            if (author != null) {
                out.println("<table border=\"1\">");
                out.println("<tr>");
                for (String[] strings : metaData) {
                    out.printf("<th>%s</th>",
                        strings[0]);
                }
                out.println("</tr>");
            
                out.printf("<tr><td>%d</td><td>%s</td><td>%s</td></tr>",
                    author.getAuthorID(), 
                    author.getFirstName(), 
                    author.getLastName());
            
                out.println("</table>");
            } else {
                out.println("<P STYLE=\"color: red;\">Incorrect AuthorID, please try again.</P>");
            }
            
            out.println("<FORM ACTION=\"" + request.getContextPath() + "/FrontController-URL\" METHOD=\"GET\">");
            out.println("<INPUT TYPE=\"SUBMIT\" VALUE=\"Return\">");
            out.println("</FORM>");
            out.println("</CENTER>");
            out.println("</BODY>");
            out.println("</HTML>");
        }
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
