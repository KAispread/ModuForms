# ๐งฉModuForm 

ModuForm์ ์ค๋ฌธ์กฐ์ฌ๋ฅผ ์ํ ์น ์ดํ๋ฆฌ์ผ์ด์์๋๋ค.        
๋ณธ ์ดํ๋ฆฌ์ผ์ด์์ ๊ธฐ๋ฅ์ ์ด์ฉํ๊ธฐ ์ํด์  ๋ก๊ทธ์ธ์ด ํ์ํฉ๋๋ค.   
๋ค์์ URL์์ ์ง์  ์ด์ฉํด ๋ณด์ค ์ ์์ต๋๋ค.    
### ๐LINK -> [ModuForm](http://ec2-3-36-156-200.ap-northeast-2.compute.amazonaws.com:8080)    


&nbsp;
&nbsp;
> **๐๋ก๊ทธ์ธ ๋ฐฉ์**
- ์ ํ๋ฆฌ์ผ์ด์ ์์ฒด ๋ก๊ทธ์ธ (JWT)
- Google login (OAuth2.0)
> **๐์ฃผ์ ๊ธฐ๋ฅ**
- ํ์๊ฐ์, ๋ก๊ทธ์ธ, ๋ก๊ทธ์์
- ํ๋กํ ๋ณด๊ธฐ, ์์ , ์ญ์  
- ์ค๋ฌธ ๋ฑ๋ก, ์์ , ์ญ์  
- ์๋ต ๋ฑ๋ก, ์์ , ์ญ์ 
- ์ ์ฒด ์ค๋ฌธ ๋ณด๊ธฐ

&nbsp;      
> **๐๊ธฐ์  ์คํ ๋ฐ ๋ฒ์ **
- JAVA 11
- Spring Boot (2.7.3)
- Spring Security (5.7.3)
- Spring Data JPA (2.7.3)
    - H2(2.1.4 test) 
-  AWS
    - EC2
    - RDS (MariaDB)      
   
&nbsp;
&nbsp;
## ๐Overview

> __Main__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/204089105-2fca96eb-3881-421b-b7d8-4f74345ec89a.png" width="800" />
</p>
&nbsp;

> __Login & Form__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/204090069-9faad37c-7358-4ade-b7cb-33032b8d9228.png" width="500" />
    <img src="https://user-images.githubusercontent.com/99247279/195881166-e4b283bf-8593-4f33-af6a-eb368d1b2b5d.png" width="190" />
</p>
&nbsp;

> __Survey & Response__
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/204089315-62f3a00b-0479-4f05-8f6b-8691ca772010.png" width="570" />
    <img src="https://user-images.githubusercontent.com/99247279/204089479-1bdf23e5-d2b0-400e-9240-2f9e3fd87aab.png" width="420" />
</p>
&nbsp;

## ๐ Architecture
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/204089207-5c9e831a-cdb3-4697-81b4-dabe131a8178.png" width="600" />
</p>
&nbsp;

## ๐ REST API
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



## ๐ฑ UML diagrams
<p align="center">
    <img src="https://user-images.githubusercontent.com/99247279/194758571-7f308f6b-4763-444b-ac2b-84accef13f49.png" width="400" />
    <img src="https://user-images.githubusercontent.com/99247279/194758544-2ea22e3d-352c-43fc-be64-d59dccdf2db4.png" width="400" />
</p>
&nbsp;

# Reference
- https://docs.spring.io/spring-boot/docs/current/reference/html/ -> Spring Boot / Reference Doc 
- https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#data-conversion-formatting -> Thymeleaf Guide doc 
- [http://logback.qos.ch] -> Logback  
- https://docs.spring.io/spring-data/jpa/docs/current/api/ -> Spring Data JPA
