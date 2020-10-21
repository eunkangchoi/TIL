# Spring 복습노트

> ## Apache Maven

- **자바용 프로젝트 관리 도구**
- **pom.xml** (Project Object Model XML)문서를 통해 해당
  - **프로젝트의 버전 정보**들을 관리
  - **라이브러리 정보**들을 관리
  - **라이브러리를 자동으로 프로젝트에 추가**할 때 주로 `pom.xml`을 사용한다.

<br><br>

> ## pom.xml 예시

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/maven-v4_0_0.xsd">

  <!--모델정보 -->
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.kh</groupId>
	<artifactId>spring</artifactId>
	<name>1_Spring</name>
	<packaging>war</packaging>
	<version>1.0.0-BUILD-SNAPSHOT</version>

  <!--프레임워크 정보-->
	<properties>
		<java-version>1.8</java-version>
		<org.springframework-version>5.2.9.RELEASE</org.springframework-version>
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
	</properties>

	<!-- 사설저장소를 등록하기. (단, 너무많이 등록하면 느려질 수 있다.) -->
	<repositories>
		<repository>
			<id>Central</id>
			<url>https://repo1.maven.org/maven2/</url>
		</repository>

		<repository>
			<id>Datanucleus</id>
			<url>http://www.datanucleus.org/downloads/maven2/</url>
		</repository>
	</repositories>

	<!--dependencies:  라이브러리 등록 -->
	<dependencies>
		<!-- Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${org.springframework-version}</version>
			<exclusions>
				<!-- Exclude Commons Logging in favor of SLF4j -->
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				 </exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>

		<!-- AspectJ -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>

		<!-- Logging -->
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${org.slf4j-version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${org.slf4j-version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.15</version>
			<exclusions>
				<exclusion>
					<groupId>javax.mail</groupId>
					<artifactId>mail</artifactId>
				</exclusion>
				<exclusion>
					<groupId>javax.jms</groupId>
					<artifactId>jms</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jdmk</groupId>
					<artifactId>jmxtools</artifactId>
				</exclusion>
				<exclusion>
					<groupId>com.sun.jmx</groupId>
					<artifactId>jmxri</artifactId>
				</exclusion>
			</exclusions>
			<scope>runtime</scope>
		</dependency>

		<!-- @Inject -->
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>1</version>
		</dependency>

		<!-- Servlet -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>

		<!-- Test -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.13.1</version>
			<scope>test</scope>
		</dependency>

		<!--[OJDBC6] -->
		<!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc6 -->
		<dependency>
		    <groupId>com.oracle.database.jdbc</groupId>
		    <artifactId>ojdbc6</artifactId>
		    <version>11.2.0.4</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/oracle/ojdbc6 -->
		<dependency>
		    <groupId>oracle</groupId>
		    <artifactId>ojdbc6</artifactId>
		    <version>11.2.0.3</version>
		</dependency>


		<!-- mybatis -->
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
		<dependency>
		    <groupId>org.mybatis</groupId>
		    <artifactId>mybatis</artifactId>
		    <version>3.5.3</version>
		</dependency>

		<!--Spring Mybatis
			Spring에서 Mybatis를 사용하기위한 라이브러리 -->
		<!-- Spring과 dependency들을 연결해놓는 라이브러리 -->
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
		<dependency>
		    <groupId>org.mybatis</groupId>
		    <artifactId>mybatis-spring</artifactId>
		    <version>1.3.2</version>
		</dependency>

		<!-- Spring-jdbc: Spring에서 Database를 사용하기 위한 라이브러리 -->
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
		    <groupId>org.springframework</groupId>
		    <artifactId>spring-jdbc</artifactId>
		    <version>${org.springframework-version}</version>
		</dependency>

		<!--Connection pool설정을 위한 라이브러리. -->
		<!--  Connection pool/unpool상태 설정. -->
		<!-- https://mvnrepository.com/artifact/commons-dbcp/commons-dbcp -->
		<dependency>
		    <groupId>commons-dbcp</groupId>
		    <artifactId>commons-dbcp</artifactId>
		    <version>1.4</version>
		</dependency>


		<!-- 10.19 -->
		<!-- Adding spring security module(스프링 보안 모듈 추가- bcrypt)
			스프링 5.2.9버젼에서는 동작이 안되기때문에 버젼을 낮춘다.

			암호화에 필요한 것은 security/ web/ core
		-->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-core</artifactId>
			<version>5.2.7.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>5.2.7.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-config</artifactId>
			<version>5.2.7.RELEASE</version>
		</dependency>


		<!-- 파일업로드 관련 라이브러리 -->
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.3.3</version>
		</dependency>

		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.6</version>
		</dependency>
	</dependencies>

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-eclipse-plugin</artifactId>
                <version>2.9</version>
                <configuration>
                    <additionalProjectnatures>
                        <projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
                    </additionalProjectnatures>
                    <additionalBuildcommands>
                        <buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
                    </additionalBuildcommands>
                    <downloadSources>true</downloadSources>
                    <downloadJavadocs>true</downloadJavadocs>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.5.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <compilerArgument>-Xlint:all</compilerArgument>
                    <showWarnings>true</showWarnings>
                    <showDeprecation>true</showDeprecation>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>1.2.1</version>
                <configuration>
                    <mainClass>org.test.int1.Main</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

```

- ### pom.xml 구성요소
  - `<modelVersion>`:  모델에 대한 정보.
  - `<repository>`: 사설저장소를 등록하여 외부에서 다운로드하게끔 한다.
  - `<dependency>` : 라이브러리를 등록할때 사용된다.
  - `<plugin>`


<br><br>

<hr>

> ## Spring Framework

- 자바 플랫폼을 위한 오픈소스 애플리케이션 프레임워크

> ## Spring 프레임워크 특징

> ### **DI** (Dependency Injection) = **의존성 주입**

- 설정파일이나 Annotation(@)을 통해 객체간의 의존관계를 설정하여 **개발자가 직접 의존하는 객체를 생성할 필요없다.**
  - 프레임워크가 제어권을 갖고있고, 프레임워크가 객체를 생성하여 만들어놓기 때문이다. (cause 제어의 반전)

  ```
  개발자: '나 이 객체를 여기서 사용할래'/ '나 이거(객체) 활용할게'

  프레임워크: 그럼 내가 이 객체를 만들어서 주입시켜놓을게~
  ```

<br>

> ### **IoC** (Inversion of Control) = **제어의 반전**

- 개발자가 아닌 **프레임워크가 제어**한다.
  - 스프링 프레임워크가 만든 규칙을 따라야한다!

- **프레임워크(Spring)가 객체의 생성부터 소멸까지, 모든 생명주기의 관리**한다.
- 객체를 직접 생성/호출 하지 않고, **만들어둔 자원을 호출하여 사용한다.**


<br>

> ### Spring AOP (Aspect Oriented Programming) = 관점지향 프로그래밍

- 트랜잭션, 로깅, 보안 에서 사용.
- 여러 모듈이나, 여러 계층 에서 공통으로 필요로하는 기능의 경우에는 해당 기능들을 분리하여 관리한다.
- Filter와 비슷하다.
- 필요한 부분에만 추가 및 변경해서 사용.

<br>

> #### 그러면 Filter와 AOP와 어떤점이 다른가?

- **Filter**
  - **요청전/요청후** 에 들어가서, 무조건적으로 받아서 집어넣는다..

- **AOP**
  - **여러 시점을 정해서**, 어떠한 메소드를 호출하여 들어간다.
  - 세분화 되어있다.(분리해서 관리한다.)


<br><br>

> ### POJO (Plain Old Java Object)

- 프레임워크에서 사용되는 객체(vo)
- 프레임워크에 크게 영향을 받지 않고 사용할 수 있는 객체
- 일반적인 J2EE 프레임워크에 비해 특정 라이브러리를 사용할 필요 없어서 개발이 쉽고, 기존 라이브러리의 지원이 용이하다.

<br><BR>


> ### Spring JDBC

```
Mybatis나 Hibernate 등의 데이터베이스를 처리하는 영속성 프레임워크와 연결할 수 있는 인터페이스 제공.
```

<br>

> ### Spring MVC

```
MVC 디자인패턴을 통해 웹애플리케이션의 Model, View, Controller 사이의 의존관계를 DI 컨테이너에서 개발자가 아닌 서버가 객체들을 관리하는 웹 애플리케이션 구축
```

<br>

> ### PSA(Portable Service Abstraction)

- 서비스를 하나에 대해서 추상화로 묶어서 관리하겠다.

```
Spring을 다른 여러모듈을 사용함에 있어서 별도의 추상화 레이어를 제공한다.

예를 들어 JPA를 사용할 때 Spring JPA를 사용하여 추상화하므로, 실제구현에 있어서 Hibernate를 사용하든 EclipseLink를 사용하든

개발자는 모듈의 의존 없이 프로그램에 집중할 수 있다.
```

<br>

> ## Spring Framework의 구성모듈

![](./spring_framework_modules.gif)

<br>

> ### 1. Data Access - 데이터 접근 계층

- JDBC나 데이터베이스 연결하는 모듈
- Transaction(트랜잭션)에 해당하는 기능 담당
- 영속성 프레임워크(MyBaits, Hibernate)와의 연결을 담당한다.

<br>

> ### 2. MVC 계층 (MVC/ Remoting)

- Spring Framework에서 Servlet과 같은 웹 구현 기술과의 연결점을 Spring MVC구성으로 지원하기위해 제공되는 모듈계층

- HTTP호출도 MVC계층에 해당.
  - 서블릿들이 MVC계층에 있다.
  - 컨트롤러를 이용하여 추가적으로 구현이 가능하다.

<BR>

> ### 3. AOP 계층

- AOP를 지원하는 계층


<BR>

> ### 4. **Core Container**

- Spring의 핵심부분
- 모든 스프링관련 모듈들을 Core Container기반으로 구축한다.

- 스프링의 큰 특징인 DI와 IoC(제어의 반전=프레임워크가 제어담당/객체 담당)

- Beans: 객체 관리
  - 스프링의 핵심기능인 DI, Ioc 가 관여되어있다.

<BR><br>

> ## Spring의 구성 모듈

|모듈이름|내용|
|:--:|:--:|
|spring-beans|스프링 컨테이너를 이용하여 **객체를 생성**하는 기본 기능 제공|
|spring-context|객체 생성/ 라이프사이클 처리/ 스키마 확장 등의 기능 제공|
|spring-aop|AOP 기능을 제공한다.|
|spring-web|REST 클라이언트 데이터 변환 처리, 서블릿 필터, 파일 업로드 지원 등 웹 개발에 필요한 기반 기능 제공|
|spring-webmvc|스프링 기반의 MVC프레임워크, 웹 애플리케이션을 개발하는데 필요한 컨트롤러, 뷰 구현 제공.|
|spring-websocket|스프링 MVC패턴에서 웹소켓 연동을 처리할 수 있도록 제공한다.|


<BR><br>

> ## XML 파일들 기능 정리.

> ### `web.xml` : 웹 애플리케이션에 대한 모든 설정 정보를 저장.

> ### `mybatis-config` : mybatis에 대한 모든 설정 정보를 저장.

> ### `mapper.xml` : SQL쿼리문들을 저장.

> ### `pom.xml` : 라이브러리, 프로젝트 버젼 정보를 저장.

> ### `spring-context.xml` : 스프링 컨테이너 자체가 가지고 있어야되는 내용들을 저장.

<br>

> ## Spring 의 동작구조

> ### Spring 애플리케이션

> ### Spring 웹 애플리케이션
