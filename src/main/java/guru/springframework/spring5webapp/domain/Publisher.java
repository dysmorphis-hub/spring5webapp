package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne
    private Address address;

    @OneToMany
    @JoinColumn(name = "publisher_id")
    private Set<Book> booksPublished = new HashSet<>();


    public Publisher() {
    }

    public Publisher(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Set<Book> getBooksPublished() {
        return this.booksPublished;
    }

    public void setBooksPublished(Set<Book> booksPublished) {
        this.booksPublished = booksPublished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        return id != null ? id.equals(publisher.id) : publisher.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
