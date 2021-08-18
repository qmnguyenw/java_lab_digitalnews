/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HomeDAO;
import dao.SearchDAO;
import entity.Article;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SearchController extends HttpServlet {

    private int[] getPageFromTo(int currentPage, int totalNumOfPage) {
        int numberArticleInPage = 3;
        int pageTo,pageFrom;
        pageFrom = currentPage;
        pageTo = currentPage + numberArticleInPage - 1;
        if (pageTo > totalNumOfPage) {
            pageTo = totalNumOfPage;
            pageFrom = pageTo - numberArticleInPage + 1;
        }
        if (pageFrom < 1) {
            pageFrom = 1;
        }
        return new int[]{pageFrom,pageTo};
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
        response.setContentType("text/html;charset=UTF-8");
        SearchDAO dao = new SearchDAO();
        try {
            String keyword = request.getParameter("keyword");
            String currentPageStr = request.getParameter("currentPage");
            if (keyword != null && !keyword.isEmpty()) {
                int currentPage;
                //user click search
                if (currentPageStr == null) {
                    currentPage = 1;
                //user click on page number
                } else if (!currentPageStr.matches("\\d+")) {
                    currentPage = 0;
                } else {
                    currentPage = Integer.parseInt(currentPageStr);
                }

                List<Article> searchList = dao.getSearchListByPageNumber(currentPage, keyword);
                int numOfPage = dao.getNumberOfPage(keyword);
                
                int[] pageFromTo = getPageFromTo(currentPage, numOfPage);
                
                request.setAttribute("searchList", searchList);
                request.setAttribute("numOfPage", numOfPage);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("pageFrom", pageFromTo[0]);
                request.setAttribute("pageTo", pageFromTo[1]);
                request.setAttribute("keyword", keyword);
                               
            }
            HomeDAO homedao = new HomeDAO();
            List<Article> last5List = homedao.getTop5RecentArticles();
            request.setAttribute("last5List", last5List);
            request.setAttribute("lastArticle", last5List.get(0));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("search.jsp").forward(request, response);
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
