-- EMPLOYEE ���̺��� �����, �Ի���-����, ����-�Ի��� ��ȸ
-- ��, ��Ī�� �ٹ��ϼ�1, �ٹ��ϼ� 2�� �ϰ� ��� ����ó��(����), ����� �ǵ��� ó��
SELECT EMP_NAME "�����", FLOOR(ABS(MONTH_BETWEEN(HIRE_DATE, SYSDATE))) "�ٹ��ϼ�1",
          FLOOR(ABS(MONTH_BETWEEN(SYSDATE, HIRE_DATE))) "�ٹ��ϼ�2"
FROM EMPLOYEE;


-- EMPLOYEE ���̺��� ����� Ȧ���� �������� ������ ��� ��ȸ
SELECT *
FROM EMPLOYEE
WHERE EMP_ID %2=1;

-- EMPLOYEE ���̺��� �ٹ� ����� 20�� �̻��� ���� ���� ��ȸ
SELECT *
FROM EMPLOYEE
WHERE ABS(MONTH_BETWEEN(HIRE_DATE, SYSDATE))

-- EMPLOYEE ���̺��� ��� ��, �Ի���, �Ի��� ���� �ٹ� �� �� ��ȸ.
