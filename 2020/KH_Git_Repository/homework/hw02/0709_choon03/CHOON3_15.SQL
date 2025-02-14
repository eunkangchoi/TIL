--문제15. 휴학생이 아닌 학생 중 평점이 4.0이상인 학생을 찾아
-- 그 학생의 학번, 이름, 학과 이름, 평점을 출력하는 SQL문을 작성하시오.

SELECT STUDENT_NO 학번, STUDENT_NAME 이름, DEPARTMENT_NAME "학과 이름", ROUND(AVG(POINT),8) 평점
FROM TB_STUDENT
        JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)--학과테이블 내부조인(DEPARTMENT_NO 중복)
        JOIN TB_GRADE USING(STUDENT_NO) --성적테이블 내부조인(STUDENT_NO 중복)
WHERE ABSENCE_YN='N' --휴학생이 아닌학생
GROUP BY STUDENT_NO, STUDENT_NAME, DEPARTMENT_NAME
HAVING AVG(POINT)>=4.0 --평점평균이 4.0이상
ORDER BY STUDENT_NO;
