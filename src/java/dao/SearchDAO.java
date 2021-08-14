/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.DBContext;
import entity.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class SearchDAO {
    DBContext db;
    int numberArticleInPage;
    
    public SearchDAO() {
        db = new DBContext();
        numberArticleInPage = 3;
    }

    //get search list item by page number
    public List<Article> getSearchListByPageNumber(int currentPage, String keyword) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = db.getConnection();
            List<Article> searchList = new ArrayList<>();
            //get row number of all item but between item start and item end of current page
            String query = "SELECT * FROM (\n"
                    + "  SELECT ROW_NUMBER()\n"
                    + "  OVER(ORDER BY [date] desc) as RowNumber,\n"
                    + "  * FROM Article \n"
                    + "  WHERE content LIKE ? OR title LIKE ? \n"
                    + "  ) as AllData where AllData.RowNumber between ? and ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            //calculate row numbers of articles in going page
            int rowNumTo = currentPage * numberArticleInPage;
            int rowNumFrom = rowNumTo - numberArticleInPage + 1;
            ps.setInt(3, rowNumFrom);
            ps.setInt(4, rowNumTo);

            rs = ps.executeQuery();
            while (rs.next()) {
                String imgPath = db.getImgDir() + rs.getString("image");
                searchList.add(new Article(rs.getInt("id"), rs.getString("title"), imgPath,
                        rs.getString("content"), rs.getDate("date"), rs.getString("author")));
            }
            return searchList;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
    }

    // get number of search page 
    public int getNumberOfPage(String keyword) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            //get number of article
            String query = "SELECT COUNT(*) FROM Article\n"
                    + "	WHERE [content] LIKE ?\n"
                    + "	OR [title] LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%"+keyword+"%");
            ps.setString(2, "%"+keyword+"%");
            
            rs = ps.executeQuery();
            //get number search page by number of article in page
            if (rs.next()) {
                //return number of page
                return (int)Math.ceil(rs.getDouble(1)/numberArticleInPage);
            }
            return 0;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
    }
}
