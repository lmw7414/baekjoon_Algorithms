SELECT A.PRODUCT_CODE, SUM(A.PRICE * B.SALES_AMOUNT) as SALES
FROM PRODUCT A JOIN OFFLINE_SALE B ON A.PRODUCT_ID = B.PRODUCT_ID 
GROUP BY A.PRODUCT_CODE
ORDER BY SALES DESC, A.PRODUCT_CODE ASC;