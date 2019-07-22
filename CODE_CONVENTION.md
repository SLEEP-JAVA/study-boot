## MINZY
### Entity에 Setter를 쓰지 않는다.

- 단, dto에는 허용
- 이유
    - 개인적으로 도메인 안에 로직이 있어야 한다고 생각. 무분별하게 setter method를 사용하면 객체의 상태를 언제든 바꿀 수 있음. 
      → 언제 어떻게 바뀌는지 일일 따라가야 파악할 수 있고 수정을 사항이 생기면 복잡함!
```java
// setter를 쓰게 되면...
@Setter
public class Study {
    private StudyState studyState;
}
    
@Test
pubic void close_Study() {
    Study study = new Study();
    study.setStudyState(StudyState.CLOSE);
}
    
// setter를 안쓰면^^
public class Study {
    private SutdyState sutdyState;

    // 이름에서 어떤 역할을 하는 메서드인지 바로 알 수 있음.
    public closeStudy() {
        studyState = StudyState.CLOSE;
    }
}
    
@Test
pubic void close_Study() {
    Study study = new Study();
    study.closeStudy();
}
```

### Field Injection이 아닌 Constructor injection을 사용하자.

- 이유
    1. DI container에서 관리되는 클래스는 독립적으로 인스턴스화할 수 있는 단순한 POJO → DI 컨테이너를 실행하지 않고도 단위 테스트 가능 
       → 그런데 field injection을 하면 모든 빈을 다 띄우고 테스트를 해야해. 왜냐면 해당 클래스를 DI container가 관리해주니까! 
    2. constructor injection은 필드를 final로 선언할 수 있음. 그렇게 되면 불변객체로 쓸 수 있지만 field injection은 불변객체로 쓸 수없어서 중간에 상태가 바뀔 수도 있음.
```java
// field injection
public class StudyController {
    @Autowired 
    private StudyService studyService;
     ...
}
    
// constructor injection
public clss StudyController {
    private final StudyService studyService;
    
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }
}
```

### @Builder를 사용한다면 클래스가 아닌 생성자 레벨에서 사용하자.
- 개인적으로 객체 생성시 builder를 사용하는 방법을 좋아함. 명시적으로 어떤 필드에 값이 들어가는지 보여서.
- 이유: class 레벨로 사용하면 모든 필드의 상태를 변경할 수 있음. 그러나 그렇게 되면 원하지 않는 필드들에도 접근해서 바꿀 수 있음.(예를 들면 id?)

### if/else 코드에서 else 사용을 지양하고 early return 하자.
- 이유: 특히 if 안에 또 if/else 들어 가는 경우! 가독성 매우 떨어짐.

### 하나의 메서드에는 하나의 indent만!
```java
// 이렇게 쓰지말자
public int evenSum() {
    ...
    for (i=0; i < 10; i++) {
        if (i % 2 == 0) {
            ...
        }
    }
}

```

### Reference
- [스프링부트로 웹 서비스 출시하기 - 2. SpringBoot & JPA로 간단 API 만들기](https://jojoldu.tistory.com/251)
- [DI(의존성 주입)가 필요한 이유와 Spring에서 Field Injection보다 Constructor Injection이 권장되는 이유](http://www.mimul.com/pebble/default/2018/03/30/1522386129211.html)


### 한국어 발음대로의 표기 금지
[avoid-korean-pronounce]

식별자의 이름을 한글 발음을 영어로 옮겨서 표기하지 않는다. 한국어 고유명사는 예외이다.

나쁜 예 : moohyungJasan (무형자산)

좋은 예 : intangibleAssets (무형자산)

### 패키지 이름은 소문자로 구성

패키지 이름은 소문자를 사용하여 작성한다. 단어별 구문을 위해 언더스코어(_)나 대문자를 섞지 않는다.

### 한 줄에 한 문장

### 메서드 이름은 동사/전치사로 시작
메서드명은 기본적으로는 동사로 시작한다. 다른 타입으로 전환하는 메서드나 빌더 패턴을 구현한 클래스의 메서드에는 전치사를 쓸 수 있다.

좋은 예
동사사용 : renderHtml()

전환메서드의 전치사 : toString()

Builder 패턴 적용한 클래스의 메서드의 전치사 : withUserId(String id)

### 테스트 코드는 최대한 한글로 

테스트 코드는 api 명세와 같기때문에 알아보기 쉽게 하면 좋을 것 같습니다. 

### 임시 변수 외에는 1 글자 이름 사용 금지

메서드 블럭 범위 이상의 생명 주기를 가지는 변수에는 1글자로 된 이름을 쓰지 않는다. 반복문의 인덱스나 람다 표현식의 파라미터 등 짧은 범위의 임시 변수에는 관례적으로 1글자 변수명을 사용할 수 있다.

### long형 값의 마지막에 `L`붙이기
long형의 숫자에는 마지막에 대문자 'L’을 붙인다. 소문자 'l’보다 숫자 '1’과의 차이가 커서 가독성이 높아진다.

