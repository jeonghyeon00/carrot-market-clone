# carrot-market-clone-kotlin-springboot
Kotlin + Spring Boot을 사용한 당근마켓 REST API 서버 클론 코딩

https://github.com/jeonghyeon00/kotlin-spring-boot-boilerplate

JWT Boilerplate 사용

## 📚 STACKS</div>
<h3> Tech Stack </h3>

- Kotlin
- Spring Boot 2.7.4
- Gradle 7.5
- PostgreSQL
- Spring Data JPA
- Spring Security
- JWT
- Redis
- STOMP


## ERD

https://dbdiagram.io/d/646304fddca9fb07c425183f

<img width="1250" alt="image" src="https://github.com/jeonghyeon00/carrot-market-clone/assets/56068392/05e946d6-f466-4f9b-85db-7a2a01d77d6f">

## 구현내용

- Spring Security를 활용한 JWT 토큰 사용, Redis를 사용한 RefreshToken 구현
- Spring Websocket & STOMP를 활용한 실시간 채팅 및 채팅 내용 저장
- 지역에 기반한 게시글 검색 및 거래 기능
- 게시글 위시리스트 및 리뷰 기능
- 리뷰로 조정되는 매너온도

## 트러블슈팅



<details>
<summary> Hibernate 프록시 적용이 안되고 바로 쿼리문이 나가는 문제</summary>

<p></p>

Hibernate는 지연로딩을 위해 Entity들을 상속하여 프록시를 만들어준다. 코틀린에서는 클래스의 기본 상속 제어자가 final이기 때문에 지연로딩으로 설정해도 프록시를 만들지 못하므로 프록시를 사용하지 못하는 문제가 발생한다. 그래서 Kotlin AllOpen 플러그인 적용으로 문제를 해결하였다.

</div>
</details>
