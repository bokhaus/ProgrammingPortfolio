REM QUERY 1. CONCAT firstname and lastnamewith space
SELECT lastname || ' ' || firstname AS query1
FROM customer
ORDER BY lastname;

---------------------------
REM Query 2. List all orders with date ordered output
SELECT orderid, TO_CHAR(orderdate, 'MONTH DD,YYYY HH:MI:SS')maskdate
FROM order_customer
ORDER BY orderdate ASC;

---------------------------
REM Query 3. list of all products using the INITCAP on the product name
SELECT productname, INITCAP(productname) initcap_productname
FROM product;

---------------------------
REM Query 4. list of all orders that have been ordered in the current month
SELECT orderid, customerid, orderdate
FROM order_customer
WHERE orderdate BETWEEN TO_DATE('01-JUL-18', 'DD-MM-RRRR')
AND TO_DATE('31-JUL-18', 'DD-MM-RRRR');

---------------------------
REM Query 5. list of products with their prices rounded to the nearest dollar
SELECT productname, unitprice, ROUND(unitprice)
FROM product;

---------------------------
REM Query 6. list of the total price of each line item based on the price and quantity
SELECT orderdetailid, price, quantity, (price*quantity) tot_lineitem
FROM order_detail;

---------------------------
REM QUERY 7. list of all customers with the first name that starts with a specific letter (T)
SELECT firstname
FROM customer
WHERE firstname LIKE 'T%';

---------------------------
REM Query 8. list of products with the cost more than $10
SELECT productname, unitprice
FROM product
WHERE unitprice >10;

---------------------------
REM Query 9. list of all of the customers with their only the first 4 characters with the last 4 padded with the * character
SELECT RPAD(firstname || lastname, 4) ||'****' query9_rpad
FROM customer;

---------------------------
REM Quey 10. list of products with the picture path removing the full path and only including the filename.
SELECT SUBSTR(picture,34) query10_image_substring
FROM product;

---------------------------ADDITIONAL QUERIES--------------------------- 

REM Query 11. list of all customers with first name abbreviated and concat with last name add address and city
SELECT RPAD(firstname, 1)|| '.' ||lastname AS firstname_abbreviated, address, city
FROM customer;

--------------------------- 
REM Query 12. NUMERIC QUERY truncate decimal point. no rounding of number
SELECT TRUNC(price,2) "Truncated Price" 
FROM order_detail;


---------------------------
REM Query 13. calculate days between orderdate and shipdate
SELECT orderdate, shipdate, 
(TO_DATE (shipdate,'dd-MM-yyyy') - TO_DATE(orderdate,'dd-MM-yyyy')) "Days to Ship"
FROM order_customer;

---------------------------
REM Query 14. Converstion function showing productid and unit price formatted to currency and two decimal places
SELECT productid, TO_CHAR(price,'L99G999D99MI') "Amount"
FROM order_detail;

---------------------------
REM Query 15 replace null value with n/a for phone number
SELECT  firstname,lastname, NVL(phone, 'n/a')Phone
FROM customer;

---------------------------END---------------------------

