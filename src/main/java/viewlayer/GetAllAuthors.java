/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package viewlayer;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobjects.AuthorDTO;
import businesslayer.AuthorsBusinessLogic;

/**
 * This class as the special Servlet gets all authors from the Books database.
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
 * @see transferobjects.AuthorDTO
 * @see businesslayer.AuthorsBusinessLogic
 * @since JDK 21 (Default)
 */
public class GetAllAuthors extends HttpServlet {
    
    /**
     * Call a new AuthorsBusinessLogic instance
     */
    AuthorsBusinessLogic logic = new AuthorsBusinessLogic();
    
    /**
     * This is the default constructor for GetAllAuthors.
     */
    public GetAllAuthors() {
    
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<String[]> metaData = logic.getMetaData();
        
        List<AuthorDTO> list = logic.getAllAuthors();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>Enter DBMS Credentials</TITLE>");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#FDF5E6\">");
            out.println("<CENTER>");
            
            out.println("<H1>Get All Authors</H1>");
            
            out.println("<table border=\"1\">");
            out.println("<tr>");
            for (String[] strings : metaData) {
                out.printf("<th>%s</th>",
                    strings[0]);
            }
            out.println("</tr>");
            
            for (AuthorDTO author : list) {
                out.printf("<tr><td>%d</td><td>%s</td><td>%s</td></tr>",
                    author.getAuthorID(), 
                    author.getFirstName(), 
                    author.getLastName());
            }
            out.println("</table>");
            
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
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

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
