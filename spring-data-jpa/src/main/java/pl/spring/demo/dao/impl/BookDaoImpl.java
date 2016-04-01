package pl.spring.demo.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.Book;
import pl.spring.demo.to.BookSearchCriteriaTo;

@Repository
public class BookDaoImpl extends AbstractDao<Book, Long> implements BookDao {

    @Override
    public List<Book> findBooks(BookSearchCriteriaTo bookSearchCriteria) {
    	StringBuilder queryString = new StringBuilder("from Book b WHERE 1 = 1 ");
    	
//		
//		criteriaQuery.select(bookRoot);
//        
//        if (bookSearchCriteria != null) {
//
//            if (!StringUtils.isEmpty(bookSearchCriteria.getTitle())) {
//            	criteriaQuery.where(criteriaBuilder.equal(bookRoot.get("title"), bookSearchCriteria.getTitle()));
//            }
//            if (!StringUtils.isEmpty(bookSearchCriteria.getAuthor())) {
//            	author..append("AND b.authors like =:title ");
//                final String author = bookSearchCriteria.getAuthor();
//                predicate.and(bookEntity.authors.any().personalData.firstName.startsWithIgnoreCase(author)
//                        .or(bookEntity.authors.any().personalData.lastName.startsWithIgnoreCase(author)))
//                        .or(bookEntity.authors.any().nickName.startsWithIgnoreCase(author));
//            }
//            if (Boolean.TRUE.equals(bookSearchCriteria.getHasReview())) {
//            	queryString.append("AND b.bookReview IS NOT NULL ");
//            }
//        }
        Query query = entityManager.createQuery(queryString.toString(), Book.class);
        return query.getResultList();
    }
}
