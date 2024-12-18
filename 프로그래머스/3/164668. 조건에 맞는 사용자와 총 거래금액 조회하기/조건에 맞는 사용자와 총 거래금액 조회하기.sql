# SELECT WRITER_ID, SUM(PRICE) AS TOTAL_SALES
# FROM USED_GOODS_BOARD
# GROUP BY WRITER_ID
# HAVING SUM(PRICE) >= 700000

SELECT USER_ID, NICKNAME, TOTAL_SALES
FROM USED_GOODS_USER U RIGHT JOIN (
    SELECT WRITER_ID, SUM(PRICE) AS TOTAL_SALES
    FROM USED_GOODS_BOARD
    WHERE STATUS='DONE'
    GROUP BY WRITER_ID
    HAVING SUM(PRICE) >= 700000
) T ON U.USER_ID = T.WRITER_ID
ORDER BY TOTAL_SALES;

