package pl.spring.demo.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.AuthorDao;
import pl.spring.demo.entity.Author;
import pl.spring.demo.service.impl.AbstractDatabaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="CommonDaoTest-context.xml")
public class AuthorDaoImplTest extends AbstractDatabaseTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testShouldFindAllAuthors() {
        // when
        List<Author> allAuthors = authorDao.findAll();
        // then
        Assert.assertEquals(3, allAuthors.size());
    }
}
