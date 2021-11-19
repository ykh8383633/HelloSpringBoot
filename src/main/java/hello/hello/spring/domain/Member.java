package hello.hello.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// jpa를 쓰기위해서는 Entity라는 걸 맵핑해야함
@Entity
public class Member {

    @Id // 아래 id와 pk를 맵핑해줌
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 저절로 id를 생성해 주는것 Identity 전략
    private Long id;

    //@Column(name = "username") -> 테이블의 이름이 username이라면 맵핑해줌
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
