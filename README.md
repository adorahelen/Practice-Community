<img width="1079" alt="image" src="https://github.com/user-attachments/assets/8e5d6f98-769b-462e-be84-3c070579c6bc">

 # URL 분석 (SQL쿼리 파라미터 방식) 
    * https://gradsch.sogang.ac.kr/front/cmsboardview.do?currentPage=1&searchField=ALL&searchValue=&searchLowItem=ALL&bbsConfigFK=403&siteId=gradsch&pkid=913320
- 	도메인: gradsch.sogang.ac.kr — 서강대학교 대학원 사이트
-  •	경로: /front/cmsboardview.do — 특정 페이지를 표시하는 경로
-  •	쿼리 파라미터:
-	•	currentPage=1: 현재 페이지 번호
-	•	searchField=ALL: 검색 필드
-	•	searchValue=: 검색 값 (빈 값)
-  •	searchLowItem=ALL: 하위 검색 항목
-	•	bbsConfigFK=403: 게시판 설정 ID
-	•	siteId=gradsch: 사이트 ID
-	•	pkid=913320: 특정 게시물의 고유 ID

    
<img width="800" alt="image" src="https://github.com/user-attachments/assets/54bd22d0-0a3d-42b7-af59-b49bf072a6e5">

- @RequestParam 을 사용하여, @GetMapping("/front/cmsboardview.do") <- 뒤에 ? (~블라블라)
- URL 에 찍혀있는 그 데이터(몇번 게시물인지 등), 서버에서 데이터를 보내주지는 않음, 뷰파일을 보여주지만 URL에 뒤에 쿼리가 찍힐뿐
- 	•	쿼리 스트링 기반 요청: RESTful 경로보다는 다양한 필터링, 검색 조건, 페이징 등과 같은 동적 요소를 전달하는 데 적합한 방식입니다.


<img width="800" alt="image" src="https://github.com/user-attachments/assets/9db3a88b-c83e-4092-93d4-4f25f99f1de7">

- •	FeedRepository의 findFeedByCriteria 메서드는 쿼리 파라미터를 기반으로 필터링된 데이터를 DB에서 바로 조회합니다.
- •	이렇게 하면, 검색어(searchValue), 하위 검색 항목(searchLowItem), 게시판 설정(bbsConfigFK), 사이트(siteId), 게시물 ID(pkid)에 따라 필터링된 게시물을 반환합니다.
- https://yourdomain.com/front/cmsboardview.do?currentPage=1&searchField=ALL&searchValue=&searchLowItem=ALL&bbsConfigFK=403&siteId=gradsch&pkid=913320


# URL 분석 (RESTful 방식)
    
    * https://nurse.jtu.ac.kr/notice/314
    
-	구조: 도메인/경로/{id} 형태
- 이 URL은 리소스를 간단하게 식별하는 방식입니다.


# 기본키 != URL
- 	•	대부분의 웹사이트가 쿼리 파라미터 방식을 사용하는 이유는 동적 조건을 쉽게 처리하고, 확장성과 유연성을 제공하기 때문입니다.
- 	RESTful 방식은 주로 고정된 리소스에 적합하지만, 복잡한 검색 조건이나 상태 정보를 다루기에는 부족합니다.
- 		•	UUID, ULID는 고유 식별자로서 뛰어나지만, 웹사이트 URL을 구성할 때 가독성과 사용자 편의성을 해치는 요소가 될 수 있어 일반적으로 사용되지 않습니다.
- 데이터베이스 내부에서 오토인크리먼트를 사용하지 않는 대신, UUID || ULID 를 사용하는 것은 효과적이나 이는 URL과 별개이다.

# URL 중요성
- 방송통신대학교 사례(쿼리 파리미터 방식이라 url 수정-> 다른 사용자 정보 보임)
    * if 문을 통해 사용자가 본인이 아니면, 볼 수 없게끔 처리 하면 됨 (코드 한줄)
