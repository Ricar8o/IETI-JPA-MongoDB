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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

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
        populateUsers();

        System.out.println("Users found with findAll():");
        System.out.println("--------------------------------------------------");
        userRepository.findAll().stream().forEach(System.out::println);

        System.out.println();

        todoRepository.deleteAll();
        populateTodos();

        System.out.println("Paginated search of todos by criteria:");
        System.out.println("--------------------------------------------------");
        todoRepository.findByResponsible("charles@natural.com", PageRequest.of(0, 2))
        .stream().forEach(System.out::println);

        System.out.println();

        runQueries(applicationContext);

        // Close application
        ((AbstractApplicationContext) applicationContext).close();
    }

    private void populateUsers() {
        userRepository.save(new User(12354L, "Charles Darwin", "charles@natural.com"));
        userRepository.save(new User(1L, "Ricardo Martinez", "ricardo@mail.com"));
        userRepository.save(new User(2L, "Mauro Torres", "mauro@mail.com"));
        userRepository.save(new User(3L, "Daniel Torres", "daniel@mail.com"));
        userRepository.save(new User(4L, "Laura Sanabria", "laura@mail.com"));
        userRepository.save(new User(5L, "James Barnes", "james@winter.com"));
        userRepository.save(new User(6L, "Stephen King", "stephen@summoner.com"));
        userRepository.save(new User(7L, "Julio Verne", "verne@travel.com"));
        userRepository.save(new User(8L, "Isaac Asimov", "isaac@robots.com"));
        userRepository.save(new User(9L, "Stephen Hawking", "stephen@physics.com"));
    }
    

    private void populateTodos() throws Exception{

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        todoRepository.save(new Todo("Travel to Galapagos", 10, dateFormat.parse("10-01-1860") , "charles@natural.com", "pending"));
        todoRepository.save(new Todo("Add music to playlist", 2, dateFormat.parse("28-03-2021") , "ricardo@mail.com", "in progress"));
        todoRepository.save(new Todo("Play Crash Bandicoot", 6, dateFormat.parse("30-04-2021"), "mauro@mail.com" , "in progress"));
        todoRepository.save(new Todo("Eat cake", 9 , dateFormat.parse("19-02-2021"), "daniel@mail.com", "done"));
        todoRepository.save(new Todo("Join a gym", 7 , dateFormat.parse("02-04-2021"), "laura@mail.com", "pending"));
        todoRepository.save(new Todo("Meet with steve", 5, dateFormat.parse("13-03-2014"), "james@winter.com", "done"));
        todoRepository.save(new Todo("Finish writing Billy Summers", 8, dateFormat.parse("03-08-2021"), "stephen@summoner.com", "in progress"));
        todoRepository.save(new Todo("Find the mysterious island", 7, dateFormat.parse("22-11-1875"), "verne@travel.com", "pending"));
        todoRepository.save(new Todo("Create three Laws of Robotics", 6, dateFormat.parse("02-12-1950"), "isaac@robots.com", "done"));
        todoRepository.save(new Todo("Publish Properties of Expanding Universes", 10, dateFormat.parse("27-10-2017"), "stephen@physics.com", "done"));
        todoRepository.save(new Todo("Travel to space", 4, dateFormat.parse("14-03-2018"), "stephen@physics.com", "pending"));
        todoRepository.save(new Todo("Publish IT", 5, dateFormat.parse("15-09-1986"), "stephen@summoner.com", "done"));
        todoRepository.save(new Todo("Take a nap", 10, dateFormat.parse("10-01-1875") , "charles@natural.com", "done"));
        todoRepository.save(new Todo("Do the economy homework", 8, dateFormat.parse("04-04-2021"), "mauro@mail.com" , "pending"));
        todoRepository.save(new Todo("Do the statistics homework", 5, dateFormat.parse("07-04-2021"), "mauro@mail.com" , "pending"));
        todoRepository.save(new Todo("Buy new headphones", 8 , dateFormat.parse("02-04-2021"), "daniel@mail.com", "in progress"));
        todoRepository.save(new Todo("Buy new clothes", 6 , dateFormat.parse("17-04-2021"), "daniel@mail.com", "pending"));
        todoRepository.save(new Todo("Completion of the ieti mongo lab", 7, dateFormat.parse("30-03-2021") , "ricardo@mail.com", "done"));
        todoRepository.save(new Todo("Finish the backend of the ieti project for the sprint review", 9, dateFormat.parse("08-04-2021") , "ricardo@mail.com", "pending"));
        todoRepository.save(new Todo("Finish reading the books", 3, dateFormat.parse("25-08-2021") , "ricardo@mail.com", "in progress"));
        todoRepository.save(new Todo("Go to therapy with the doctor every Tuesday", 6, dateFormat.parse("27-04-2021"), "james@winter.com", "in progress"));
        todoRepository.save(new Todo("Write The Dark Tower: The Gunslinger", 10, dateFormat.parse("10-06-1982"), "stephen@summoner.com", "done"));
        todoRepository.save(new Todo("Travel to Scotland with Hignard", 8, dateFormat.parse("19-12-1859"), "verne@travel.com", "pending"));
        todoRepository.save(new Todo("Do the pedagogy homework", 9 , dateFormat.parse("05-04-2021"), "laura@mail.com", "pending"));
        todoRepository.save(new Todo("Do the math homework", 9 , dateFormat.parse("07-04-2021"), "laura@mail.com", "in progress"));
    }   

    private void runQueries(ApplicationContext applicationContext) {
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");
        System.out.println(customerQuery(mongoOperation));
        System.out.println();

        List<Todo> expiredTodos = expiredTodosQuery(mongoOperation);
        System.out.println("Expired Todos Query:");
        System.out.println("--------------------------------------------------");
        expiredTodos.stream().forEach(System.out::println);
        System.out.println();
        
        String responsible = "ricardo@mail.com";
        int priority = 5;
        List<Todo> todosUserPrioritygte =  todosUserPrioritygteQuery(responsible, priority, mongoOperation);
        System.out.println(String.format( "User: %s & priority greater or equal than %s Todos Query: ", responsible, priority));
        System.out.println("--------------------------------------------------");
        todosUserPrioritygte.stream().forEach(System.out::println);
        System.out.println();
        
        int num = 2;
        System.out.println(String.format( "Users with more than %s Todos: ", num));
        System.out.println("--------------------------------------------------");
        List<User> usersWithTodos = usersAssignedTodos(num, mongoOperation);
        usersWithTodos.stream().forEach(System.out::println);
        System.out.println();

        int length = 30;
        List<Todo> todosDescriptionLengthgt =  todosDescriptionLengthgtQuery(length ,mongoOperation);
        System.out.println(String.format( "Todos Description with a length greater than %s Query: ", length));
        System.out.println("--------------------------------------------------");
        todosDescriptionLengthgt.stream().forEach(System.out::println);
        System.out.println();

        
    }

    private Customer customerQuery(MongoOperations mongoOperation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Alice"));
        return mongoOperation.findOne(query, Customer.class);
    }

    private List<Todo> expiredTodosQuery(MongoOperations mongoOperation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("dueDate").lt(new Date()));
        return mongoOperation.find(query, Todo.class);
    }

    private List<Todo> todosUserPrioritygteQuery(String res, int pri, MongoOperations mongoOperation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("responsible").is(res).and("priority").gte(pri));
        return mongoOperation.find(query, Todo.class);
    }

    private List<User> usersAssignedTodos(int num, MongoOperations mongoOperation) {
        GroupOperation groupOperation = Aggregation.group("responsible").count().as("count");
        MatchOperation matchOperation = Aggregation.match(Criteria.where("count").gt(num));
        Aggregation agg = Aggregation.newAggregation(groupOperation, matchOperation);
        AggregationResults<Document> result = mongoOperation.aggregate(agg, "todo", Document.class);
        
        List<String> users = new ArrayList<String>();

        for (Document doc : result.getMappedResults()){
            String email = (String) doc.get("_id");
            users.add(email);
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("email").in(users));

        return mongoOperation.find(query, User.class);

    }

    private List<Todo> todosDescriptionLengthgtQuery(int length, MongoOperations mongoOperation) {
        Query query = new Query();
        String string = String.format("^.{%s,}$", length+1);
        query.addCriteria(Criteria.where("description").regex(string));
        return mongoOperation.find(query, Todo.class);
    }

}