

- How many customers were created in the database? 

    5
    
- Where is the findAll method implemented?

    In the customerRepository after save all new customers.

- Suppose you have more than 1000 products in your database. How would you implement a method for supporting pagination and return pages of 50 products to your frontend?

    By consulting them all and returning them in a list of lists of 50 documents each one.

- How many products contain the "plus" word in their description?

    4

- How many products are returned by the findByDescriptionContaining query? Why?

    2, because the page in which the elements are grouped has a size of 2.

- Which are the collection names where the objects are stored? Where are those names assigned?

    The names are customer and product.

    Nowhere are those names assigned, I assume they have those names because the model classes and nowhere is specified any name for the collections.


8. Implement the queries of the previous step using *derived query methods* in your repository interface. Is it possible to implement all of them?

Users that have assigned more than 2 Todos query is not possible to implement using derived query methods because it needs information from both collections ( user and todo ).