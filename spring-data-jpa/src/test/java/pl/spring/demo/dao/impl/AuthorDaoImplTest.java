package pl.spring.demo.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.dao.AuthorDao;
import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.ProfessorEntity;
import pl.spring.demo.service.impl.AbstractDatabaseTest;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AuthorDaoImplTest extends AbstractDatabaseTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testShouldFindAllAuthors() {
        // when
        List<AuthorEntity> allAuthors = authorDao.findAllAuthors(AuthorEntity.class);
        // then
        Assert.assertEquals(3, allAuthors.size());
    }

    @Test
    public void testShouldFindProfessorAuthors() {
        // when
        List<ProfessorEntity> allAuthors = authorDao.findAllAuthors(ProfessorEntity.class);
        // then
        Assert.assertEquals(1, allAuthors.size());
    }
}
