package viewlayer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businesslayer.AuthorsBusinessLogic;
import transferobjects.CredentialsDTO;

/**
 * This class as the front controller creates an initial login page 
 * to collect credentials needed to access the database.
 * It shows two text fields to users, allowing them to input their credentials.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.io.IOException
 * @see java.io.PrintWriter
 * @see javax.servlet.ServletException
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.http.HttpServletRequest
 * @see javax.servlet.http.HttpServletResponse
 * @see businesslayer.AuthorsBusinessLogic
 * @see transferobjects.CredentialsDTO
 * @since JDK 21 (Default)
 */
public class FrontController extends HttpServlet {
    
    /**
     * Call a new AuthorsBusinessLogic instance
     */
    AuthorsBusinessLogic logic = new AuthorsBusinessLogic();
    
    /**
     * Declare a CredentialsDTO
     */
    CredentialsDTO creds;
    
    /**
     * This is the default constructor for FrontController.
     */
    public FrontController() {
    
    }
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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<HTML>");
            out.println("<HEAD>");
            out.println("<TITLE>Enter DBMS Credentials</TITLE>");
            out.println("</HEAD>");
            out.println("<BODY BGCOLOR=\"#FDF5E6\">");
            out.println("<CENTER>");
            
            out.println("<H1>Enter DBMS Credentials</H1>");
            out.println("<FORM ACTION=\"" + request.getContextPath() + "/FrontController-URL\" METHOD=\"POST\">");
            
            out.println("<P>Username: <INPUT TYPE=\"TEXT\" NAME=\"username\"></P>");
            out.println("<P>Password: <INPUT TYPE=\"TEXT\" NAME=\"password\"></P>");
            
            out.println("<P>");
            out.println("<INPUT TYPE=\"SUBMIT\" NAME=\"action\" VALUE=\"GetAllAuthors\">");
            out.println("<INPUT TYPE=\"SUBMIT\" NAME=\"action\" VALUE=\"GetAuthorByAuthorId\">");
            out.println("<INPUT TYPE=\"SUBMIT\" NAME=\"action\" VALUE=\"AddAuthor\">");
            out.println("<INPUT TYPE=\"SUBMIT\" NAME=\"action\" VALUE=\"UpdateAuthorById\">");
            out.println("<INPUT TYPE=\"SUBMIT\" NAME=\"action\" VALUE=\"DeleteAuthorById\">");
            out.println("</P>");
            
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
        
        // instantiate a new CredentialsDTO to store username and password
        creds = new CredentialsDTO();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String action;
        
        creds.setUsername(username);
        creds.setPassword(password);
        
        boolean validCredential = logic.validateCredentials(creds);
        
        if (validCredential) {
            action = request.getParameter("action");
            switch (action) {
                case "GetAllAuthors":
                    request.getRequestDispatcher("/GetAllAuthors-URL").include(request, response);
                    break;  
                case "GetAuthorByAuthorId":
                    response.sendRedirect(request.getContextPath() + "/GetAuthorByAuthorId-URL");
                    break;
                case "AddAuthor":
                    response.sendRedirect(request.getContextPath() + "/AddAuthor-URL");
                    break;  
                case "UpdateAuthorById":
                    response.sendRedirect(request.getContextPath() + "/UpdateAuthorById-URL");
                    break;
                case "DeleteAuthorById":
                    response.sendRedirect(request.getContextPath() + "/DeleteAuthorById-URL");
                    break;
            }
        } else {
            request.getRequestDispatcher("/LoginError-URL").include(request, response);
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
