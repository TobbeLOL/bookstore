package com.lexicon.bookstore.repository;

import com.lexicon.bookstore.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional(Transactional.TxType.SUPPORTS)
public class BookRepistory {

    @PersistenceUnit(unitName = "bookStorePU")
    private EntityManager em;

    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("SELECT  count(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

    public Book findBook(Long id) {
        return em.find(Book.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteBook(Long id) {
        em.remove(em.getReference(Book.class, id));
    }
}
