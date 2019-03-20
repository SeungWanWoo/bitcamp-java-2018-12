package ch29.j;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

//@Configuration 
// 스프링 IoC 컨테이너에게 이 클래스를 직접 알려주기 때문에 이 애노테이션은 필요없다.
// 애노테이션으로 특정 조건의 패키지나 클래스를 관리 대상에서 제외하기
@ComponentScan(
    basePackages={"ch29.j2", "ch29.j"},
    // 애노테이션 내부에 프로퍼티 값으로 애노테이션을 줄 수 있다.
    // 여러개의 값을 설정하려면 중괄호{}를 생략할 수 없다.
    excludeFilters= {
        @ComponentScan.Filter(
            type=FilterType.REGEX,
            pattern="ch29.j.Car"
        ), 
        @ComponentScan.Filter(
            type=FilterType.REGEX,
            pattern="ch29.j.AppConfig2"
        )
    }
) 
public class AppConfig3 {
  
}
