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
public class HomeDAO {    
    DBContext db;

    public HomeDAO() {
        db = new DBContext();
    }

    // get top 5 recent articles by time desc
    public List<Article> getTop5RecentArticles() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> top5List = new ArrayList<>();
        try {
            con = db.getConnection();
            String query = "SELECT TOP 5 * FROM [Article] order by [date] desc";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            //add each article record in result set to list
            while (rs.next()) {
                String imgPath = db.getImgDir() + rs.getString("image");
                top5List.add(new Article(rs.getInt("id"), rs.getString("title"), imgPath,
                        rs.getString("content"), rs.getDate("date"), rs.getString("author")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
        return top5List;
    }

    public Article getArticleById(int id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            String query = "SELECT * FROM [Article] WHERE [id] = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String imgPath = db.getImgDir() + rs.getString("image");
                return new Article(rs.getInt("id"), rs.getString("title"), imgPath,
                        rs.getString("content"), rs.getDate("date"), rs.getString("author"));
            }
            return null;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(con, ps, rs);
        }
    }
}
