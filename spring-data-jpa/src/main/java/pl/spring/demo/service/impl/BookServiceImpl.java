package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.BookExemplarDao;
import pl.spring.demo.dao.CustomerDao;
import pl.spring.demo.dao.LoanDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.CustomerEntity;
import pl.spring.demo.entity.LoanEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.service.helper.CurrentDateProvider;
import pl.spring.demo.to.*;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CurrentDateProvider currentDateProvider;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private BookExemplarDao bookExemplarDao;

    @Override
    public List<BookTo> findBooks(BookSearchCriteriaTo searchCriteria) {
        List<BookEntity> books = bookDao.findBooks(searchCriteria);
        return bookMapper.mapSourceCollection(books);
    }

    @Override
    public BookLoanResultTo loanBook(BookLoanRequestTo bookLoanRequest) {
        boolean isBookExemplarBorrowed = bookExemplarDao.isBookExemplarBorrowed(bookLoanRequest.getBookExemplarId());
        if (isBookExemplarBorrowed) {
            return new BookLoanResultTo(BookLoanStatus.ALREADY_BORROWED);
        }

        Date currentDate = currentDateProvider.getCurrentDate();
        LoanEntity loan = new LoanEntity(currentDate);
        loan.setCustomerEntity(customerDao.getReference(bookLoanRequest.getCustomerId()));
        loan.getBookExemplars().add(bookExemplarDao.getReference(bookLoanRequest.getBookExemplarId()));
        loanDao.save(loan);

        return new BookLoanResultTo(BookLoanStatus.SUCCESS);
    }
}
