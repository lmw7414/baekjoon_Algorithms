WITH TBL AS (
    SELECT FIRST_HALF.FLAVOR, SUM(FIRST_HALF.TOTAL_ORDER + JULY.TOTAL_ORDER) AS TOTAL
    FROM FIRST_HALF, JULY
    WHERE FIRST_HALF.FLAVOR = JULY.FLAVOR
    GROUP BY FIRST_HALF.FLAVOR
    ORDER BY TOTAL DESC
    FETCH FIRST 3 ROWS ONLY
)

SELECT FLAVOR FROM TBL;



