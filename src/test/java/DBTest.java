import com.example.webcatalog2.Model.Article;
import com.example.webcatalog2.db.DbHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBTest {
    public DBTest() {
    }

    @Test
    public void getUser() throws SQLException {
        DbHandler handler = new DbHandler();
        handler.createTable();
        handler.deleteAll();
        Article expected = new Article("t", "a", "bb", "f");
        int i = handler.addArticle(expected);
        Article actual = handler.getArticle("" + i);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void search1() throws SQLException {
        DbHandler handler = new DbHandler();
        handler.createTable();
        handler.deleteAll();
        ArrayList<Article> expected = new ArrayList<>();
        expected.add(new Article("t", "a", "bb", "f"));
        int i = handler.addArticle(expected.get(0));
        ArrayList<Article> actual = handler.getAllArticles("t", "a", "bb", "f");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void search2() throws SQLException {
        DbHandler handler = new DbHandler();
        handler.createTable();
        handler.deleteAll();
        ArrayList<Article> expected = new ArrayList<>();
        expected.add(new Article("t", "a", "b", "f"));
        int i = handler.addArticle(expected.get(0));
        ArrayList<Article> actual = handler.getAllArticles("t", "a", "bb", "f");
        Assertions.assertNotEquals(expected, actual);
    }
    @Test
    public void search3() throws SQLException {
        DbHandler handler = new DbHandler();
        handler.createTable();
        handler.deleteAll();
        ArrayList<Article> expected = new ArrayList<>();
        expected.add(new Article("t", "a", "b aaa g", "f"));
        expected.add(new Article("t", "a", "aaa 90", "f"));
        expected.add(new Article("t", "a", "777777 aaa 9h0 t", "f"));

        handler.addArticle(expected.get(0));
        handler.addArticle(expected.get(1));
        handler.addArticle(expected.get(2));
        handler.addArticle(new Article("t", "a", "7 t", "f"));
        ArrayList<Article> actual = handler.getAllArticles("all", "all", "aaa", "all");

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void search5() throws SQLException {
        DbHandler handler = new DbHandler();
        handler.createTable();
        handler.deleteAll();
        ArrayList<Article> expected = new ArrayList<>();
        expected.add(new Article("t", "a", "b aaa g", "f"));
        expected.add(new Article("t", "a", "aaa 90", "f"));
        expected.add(new Article("t", "a", "777777 aaa 9h0 t", "f"));

        handler.addArticle(expected.get(0));
        handler.addArticle(expected.get(1));
        handler.addArticle(expected.get(2));
        handler.addArticle(new Article("t", "a", "7 t", "f"));
        ArrayList<Article> actual = handler.getAllArticles("all", "all", "aaay", "all");

        Assertions.assertNotEquals(expected, actual);
    }
}
