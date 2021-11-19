package hello.hello.spring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) { // html파일에서 입력하면 스프링이 이 함수를 호출해 이름을 저장
        this.name = name;
    }
}
