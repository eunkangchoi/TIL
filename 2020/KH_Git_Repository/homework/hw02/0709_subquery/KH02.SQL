--2) 직원의 나이를 구해보자
-- 원래는 FLOOR(BETWEEN_MONTHS(SYSDATE, TO_DATE( SUBSTR(EMP_NO,1,6),'RRMMDD') )/12)
-- (현재 년도)-(직원태어난년도)
SELECT EMP_NAME 이름, EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO,1,2),'RR')) 나이
FROM EMPLOYEE;

--3) 제일 어린 나이를 구한다.
SELECT MIN( EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO,1,2),'RR')))
FROM EMPLOYEE;

--4) ANSWER
SELECT DEPT_CODE 사원코드, EMP_NAME 사원명,
        DEPT_TITLE 부서명,
        TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY'))-TO_NUMBER(TO_CHAR(TO_DATE(SUBSTR(EMP_NO,1,2),'RR'),'YYYY')) 나이,
        JOB_NAME 직급명
FROM EMPLOYEE
    JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID)
    JOIN JOB USING(JOB_CODE)
WHERE TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY'))-TO_NUMBER(TO_CHAR(TO_DATE(SUBSTR(EMP_NO,1,2),'RR'),'YYYY'))=(SELECT MIN(TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY'))-TO_NUMBER(TO_CHAR(TO_DATE(SUBSTR(EMP_NO,1,2),'RR'),'YYYY')))
                                                                                                        FROM EMPLOYEE);
                                                                 
--WHERE 나이 =(SELECT MIN((SELECT MIN(TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY'))-TO_NUMBER(TO_CHAR(TO_DATE(SUBSTR(EMP_NO,1,2),'RR'),'YYYY')))
--              FROM EMPLOYEE);) --OCCUR ERROR
