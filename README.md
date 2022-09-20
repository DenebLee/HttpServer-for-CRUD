# History
## TODO
1. TodoServiceTestImpl 구현
2. TodoServiceTestImpl 테스트 작성
3. TodoServicePostgreSQLImpl 구현
4. TodoServicePostgreSQLImpl 테스트 작성

## DONE
- UserService.class 테스트 코드 작성 및 내부 코드 수정 ✔
- PostHandler.class 테스트 코드 작성 ✔
- PatchHandler.class 테스트 코드 작성 ✔
- DeleteHandler.class 테스트 코드 작성 ✔
- TodoHandler 시작 ✔
- PostgreSqlDbcp 구현 ✔
- PostgreSqlDbcp 테스트 작성 ✔
- UserServicePostgreSQLImpl 구현 ✔ 
- UserServicePostgreSQLImpl 테스트 작성 ✔
### COMMIT
####  2022-09-02
- http server 생성
- /user 패스에 GET, POST, PATCH, DELETE 핸들러 생성
- 고정값 리턴
#### 2022-09-06
- @GET,@POST,@DELETE,@PATCH Handler 생성
- @GET Handler 최적화 및 생성 
- Refactoring
#### 2022-09-14
- @GET,@POST,@DELETE,@PATCH TestHandler 코드 작성 완료 
- UserTest 
- UserServiceTestImpl 테스트 코드 작성 완료 
- TODO 작업 시작
#### 2022-09-16
- UserServicePostgreSQLImplTest 작성 
  - CreateTable 테스트 코드 작성 완료 
  - Save(user) 테스트 코드 작성 완료 
- Exception 관련 지식 습득
