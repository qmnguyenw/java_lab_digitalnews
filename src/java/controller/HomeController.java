/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Article;
import exception.MyException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.HomeDAO;

/**
 *
 * @author Admin
 */
public class HomeController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String id = request.getParameter("id");
            HomeDAO dao = new HomeDAO();
            List<Article> last5List = dao.getTop5RecentArticles();
            Article displayArticle = null;
            //if user click an article to read it => id != null, get the selected news
            if (id != null) {
                try {
                    displayArticle = dao.getArticleById(Integer.parseInt(id));
                    //if user enter not existed id on url, handle the exception
                    if (displayArticle == null) throw new NumberFormatException();
                }catch(NumberFormatException e) {
                    //define not existed id exception
                    MyException notExistIdException = new MyException("The article is not existed");
                    //throw to outer catch to continue handle
                    throw notExistIdException;
                }
            //if user go to home page, get the most recent news
            }else {
                displayArticle = last5List.get(0);
            }
            
            request.setAttribute("last5List", last5List);
            request.setAttribute("lastArticle", last5List.get(0));
            request.setAttribute("displayArticle", displayArticle);
        }catch(Exception e) {
            e.printStackTrace();
            MyException myEx = new MyException(e);
            request.setAttribute("exception", myEx);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
