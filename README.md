# Check Clevertec

### Usage:
    java -jar check.jar 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 saveToFile=result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=postgres  

### Input:
- id - product id by products.csv file
- quantity - product amount in shop basket
- discountCard=xxxx - discount cards number
- balanceDebitCard=xxxx - card balance for payment
- saveToFile={path} - result file for check (or errors) writing
- datasource.* - database config data

### Output:
- result.csv file or input_file_name or error_result.csv
- print custom check in console

#### Examples:  
    java -jar check.jar 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 saveToFile=./result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=postgres

### Exception Handling:
- Invalid input pattern
- Searching for a product by incorrect ID
- Not enough money when paying
