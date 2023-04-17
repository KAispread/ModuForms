# 🧩ModuForm 

***ModuForm***은 설문조사를 위한 웹 어플리케이션입니다.        
본 어플리케이션의 기능을 이용하기 위해선 로그인이 필요합니다.   
다음의 URL에서 직접 이용해 보실 수 있습니다.
&nbsp;
  

***현재 프리티어 기간 만료로 인해 동작하지 않습니다***
### 🖇LINK -> [ModuForm](http://modu-elb-1191182514.ap-northeast-2.elb.amazonaws.com:8080)    

&nbsp;
&nbsp;

> **🔑로그인**
- 애플리케이션 자체 로그인 (JWT)
- Google login (OAuth2.0)

&nbsp;

> **📑주요 기능**
- 회원가입, 로그인, 로그아웃
- 프로필 보기, 수정, 삭제 
- 설문 등록, 수정, 삭제 
- 응답 등록, 수정, 삭제
- 전체 설문 보기

&nbsp;      
> **📚버전 및 사용 기술**
- JAVA 11
- Spring Boot (2.7.3)
- Spring Security (5.7.3)
- Spring Data JPA (2.7.3)
    - H2(2.1.4 test) 
- AWS
    - EC2 (ALB)
    - RDS (MariaDB)
    - VPC
    - S3
    - CodeDeploy
- Github Actions

&nbsp;
&nbsp;
## 📌Overview

> __Main__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/224551216-ad31d272-50ff-48ff-a48d-958892911042.png" width="800" />
</p>
&nbsp;

> __Login__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/224550941-eb641d17-2715-4120-a6fb-942796f6be70.png"/>
</p>
&nbsp;

> __Profile__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/224551385-448acaf9-fad7-44ec-a62f-c77dff34652c.png"/>
</p>
&nbsp;

> __Survey__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/224551157-364cac44-f83d-434e-ada5-4abb5910473c.png"/>
</p>
&nbsp;

> __Answer__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/224550743-8c63b62f-af4d-447c-9ecb-a65763d2746f.png"/>
</p>
&nbsp;


## 📑 API (Swagger UI)
> __View__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/211711054-415868bb-da78-4bcf-afc3-c440d8451685.png" width="700" />
</p>
&nbsp;

> __Data__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/211711013-5e5cc170-09cf-41b5-b95c-fdb6f6263192.png" width="700" />
</p>
&nbsp;

## 🔱 ERD
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/224550591-706d14ee-92d5-4bac-b55a-441e6ba874bd.jpg" width="800" />
</p>
&nbsp;

## 🌊 Architecture
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/224550527-00049d0d-f7a0-456b-a2ab-d9c33213a6ef.png" width="800" />
</p>
&nbsp;

## 🔰 CI/CD
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/232543765-31e609b0-b74e-49c1-aa6f-431dbad1d3af.png" />
</p>
&nbsp;



# Reference
- https://docs.spring.io/spring-boot/docs/current/reference/html/ -> Spring Boot / Reference Doc 
- https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#data-conversion-formatting -> Thymeleaf Guide doc 
- [http://logback.qos.ch] -> Logback  
- https://docs.spring.io/spring-data/jpa/docs/current/api/ -> Spring Data JPA
