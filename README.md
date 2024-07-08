# Check Clevertec

### Usage:
    java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx balanceDebitCard=xxxx  

### Input:
- id - product id by products.csv file
- quantity - product amount in shop basket
- discountCard=xxxx - discount cards number
- balanceDebitCard=xxxx - card balance for payment

### Output:
- result.csv file
- print custom check in console

#### Examples:  
    java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100
    java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 balanceDebitCard=1233
    java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 balanceDebitCard=-15

### Exception Handling:
- Invalid input pattern
- Searching for a product by incorrect ID
- Not enough money when paying
