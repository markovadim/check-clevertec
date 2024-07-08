# Check Clevertec

### Usage:
    java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx balanceDebitCard=xxxx pathToFile={path} saveToFile={path}  

### Input:
- id - product id by products.csv file
- quantity - product amount in shop basket
- discountCard=xxxx - discount cards number
- balanceDebitCard=xxxx - card balance for payment

### Output:
- result.csv file
- print custom check in console

#### Examples:  
    java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 pathToFile=test-products.csv
    java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 balanceDebitCard=1233 pathToFile=test-products.csv saveToFile=test-result.csv

### Exception Handling:
- Invalid input pattern
- Searching for a product by incorrect ID
- Not enough money when paying
