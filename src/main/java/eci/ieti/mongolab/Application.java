package eci.ieti.mongolab;

import eci.ieti.mongolab.data.CustomerRepository;
import eci.ieti.mongolab.data.ProductRepository;
import eci.ieti.mongolab.data.TodoRepository;
import eci.ieti.mongolab.data.UserRepository;
import eci.ieti.mongolab.data.model.Customer;
import eci.ieti.mongolab.data.model.Product;
import eci.ieti.mongolab.data.model.Todo;
import eci.ieti.mongolab.data.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
   
        System.out.println();

        userRepository.deleteAll();
        userRepository.save(new User(12354L, "Charles Darwin", "charles@natural.com"));
        userRepository.save(new User(1L, "Ricardo Martinez", "ricardo@mail.com"));

        System.out.println("Users found with findAll():");
        System.out.println("-------------------------------");
        userRepository.findAll().stream().forEach(System.out::println);

        System.out.println();

        todoRepository.deleteAll();
        Date date = new Date();  
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); ;  
        String date1= dateFormat.format(date);
        todoRepository.save(new Todo("travel to Galapagos", 10, "10-01-1860", "charles@natural.com", "pending"));
        todoRepository.save(new Todo("add music to playlist", 2, date1, "ricardo@mail.com", "done"));

        System.out.println("Paginated search of todos by criteria:");
        System.out.println("-------------------------------");
        todoRepository.findByResponsible("charles@natural.com", PageRequest.of(0, 2))
        .stream().forEach(System.out::println);

    }

}