package pl.spring.demo.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.Book;
import pl.spring.demo.service.impl.AbstractDatabaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="CommonDaoTest-context.xml")
public class BookDaoImplTest extends AbstractDatabaseTest{
	@Autowired
    private BookDao bookDao;

    @Test
    public void shouldFindAllAuthors() {
        // when
        List<Book> allAuthors = bookDao.findAll();
        // then
        Assert.assertEquals(8, allAuthors.size());
    }
}
