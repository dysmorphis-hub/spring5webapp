package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AddressRepository;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner  {


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AddressRepository addressRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, AddressRepository addressRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "2134");

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "439493");

        Address address = new Address("testStreet", "vienna", "Vienna", "1234");
        Publisher publisher = new Publisher("John Doe", address);

        addressRepository.save(address);
        publisherRepository.save(publisher);

        eric.getBooks().add(book);
        book.getAuthors().add(eric);
        book.setPublisher(publisher);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);


        publisher.getBooksPublished().add(book);
        publisher.getBooksPublished().add(noEJB);

        authorRepository.save(eric);
        bookRepository.save(book);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        addressRepository.save(address);
        publisherRepository.save(publisher);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of books:" + bookRepository.count());
        System.out.println("\n");
        System.out.println("Publisher: " + publisher.getName() + " \n");
        System.out.println("Number of publishers saved:  " + publisherRepository.count());
        System.out.println("Number of books published:  " + publisher.getBooksPublished().size());


    }
}
