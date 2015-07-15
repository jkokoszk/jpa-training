package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.BookExemplarDao;
import pl.spring.demo.dao.CustomerDao;
import pl.spring.demo.dao.LoanDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.BookExemplarEntity;
import pl.spring.demo.entity.CustomerEntity;
import pl.spring.demo.entity.LoanEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.service.helper.CurrentDateProvider;
import pl.spring.demo.to.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final CurrentDateProvider currentDateProvider;
    private final BookDao bookDao;
    private final CustomerDao customerDao;
    private final LoanDao loanDao;
    private final BookExemplarDao bookExemplarDao;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, CurrentDateProvider currentDateProvider, BookDao bookDao, CustomerDao customerDao,
                           LoanDao loanDao, BookExemplarDao bookExemplarDao) {
        this.bookMapper = bookMapper;
        this.currentDateProvider = currentDateProvider;
        this.bookDao = bookDao;
        this.customerDao = customerDao;
        this.loanDao = loanDao;
        this.bookExemplarDao = bookExemplarDao;
    }

    @Override
    public BookTo findBookById(long bookId) {
        return bookMapper.mapSource(bookDao.find(bookId));
    }

    @Override
    public List<BookTo> findBooks(BookSearchCriteriaTo searchCriteria) {
        List<BookEntity> books = bookDao.findBooks(searchCriteria);
        return new ArrayList<>(bookMapper.mapSourceCollection(books));
    }

    @Override
    @Transactional(readOnly = false)
    public BookLoanResultTo loanBook(BookLoanRequestTo bookLoanRequest) {
        boolean isBookExemplarBorrowed = bookExemplarDao.isBookExemplarBorrowed(bookLoanRequest.getBookExemplarId());
        if (isBookExemplarBorrowed) {
            return new BookLoanResultTo(BookLoanStatus.ALREADY_BORROWED);
        }

        Date currentDate = currentDateProvider.getCurrentDate();
        LoanEntity loan = new LoanEntity(currentDate);
        CustomerEntity customerEntity = customerDao.getOne(bookLoanRequest.getCustomerId());
        loan.setCustomerEntity(customerEntity);
        BookExemplarEntity bookExemplarEntity = bookExemplarDao.getOne(bookLoanRequest.getBookExemplarId());
        loan.addBookExemplar(bookExemplarEntity);
        loanDao.save(loan);

        return new BookLoanResultTo(BookLoanStatus.SUCCESS);
    }

    @Override
    @Transactional(readOnly = false)
    public BookTo createBook(NewBookTo bookToSave) {
        BookEntity bookEntity = bookMapper.mapNewBook(bookToSave);
        bookEntity = bookDao.save(bookEntity);
        return bookMapper.mapSource(bookEntity);
    }
}
