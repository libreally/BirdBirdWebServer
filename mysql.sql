# 数据库
# 存储数据的仓库，就称为数据库。
# 我们创建的一个目录users,里面存放了很多的obj文件，每个文件保存了一组信息。users就是一个
# 数据库。只是我们管理起来要么手动，要么写一些代码进行维护。
# 低效，不通用。
#
# 数据库管理系统DBMS: DataBaseManagementSystem
# 常见的数据库管理系统:
# MySQL
# ORACLE
# SQLServer
# DB2

# 主要的学习目标:
# SQL：Structured Query Language
# 学习SQL语句。
# SQL语句是我们操作数据库的语言，通过执行SQL可以完成对数据的相关操作。


# 连接数据库的形式:
# 1:控制台(命令行)的客户端
# 2:第三方图形界面的软件
# 3:IDEA这样的集成开发环境中也带有连接数据库的操作
# 4:使用JDBC连接数据库
#
# SQL基础:
# 查看当前MySQL当中有多少个数据库:
SHOW DATABASES;

# 创建数据库:
# CREATE DATABASE 数据库名
# 创建数据库:mydb
CREATE DATABASE mydb;

# 数据库创建时可以指定字符集
# CREATE DATABASE 数据库名 CHARSET=UTF8/GBK

# 创建数据库:db1(字符集用gbk)  db2(字符集用utf8)
CREATE DATABASE db1 CHARSET = UTF8;
CREATE DATABASE db2 CHARSET = GBK;

# 查看数据库信息
# SHOW CREATE DATABASE 数据库名;
SHOW CREATE DATABASE db2;

# 删除数据库
# DROP DATABASE 数据库名
DROP DATABASE db1;
DROP DATABASE db2;

# 想保存数据,就要选取需要保存数据的数据库,然后才能在该数据库中建立表等操作.
# USE 数据库名
# 使用mydb这个数据库
USE mydb;

#练习:
# 1. 创建 mydb1和mydb2 数据库 字符集分别为utf8和gbk
CREATE DATABASE mydb1 CHARSET = utf8;
CREATE DATABASE mydb2 CHARSET = gbk;
# 2. 查询所有数据库检查是否创建成功
SHOW DATABASES;
# 3. 检查两个数据库的字符集是否正确
SHOW CREATE DATABASE mydb1;
SHOW CREATE DATABASE mydb2;
# 4. 先使用mydb2 再使用 mydb1
USE mydb2;
USE mydb1;
# 5. 删除两个数据库
DROP DATABASE mydb1;
DROP DATABASE mydb2;

# 在mysql中,我们可以为不同的项目创建不同的数据库.而每个数据库中都可以创建若干张表.每张表
# 用来保存一组数据.比如我们为保存用户信息可以创建userinfo表.保存文章信息可以创建article表等

# SQL语句分类:
# DDL,DML,DQL,TCL,DCL

# 表相关的操作
# DDL语句:数据定义语言,用来操作数据库对象的.
# 数据库对象:表,视图,索引都属于数据库对象.

# 创建表
# CREATE TABLE 表名(
#     列名1 类型[(长度)] [DEFAULT 默认值] [约束条件],
#     列名2 类型...
# )[CHARSET=utf8/gbk]

# 创建userinfo表
CREATE TABLE userinfo
(
    id       INT,
    username VARCHAR(32),
    password VARCHAR(32),
    nickname VARCHAR(32),
    age      INT(3)
);
# 数字的长度表示位数,VARCHAR的长度表示最多占用的字节数

# 查看当前数据库创建的所有表
SHOW TABLES;

# 查看创建的某一张表的详细信息
# SHOW CREATE TABLE 表名
SHOW CREATE TABLE userinfo;

#查看表结构
# DESC 表名
DESC userinfo;

#删除表
# DROP TABLE 表名
DROP TABLE userinfo;

#修改表
#修改表名
# RENAME TABLE 原表名 TO 新表名
RENAME TABLE userinfo TO user;

# 练习:
# 1. 创建数据库mydb3 字符集gbk 并使用
CREATE DATABASE mydb3 CHARSET = gbk;
USE mydb3;
# 2. 创建t_hero英雄表, 有名字和年龄字段 默认字符集
CREATE TABLE t_hero
(
    id   INT,
    name VARCHAR(32),
    age  INT(3)
);
# 3. 修改表名为hero
RENAME TABLE t_hero TO hero;
# 4. 查看表hero的字符集
SHOW CREATE TABLE hero;
# 5. 查询表hero结构
DESC hero;
# 6. 删除表hero
DROP TABLE hero;
# 7. 删除数据库mydb3
DROP DATABASE mydb3;
/*
# 修改表结构:ALTER TABLE
    # 实际开发中,通常不建议在表中含有数据时修改表结构
    # 添加列
    # ALTER TABLE 表名 ADD 列名 类型[长度]
ALTER TABLE user ADD gender VARCHAR(10);
DESC user;

# CREATE TABLE hero(
                       #     username VARCHAR(32),
                       #     age INT(3)
                           # )

    # 在表的第一列上添加新列
# ALTER TABLE 表名 ADD 列名 类型[长度] FIRST
ALTER TABLE hero ADD id INT FIRST;
DESC hero;

# 在表中插入一个字段
# ALTER TABLE 表名 ADD 列名 类型[长度] AFTER 字段名
ALTER TABLE hero ADD gender VARCHAR(10) AFTER username;

#删除表中现有的列
# ALTER TABLE 表名 DROP 字段名(注:列名)
ALTER TABLE hero DROP gender;
DESC hero;*/

# 修改表中现有的列
# 注意,当表中含有数据后,字段类型尽量不要改变,长度修改尽量不要减少,否则都有可能违背表中现有
# 数据要求导致修改失败.

# ALTER TABLE 表名 CHANGE 原字段名 新字段名 新类型
# 将age的类型从INT换成VARCHAR
ALTER TABLE hero
    CHANGE age age VARCHAR(10);
# 将age的长度改为100
ALTER TABLE hero
    CHANGE age age VARCHAR(100);
# 将age改为gender,长度改为10
ALTER TABLE hero
    CHANGE age gender VARCHAR(10);

# 练习:
# 1. 创建数据库mydb4 字符集utf8并使用
CREATE DATABASE mydb4 CHARSET = utf8;
USE mydb4;
# 2. 创建teacher表 有名字(name)字段
CREATE TABLE teacher
(
    name VARCHAR(32)
);
# 3. 添加表字段: 最后添加age 最前面添加id(int型) , age前面添加salary工资(int型)
ALTER TABLE teacher
    ADD age INT(3);
ALTER TABLE teacher
    ADD id INT FIRST;
ALTER TABLE teacher
    ADD salary INT AFTER name;

# 4. 删除age字段
ALTER TABLE teacher
    DROP age;
# 5. 修改表名为t
RENAME TABLE teacher TO t;
# 6. 删除表t
DROP TABLE t;
# 7. 删除数据库mydb4
DROP DATABASE mydb4;

# 总结:
# DDL语言,数据定义语言,操作数据库对象
# CREARE,ALTER,DROP
# 创建表:CREATE TABLE
# 修改表:ALTER TABLE
# 删除表:DROP TABLE

# DML语言:数据操作语言,是对表中的数据进行操作的语言,包含:增,删,改操作
CREATE TABLE person
(
    name VARCHAR(32),
    age  INT(3)
);

# 插入数据
# INSERT INTO 表名 [(字段1,字段2...)] VALUES (字段1的值，字段2的值...)
INSERT INTO person(name, age)
VALUES ('张三', 22);
INSERT INTO person(age, name)
VALUES (22, '王五');

#未指定的列插入的都是列的默认值。当创建表时没有为列声明特定的默认值时，列默认值为null。
INSERT INTO person(name)
VALUES ('李四');


#字段名可以忽略不写，此时为全列插入，即:VALUES需要指定每一列的值，且顺序，个数，类型必须与表中的字段一致
INSERT INTO person
VALUES ('传奇', 22);

# 查看person表中的所有数据
SELECT *
FROM person;

#修改表中的数据
#UPDATE 表 SET 字段1=新改值。。。 WHERE 条件约束;
UPDATE person
SET age=55;
SELECT *
FROM person;

UPDATE person
SET age=34
WHERE name = '张三';
SELECT *
FROM person;
#将年龄大于50的人的年龄改为25
UPDATE person
SET age=25
WHERE age > 50;
SELECT *
FROM person;
#将每个人的年龄加一岁
UPDATE person
SET age=age + 1;
SELECT *
FROM person;

UPDATE person
SET name='李四',
    age=35
WHERE age = 55;
SELECT *
FROM person;

#删除表中语句
#DELETE FROM 表名 【WHERE 过滤条件】
DELETE
FROM person
WHERE age = 26;
SELECT *
FROM person;

UPDATE person
SET age=20
WHERE name = '张三';
SELECT *
FROM person;

#删除年龄大于25
DELETE
FROM person
WHERE age > 25;
SELECT *
FROM person;

#清空表操作
DELETE
FROM person;

/*#练习:
#1. 创建数据库day1db 字符集utf8并使用
create database day1db charset=utf8;
use day1db;
#2. 创建t_hero表, 有name字段 字符集utf8
create table t_hero(name varchar(20))charset=utf8;
#3. 修改表名为hero
rename table t_hero to hero;
#4. 最后面添加价格字段money, 最前面添加id字段, name后面添加age字段
alter table hero add money int;
alter table hero add id int first;
alter table hero add age int after name;
#5. 表中添加以下数据: 1,李白,50,6888 2,赵云,30,13888 3,刘备,25,6888
insert into hero values(1,'李白',50,6888);
insert into hero values(2,'赵云',30,13888);
insert into hero values(3,'刘备',25,6888);
#6. 查询价格为6888的英雄名
select name from hero where money=6888;
#7. 修改刘备年龄为52岁
update hero set age=52 where name='刘备';
#8. 修改年龄小于等于50岁的价格为5000
update hero set money=5000 where age<=50;
#9. 删除价格为5000的信息
delete from hero where money=5000;
#10. 删除表, 删除数据库
drop table hero;
drop database day1db;*/

CREATE TABLE userinfo
(
    id     INT,
    name   VARCHAR(30),
    birth  DATETIME,
    salary DOUBLE(7, 2)
);
INSERT INTO userinfo
VALUES (1, '张三', '1999-02-20 00:02:20', 5000.59);
INSERT INTO userinfo
VALUES (2, '李四', '1999-03-23 00:52:10', 7550.59);
SELECT *
FROM userinfo;

CREATE TABLE student
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(30) NOT NULL,
    age    INT(3),
    gender CHAR(1)
);
SELECT *
FROM student;
INSERT INTO student
VALUES (1, '张三', 12, '男'),
       (2, '李四', 13, '女');
INSERT INTO student(name, age, gender)
VALUES ('克晶', 88, '女');
DESC student;

CREATE TABLE teacher
(
    id     INT(2) PRIMARY KEY,
    name   VARCHAR(30) NOT NULL,
    age    INT(3),
    gender CHAR(1)
);

INSERT INTO teacher
VALUES (1, '张三', 12, '男'),
       (2, '李四', 13, '女');
SELECT *
FROM teacher;

select *
from student
where student.gender is not null;

USE empdb;
#DQL数据库查询语言
#select  字段‘’‘ from  表名 where 条件约束
SELECT *
FROM emp;
SELECT name, job, hiredate
FROM emp;

#比较运算符 =,>,<,<=,>=,<>
#查看工资大于1000的员工的名字职位工资
SELECT name, job, sal
FROM emp
WHERE sal > 1000;
#查看职位除人事之外的所有员工的名字，工资，职位
SELECT name, job, sal
FROM emp
WHERE job <> '人事';
#部门编号为2且工资高于1000的所有信息
SELECT *
FROM emp
WHERE dept_id = 2
  AND sal > 1000;
#查看职位是销售且人事的工资大于一千所有员工的名字 工资 职位 部门编号
SELECT name, job, sal, dept_id
FROM emp
WHERE job = '人事'
   OR job = '销售' AND sal > 1000;
#查看职位是销售和人事的工资大于一千的所有员工的名字 工资 职位 部门编号
SELECT name, job, sal, dept_id
FROM emp
WHERE (job = '人事' OR job = '销售')
  AND sal > 1000;

#IN(列表) 值在列表中(等于列表中的其中之一)
SELECT name, job, sal, dept_id
FROM emp
WHERE job IN ('人事', '销售');
#除了销售和人事之外的所有的员工姓名 职位 工资
SELECT name, job, sal
FROM emp
WHERE job <> '人事'
  AND job <> '销售';
SELECT name, job, sal
FROM emp
WHERE job NOT IN ('人事', '销售');

#查看工资在两千到三千的员工姓名 职位 工资
SELECT name, job, sal
FROM emp
WHERE sal >= 2000
  AND sal <= 3000;
SELECT name, job, sal
FROM emp
WHERE sal BETWEEN 2000 AND 3000;

#查看公司有多少种职位
#DISTINCT去重
SELECT DISTINCT job
FROM emp;

# 1. 查询2号部门工资高于1000的员工信息
SELECT *
FROM emp
WHERE dept_id = 2
  AND sal > 1000;
# 2. 查询3号部门或工资等于5000的员工信息
SELECT *
FROM emp
WHERE dept_id = 3
   OR sal = 5000;
# 3. 查询工资在1000到2000之间的员工姓名和工资
SELECT name, sal
FROM emp
WHERE sal BETWEEN 1000 AND 2000;
# 4. 查询工资不等于3000和5000的员工信息
SELECT *
FROM emp
WHERE sal NOT IN (3000, 5000);
# 5. 查询1号部门有哪几种不同的工作
SELECT DISTINCT job
FROM emp
WHERE dept_id = 1;

#模糊查询LINK _ %
SELECT *
FROM emp
WHERE name LIKE '孙%';

SELECT *
FROM emp
WHERE name LIKE '__精';

# 总结
# %X%:字符串中包含X
# %X:字符串以X结尾
# X%:字符串以X开头
# _X%:字符串第二个字符是X
# %X_:倒数第二个字符是X
# X%Y:字符串以X开头Y结尾
# X_Y:字符串只有三个字，第一个是X，第三个是Y

# 练习:
# 1. 查询名字姓猪的员工姓名
SELECT name
FROM emp
WHERE name LIKE '猪%';
# 2. 查询名字中包含僧的员工信息
SELECT *
FROM emp
WHERE name LIKE '%僧%';
# 3. 查询名字以精结尾的员工姓名
SELECT name
FROM emp
WHERE name LIKE '%精';
# 4. 查询工作中包含销售并且工资大于1500的员工信息
SELECT *
FROM emp
WHERE job LIKE '%销售%'
  AND sal > 1500;
# 5. 查询工作中第二个字是售的员工姓名和工作
SELECT name, job
FROM emp
WHERE job LIKE '_售%';
# 6. 查询1号和2号部门中工作以市开头的员工信息
SELECT *
FROM emp
WHERE dept_id IN (1, 2)
  AND job LIKE '市%';

# ORDER BY 根据指定字段排序  DESC 降序排序
SELECT name, sal
FROM emp
ORDER BY sal;

SELECT name, sal
FROM emp
ORDER BY sal DESC;



# 练习:
# 1. 查询有领导的员工信息,按照入职日期(hiredate) 升序排序
SELECT *
FROM emp
WHERE manager IS NOT NULL
ORDER BY hiredate;
# 2. 查询1号部门中名字中包含八的员工信息
SELECT *
FROM emp
WHERE dept_id = 1
  AND name LIKE '%八%';
# 3. 查询2号和3号部门中工资低于1500的员工信息
SELECT *
FROM emp
WHERE dept_id IN (2, 3)
  AND sal < 1500;

SELECT *
FROM emp
WHERE (dept_id = 2 OR dept_id = 3)
  AND sal < 1500;
# 4. 查询人事和程序员中工资高于2500的员工姓名,工资和工作
SELECT name, sal, job
FROM emp
WHERE job IN ('人事', '程序员')
  AND sal > 2500;
# 5. 查询不是CEO的员工中工资高于2000的员工姓名,工资和工作,并且按照工资降序排序
SELECT name, sal, job
FROM emp
WHERE job <> 'CEO'
  AND sal > 2000
ORDER BY sal DESC;


#分页查询 必须先排序才有意义
#在ORDER BY 中 LIMIT（跳过的记录数，请求的记录数）
#LIMIT（页数-1）*每页显示的记录数，每页显示的记录数
#每页显示三条，查看第三页
SELECT name, sal
FROM emp
ORDER BY sal
LIMIT 3,3;


# 查询的字段可以使用表达式
# 查看每个员工的年薪是多少?
SELECT name, sal, sal * 12
FROM emp;
# 查询时也可以用函数的结果作为字段
# 孙悟空的职位是销售   name+"的职位是"+job
SELECT CONCAT(name, '的职位是', job)
FROM emp;

# 查看每个员工的工资，奖金，工资+奖金
# 数字与NULL运算结果就为NULL
SELECT name, sal, comm, sal + comm
FROM emp;

#NVL函数 用来替换NULL值
# NVL(arg1,arg2)  当arg1不为NULL时则函数返回arg1的值，如果arg1为NULL则返回arg2的值
# SELECT name,sal,NVL(comm,0) FROM emp;

# 别名
# 我们可以为字段定义别名，也可以给表定义别名。
# 为字段定义别名一般多用于:
# 1:隐藏实际表字段名名
# 2:为计算表达式或函数的结果只作为字段时定义可读性更好的字段名
SELECT name ename, sal salary
FROM emp;
SELECT name, sal * 12 salary
FROM emp;

# 支持的语法:
# 字段名 别名
SELECT name, sal * 12 salary
FROM emp;
# 字段名 as 别名
SELECT name as ename, sal * 12 salary
FROM emp;
# 字段名 as '别名'
SELECT name as 'ename', sal * 12 salary
FROM emp;
# 字段名 as "别名"
SELECT name as "ename", sal * 12 salary
FROM emp;

# ORDER BY子句，根据指定的字段排序查询结果集。
# 练习:
# 1. 查询员工表中3号部门工资高于1500的员工信息
SELECT *
FROM emp
WHERE dept_id = 3
  AND sal > 1500;
# 2. 查询2号部门员工或者没有领导的员工信息
SELECT *
FROM emp
WHERE dept_id = 2
   OR manager IS NULL;
# 3. 查询有领导的员工姓名,工资按照工资降序排序
SELECT name, sal
FROM emp
WHERE manager IS NOT NULL
ORDER BY sal DESC;
# 4. 查询2号和3号部门的员工姓名和入职日期hiredate按照入职日期降序排序
SELECT name, hiredate
FROM emp
WHERE dept_id IN (2, 3)
ORDER BY hiredate DESC;
# 5. 查询名字中包含僧和包含精的员工姓名
SELECT name
FROM emp
WHERE name LIKE '%僧%'
   OR name LIKE '%精%';
# 6. 查询工资高于2000的工作有哪几种?
SELECT DISTINCT job
FROM emp
WHERE sal > 2000;
# 7. 查询工资升序第4页的2条数据
SELECT *
FROM emp
ORDER BY sal
LIMIT 6,2;


# 聚合函数(也称为多函数).聚合函数是用来多条记录统计为一条记录
# MIN():求最小值
# MAX():求最大值
# COUNT():统计记录数
# AVG():求平均值
# SUM():求和
# 查看公司收入收入的员工工资是多少?
SELECT MIN(sal)
FROM emp;

# SELECT MIN(sal) 最低工资 ,MAX(sal) 最高工资,AVG(sal) 平均工资,SUM(sal) 工资总和 FROM emp;

#聚合函数忽略NULL值，在AVG中比较明显可以看出这一点，以下仅对4个有奖金的人取了个并均值，并非11个人的平均值
SELECT MIN(comm), MAX(comm), AVG(comm), SUM(comm)
FROM emp;

SELECT *
FROM emp 练习;
# 1. 查询销售的平均工资
SELECT AVG(sal)
FROM emp
WHERE job = '销售';
# 2. 查询程序员的最高工资
SELECT MAX(sal)
FROM emp
WHERE job = '程序员';
#     3. 查询名字包含精的员工数量
SELECT COUNT(*)
FROM emp
WHERE name LIKE '%精%';
#     4. 查询和销售相关的工作一个月工资总和
SELECT SUM(sal)
FROM emp
WHERE job LIKE '%销售%';

#     5. 查询2号部门的最高工资和最低工资起别名
SELECT MIN(sal) 最低工资, MAX(sal) 最高工资
FROM emp
WHERE dept_id = 2;
# 查看每个部门的工资并排序
SELECT sal, dept_id
FROM emp
ORDER BY dept_id;
# GROUP BY子句，分组。
# GROUP BY 也是为统计服务的，所以是搭配在聚合函数上使用的。
#查看每个部门的平均工资是多少
SELECT AVG(sal), dept_id
FROM emp
GROUP BY dept_id;

# 每种职位的最高工资
SELECT MAX(sal), job
FROM emp
GROUP BY job;

# 练习
# 1.查询每个部门的最高工资
SELECT MAX(sal), dept_id
FROM emp
GROUP BY dept_id;
# 2.查询每个部门工资高于2000的人数
SELECT COUNT(dept_id)
FROM emp
WHERE sal > 2000;
# 3.查询每种工作的最低工资
SELECT MIN(sal), job
FROM emp
GROUP BY job;
# 4.查询1号部门和2号部门的人数
SELECT COUNT(*), dept_id
FROM emp
WHERE dept_id IN (1, 2)
GROUP BY dept_id;
# 5.查询平均工资最高的部门id和平均工资
SELECT AVG(sal), dept_id
FROM emp
GROUP BY dept_id
ORDER BY AVG(sal) DESC
LIMIT 0,1;

SELECT AVG(sal) avg, dept_id
FROM emp
GROUP BY dept_id
ORDER BY avg DESC
LIMIT 0,1;
#查看每个部门的平均工资是多少
# 聚合函数不可以使用在WHERE子句中
SELECT AVG(sal), dept_id
FROM emp
GROUP BY dept_id
HAVING AVG(sal) > 2000;

SELECT AVG(sal), dept_id
FROM emp
GROUP BY dept_id
HAVING MIN(sal) > 1000;

# 查询每个部门的工资总和，只查询有领导的员工，并且要求工资总和大于5400
SELECT SUM(sal) sum, dept_id
FROM emp
WHERE manager IS NOT NULL
GROUP BY dept_id
HAVING SUM(sal) > 5400;
# 查看比公司平均工资低的员工的名字和工资
SELECT name, sal
FROM emp
WHERE sal > (SELECT AVG(sal) FROM emp);

# 查询工资高于2号部门平均工资的员工信息
SELECT *
FROM emp
WHERE sal > (SELECT AVG(sal)
             FROM emp
             WHERE dept_id = 2);
#查询比沙僧工资低的人的信息
SELECT name, sal
FROM emp
WHERE sal < (SELECT sal
             FROM emp
             WHERE name = '沙僧');
# 查询与孙悟空同职位的信息
SELECT *
FROM emp
WHERE job = (SELECT job
             FROM emp
             WHERE name = '孙悟空');
# 查询公司最低工资员工同属一个部门的员工信息
SELECT *
FROM emp
WHERE dept_id = (SELECT dept_id
                 FROM emp
                 WHERE sal = (SELECT MIN(sal)
                              FROM emp));
#查询比3号和2号部门工资都高的员工名字和工资
SELECT *
FROM emp
WHERE sal > ALL (SELECT sal
                 FROM emp
                 WHERE dept_id IN (2, 3));
#查询比3号和2号部门工资任意一个高的员工名字和工资
SELECT *
FROM emp
WHERE sal > ANY (SELECT sal
                 FROM emp
                 WHERE dept_id IN (2, 3));

# 子查询分类(按查询结果集分类):
# 单行单列子查询(结果集只有1个值)
# 多行单列子查询(结果集有多个值)
# 多行多列子查询(结果集是一个表)

# 单列子查询通常用于过滤条件中使用。
# 单行单列可以配合>,>=,=,<,<=使用
# 多行单列可以配合ANY,ALL,IN使用。
# 例如:
# >ALL(子查询):大于子查徐结果集中最大的
# <ALL(子查询):小于子查询结果集中最小的
# >ANY(子查询):大于子查询结果集中最小的
# <ANY(子查询):小于子查询结果集中最大的
# IN(子查询):等于子查询结果集中的任意一个值

# 多行多列子查询(结果集是一个表),通常就当做一张表使用，可以跟在FROM字句中
# 或者跟在DDL语句中基于一个查询结果集创建表。
# 将1号部门员工信息单独定义一张表名为emp_dept1
CREATE TABLE emp_dept1
AS
SELECT *
FROM emp
WHERE dept_id = 1;

SELECT *
FROM emp_dept1;

# 如果创建表基于的子查询中某个字段是一个表达式或函数时，要给该字段取别名，那么创建出来的表该字段会以别名作为字段名。
# 创建一张表emp_dept_sal.该表记录了每个部门的薪资情况，包含最高工资，最低工资，平均工资，工资总和，部门编号

CREATE TABLE emp_dept_sal
AS
SELECT MAX(sal) max_sal, MIN(sal) min_sal, AVG(sal) avg_sal, SUM(sal) sum_sal, dept_id
FROM emp
GROUP BY dept_id;

SELECT *
FROM emp_dept_sal;
# 查看每个部门的最低薪水是多少?
SELECT min_sal
FROM emp_dept_sal;
# 创建一张表emp_annual_salary，记录每个员工的名字，工资，年薪和部门，年薪字段用:a_salary,工资用salary
CREATE TABLE emp_annual_salary
AS
SELECT name, sal salary, sal * 12 a_salary, dept_id
FROM emp;

SELECT *
FROM emp_annual_salary;

# 名字里含"精"的员工年薪是多少?
SELECT name, a_salary
FROM emp_annual_salary
WHERE name LIKE '%精%';


SELECT *
FROM dept ;
/*关联查询 查询结果集中的数据来自多张表，而表与表中数据之间的对应关系就是关联关系

两张表就可以产生关联关系了，关联关系分为三类:
1:一对一  A表中的1条记录只唯一对应B表中的1条记录
2:一对多  A表中的1条记录可以对应B表中的多条记录
3:多对多  A表与B表双向都是一对多时，就是多对多关系。

关联查询就是基于多张表联合查询数据而形成一个结果集的过程，在关联查询中一个至关重要的点就是关联条件
原则:N张表关联查询至少要有N-1个连接条件。
缺失连接条件会产生笛卡尔积，该查询结果集的记录数是关联表中所有记录数乘积的结果，它通常是一个无意义的结果集，要
尽量避免产生

关联查询语法:*/
# SELECT 字段
# FROM 表A，表B[，表C，表D...]
# WHERE 过滤条件
# AND 连接条件
# 注意:连接条件必须与过滤条件同时成立!!

# 笛卡尔积:产生了44条数据，将emp表每条记录都与dept表每条记录产生一条记录。
SELECT *
FROM dept;

# 当表中出现了同名字段时，为了查询区分字段来自于哪张表，我们可以在字段名前用"表名."来标识

SELECT emp.name, emp.sal, emp.dept_id, dept.name, dept.loc
FROM emp,
     dept
WHERE emp.dept_id = dept.id;
# 查看在天庭工作的人
SELECT emp.name, emp.sal, emp.dept_id, dept.name, dept.loc
FROM emp,
     dept
WHERE emp.dept_id = dept.id
  AND dept.loc = '天庭';
#名字含飞的人来自哪里
SELECT emp.name, dept.loc
FROM dept,
     emp
WHERE emp.dept_id = dept.id
  AND emp.name LIKE '%飞%';
#天庭的最高工资
SELECT MAX(sal), dept.loc
FROM emp,
     dept
WHERE emp.dept_id = dept.id
  AND dept.loc = '天庭'
GROUP BY dept.loc;
# 查看每个地区的平均工资
SELECT AVG(sal), dept.loc
FROM emp,
     dept
WHERE emp.dept_id = dept.id
GROUP BY dept.loc;

# 1. 查询工资大于等于3000的员工姓名和工资
SELECT name,sal
FROM emp
WHERE sal>=3000;
# 2. 查询1号部门的员工姓名和工作
SELECT name,job
FROM emp
WHERE dept_id=1;
# 3. 查询不是程序员的员工姓名和工作(两种写法)
SELECT name,job
FROM emp
WHERE job<>'程序员';
SELECT name,job
FROM emp
WHERE job NOT IN ('程序员');
# 4. 查询奖金等于300的员工姓名,工资和工作
SELECT name,sal,job
FROM emp
WHERE comm=300;
# 5. 查询1号部门工资大于2000的员工信息
SELECT *
FROM emp
WHERE dept_id=1 AND sal>2000;
# 6. 查询3号部门或工资等于5000的员工信息
SELECT *
FROM emp
WHERE dept_id=3 OR sal=5000;
# 7. 查询出CEO和项目经理的名字
SELECT name
FROM emp
WHERE job='CEO' OR job='项目经理';
# 8. 查询工资为3000,1500和5000的员工信息
SELECT *
FROM emp
WHERE sal IN (3000,1500,5000);
# 9. 查询工资不等于3000,1500和5000的员工信息
SELECT *
FROM emp
WHERE sal NOT IN (3000,1500,5000);
# 10. 查询工资在1000到2000之间的员工信息
SELECT *
FROM emp
WHERE sal BETWEEN 1000 AND 2000;
# 11. 查询工资在1000到2000以外的员工信息
SELECT *
FROM emp
WHERE sal<=1000 OR sal>=2000;
# 12. 查询有领导的员工姓名和领导id
SELECT name,manager
FROM emp
WHERE manager IS NOT NULL ;
# 13. 查询没有领导的员工姓名和领导id
SELECT name,manager
FROM emp
WHERE manager IS NULL ;
# 14. 查询员工表中出现了哪几种不同的工作
SELECT job
FROM emp
GROUP BY job;
# 15. 查询员工表中出现了那几个部门的id
SELECT dept_id
FROM emp
GROUP BY dept_id;
# 16. 查询姓孙的员工姓名
SELECT name
FROM emp
WHERE name LIKE '孙%';
# 17. 查询名字最后一个字是精的员工信息
SELECT name
FROM emp
WHERE name LIKE '%精';
# 18. 查询工作中包含销售的员工信息
SELECT *
FROM emp
WHERE job='销售';
# 19. 查询工作中第二个字是售的员工信息
SELECT *
FROM emp
WHERE job LIKE '_售%';
# 20. 查询名字中包含僧的员工并且工资高于2000的员工信息
SELECT *
FROM emp
WHERE name LIKE '%僧%' AND sal>2000;
# 21. 查询1号和2号部门中工作以市开头的员工信息
SELECT *
FROM emp
WHERE dept_id IN (1,2) and job LIKE '市%';
# 22. 查询所有员工的姓名和工资 按照工资升序排序
SELECT name,sal
FROM emp
ORDER BY sal;
# 23. 查询所有员工的姓名和工资 按照工资降序排序
SELECT name,sal
FROM emp
ORDER BY sal DESC ;
# 24. 查询所有员工姓名 工资和部门id 按照部门id降序排序,如果部门id一致则按照工资升序排序
SELECT name,sal,dept_id
FROM emp
ORDER BY dept_id;
# 25. 查询员工表中3号部门工资高于1500的员工信息
SELECT *
FROM emp
WHERE dept_id=3 AND sal>1500;
# 26. 查询2号部门员工或者没有领导的员工信息
SELECT *
FROM emp
WHERE dept_id=2 OR manager IS NULL ;
# 27. 查询有领导的员工姓名,工资按照工资降序排序
SELECT name,sal
FROM emp
WHERE manager IS NULL
ORDER BY sal DESC ;
# 28. 查询2号和3号部门的员工姓名和入职日期hiredate 按照入职日期降序排序
SELECT name,hiredate
FROM emp
WHERE dept_id IN (1,2)
ORDER BY hiredate DESC ;
# 29. 查询名字中包含僧和包含精的员工姓名
SELECT name
FROM emp
WHERE name LIKE '%僧%' OR name LIKE '%精%';
# 30. 查询工资高于2000的工作有哪几种?
SELECT job,sal
FROM emp
WHERE sal>2000
ORDER BY job ;
# 31. 查询工资最高的前三个员工
SELECT *
FROM emp
ORDER BY sal DESC
LIMIT 0,3;
# 32. 查询员工表按照id排序, 第2页的5条数据
SELECT *
FROM emp
ORDER BY id
LIMIT 5,5;
# 33. 查询员工表按照id排序, 第3页的4条数据
SELECT *
FROM emp
ORDER BY id
LIMIT 8,4;
# 34. 查询3号部门工资最低的员工姓名和工资
SELECT MIN(sal),name
FROM emp
WHERE dept_id=3;
# 35. 查询工作不是人事的员工中工资降序第二页的3条数据
SELECT *
FROM emp
WHERE job<>'人事'
ORDER BY sal DESC
LIMIT 3,3;
# 36. 查询没有领导的员工和3号部门的员工,工资降序取前三条
SELECT *
FROM emp
WHERE dept_id=3 OR manager IS NULL
ORDER BY sal DESC
LIMIT 0,3;
# 37. 查询2号部门的最高工资
SELECT MAX(sal),dept_id
FROM emp
WHERE dept_id=2;
# 40. 查询有领导的员工中工资在1000到2000之间的人数
SELECT COUNT(*)
FROM emp
WHERE manager IS NOT NULL
AND sal BETWEEN 1000 AND 2000;
# 41. 查询3号部门的工资总和
SELECT SUM(sal),dept_id
FROM emp
WHERE dept_id=3;
# 42. 查询程序员和销售的总人数
SELECT COUNT(*)
FROM emp
WHERE job IN ('程序员','销售');
# 43. 查询1号部门有领导的员工的平均工资
SELECT AVG(sal)
FROM emp
WHERE dept_id=1 AND manager IS NOT NULL;
# 44. 查询1号部门的最低工资和最高工资
SELECT MIN(sal),MAX(sal),dept_id
FROM emp
WHERE dept_id=1;
# 45. 查询和销售相关的工作人数
SELECT COUNT(job)
FROM emp
WHERE job='销售';
# 46. 查询工资不是1500和3000的员工人数
SELECT COUNT(sal)
FROM emp
WHERE sal NOT IN (1500,3000);
# 47. 查询1号部门出现了哪几种工作
SELECT job
FROM emp
WHERE dept_id=1
GROUP BY job;
# 48. 查询名字包含精的员工数量
SELECT COUNT(*)
FROM emp
WHERE name LIKE '%精%';
# 49. 查询和销售相关的工作一个月工资总和
SELECT SUM(sal)
FROM emp
WHERE job='销售';
# 50. 查询2号部门的最高工资和最低工资起别名
SELECT MAX(sal) max_sal,MIN(sal) min_sal
FROM emp
WHERE dept_id=2;
# 51.查询每个部门的平均工资
SELECT AVG(sal),dept_id
FROM emp
GROUP BY dept_id;
# 52. 查询每种工作的平均工资
SELECT AVG(sal),job
FROM emp
GROUP BY job;
# 53. 查询每个部门的最高工资
SELECT MAX(sal),dept_id
FROM emp
GROUP BY dept_id;
# 54. 查询每种工作的最低工资
SELECT MIN(sal),job
FROM emp
GROUP BY job;
# 55. 查询每个部门工资高于2000的人数
SELECT COUNT(sal),dept_id
FROM emp
WHERE sal>2000
GROUP BY dept_id;
# 56. 查询每个部门有领导的员工人数
SELECT COUNT(manager),dept_id
FROM emp
WHERE manager IS NOT NULL
GROUP BY dept_id;
# 57. 查询1号部门每种工作的最低工资
SELECT MIN(sal),job
FROM emp
WHERE dept_id=1
GROUP BY job;
# 58. 查询平均工资最高的部门id和平均工资
SELECT AVG(sal),dept_id
FROM emp
GROUP BY dept_id
ORDER BY AVG(sal) DESC
LIMIT 0,1;
# 59. 查询每个部门的平均工资,要求平均工资大于2000
SELECT AVG(sal),dept_id
FROM emp
GROUP BY dept_id
HAVING AVG(sal)>2000;
# 60. 查询每种工作的人数,只查询人数大于1的
SELECT COUNT(job),job
FROM emp
GROUP BY job;
# 61. 查询每个部门的工资总和,只查询有领导的员工, 并且要求工资总和大于5400.
SELECT SUM(sal),dept_id
FROM emp
WHERE manager IS NOT NULL
GROUP BY dept_id
HAVING SUM(sal)>5400;
# 62. 查询每个部门的平均工资, 只查询工资在1000到3000之间的,并且过滤掉平均工资低于2000的
SELECT AVG(sal),dept_id
FROM emp
WHERE sal BETWEEN 1000 AND 3000
GROUP BY dept_id
HAVING AVG(sal)>2000;

# 内连接JOIN子句
# 内连接与关联查询效果一致，区别是单独书写关联关系(关联条件与过滤条件分开)
# JOIN..ON..子句可以写多个，当连接多张表时使用。
#
# SELECT 字段
# FROM A表 a
# JOIN B表 b
# ON a.xx=b.xx(连接条件)
# JOIN C表 c
# ON c.xxx=b.xxx或c.xxx=a.xxx
# JOIN .... ON ...
# 查看每个员工信息以及其对应的部分信息
SELECT e.name,e.job,e.manager,e.sal,d.name,d.loc
FROM emp e
JOIN dept d
ON d.id = e.dept_id;

# 在内连接中，过滤条件还是写在WHERE子句中
# 查看工资高于1300的员工信息和所在的部门信息
# SELECT 字段
# FROM 表1
# JOIN 表2
# ON 连接条件
# WHERE 过滤条件
SELECT e.name,e.job,e.manager,e.sal,d.name,d.loc
FROM emp e,dept d
WHERE e.dept_id=d.id;
#查看工资高于1300的员工的信息和所属部门
SELECT emp.name,emp.job,dept.name,dept.loc
FROM emp,dept
WHERE emp.dept_id=dept.id AND emp.sal>1300;

SELECT *
FROM emp
JOIN dept d on d.id = emp.dept_id
WHERE sal>1300;

# 如果需要在结果集中列出不满足连接条件的记录时我们需要使用外连接
# 外连接有:
# 左外连接:以LEFT JOIN左侧表作为主表，其中的记录都要展示，不满足连接条件时来自右侧表中记录
#         的字段值全部为NULL
# 右外连接:以RIGHT JOIN右侧表作为主表，其中的记录都要展示，不满足连接条件时来自左侧表中记录
#         的字段值全部为NULL
SELECT e.name,e.job,e.manager,e.sal,d.name,d.loc
FROM emp e
         LEFT JOIN dept d
                   ON e.dept_id = d.id;

SELECT e.name,e.job,e.manager,e.sal,d.name,d.loc
FROM emp e
         RIGHT JOIN dept d
                    ON e.dept_id = d.id;
# 全连接效果，结果集包含满足连接条件的和左连接，右连接的所有数据
SELECT e.name,e.job,e.manager,e.sal,d.name,d.loc
FROM emp e
        LEFT JOIN dept d
              ON d.id = e.dept_id
UNION
SELECT e.name,e.job,e.manager,e.sal,d.name,d.loc
FROM emp e
         RIGHT JOIN dept d
                   ON d.id = e.dept_id;


CREATE TABLE emp_dept_sal
AS
SELECT MAX(sal) max_sal,MIN(sal) min_sal,AVG(sal) avg_sal,SUM(sal) sum_sal,dept_id
FROM emp
GROUP BY dept_id;
CREATE TABLE emp_annual_salary
AS
SELECT name,sal salary,sal*12 a_salary,dept_id
FROM emp;
# 查看比本部门平均工资高的员工信息
/*
1.先查询各部门平均薪资
select avg(sal) avg_sal from emp group by dept_id;
2.SELECT e.name,e.sal,a.avg_sal,a.dept_id
FROM emp e,(select avg(sal) avg_sal from emp group by dept_id) a
WHERE e.dept_id=a.dept_id
AND e.sal>a.avg_sal;
*/
SELECT e.name,e.sal,a.avg_sal,a.dept_id
FROM emp e,emp_dept_sal a
WHERE e.dept_id=a.dept_id
AND e.sal>a.avg_sal;

SELECT e.name,e.sal,e.dept_id,a.avg_sal
FROM emp e,(select avg(sal) avg_sal,dept_id from emp group by dept_id) a
WHERE e.dept_id=a.dept_id
AND e.sal>a.avg_sal;
# 查看比所在地区平均工资高的员工信息
# 第一步查看每个员工的工资以及所在地区
SELECT e.sal,d.loc
FROM dept d,emp e
WHERE e.dept_id=d.id;
# 第二步查看每个地区的平均工资
SELECT AVG(sal) avg_sal,d.loc,e.dept_id
FROM emp e,dept d
WHERE e.dept_id=d.id
GROUP BY d.loc;
# 第三步关联表 emp-dept-第二部的整体结果
SELECT e.name,e.sal,a.avg_sal,d.loc
FROM emp e,dept d,(SELECT AVG(sal) avg_sal, dept.loc,dept_id
            FROM emp,dept
            WHERE emp.dept_id = dept.id
            GROUP BY dept.loc) a
WHERE  e.sal>a.avg_sal
AND d.loc = a.loc
AND e.dept_id=d.id;
#改写join
SELECT e.name,e.sal,a.avg_sal,d.loc
FROM dept d
join emp e on d.id = e.dept_id
join (SELECT AVG(sal) avg_sal, dept.loc,dept_id
FROM emp,dept
WHERE emp.dept_id = dept.id
GROUP BY dept.loc) a
on d.loc = a.loc
WHERE e.sal>a.avg_sal;
