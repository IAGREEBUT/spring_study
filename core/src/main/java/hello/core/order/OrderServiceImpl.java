package hello.core.order;

import hello.core.discount.DisountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{


    private final MemberRepository memberRepository;// -> 오직 인터페이스만 존재 (추상화에만 의존함 DIP)
    private final DisountPolicy disountPolicy; //인터페이스 (추상)에만 의존함 (DIP)
    // 어떤 구현체가 들어올지 전혀 모르는 상태다

//    추상뿐 아니라 구현클래스에도 의존중
//    private final DisountPolicy disountPolicy = new FixDiscountPolicy();
//    private final DisountPolicy disountPolicy = new RateDiscountPolicy();

    public OrderServiceImpl(MemberRepository memberRepository, DisountPolicy disountPolicy){
        this.disountPolicy =disountPolicy;
        this.memberRepository=memberRepository;
    }



    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) { //1. 주문 생성

        // 2. 회원 조회
        Member member = memberRepository.findById(memberId);


        // 3. 할인 적용
        //할인에 대한 내용은 Order에서 신경쓰지 않고 결과만 사용한, discountPolicy에게 일임함 -> 잘 된 설계 : 단일 체계의 원칙이 잘 지켜짐
        // 할인에 대한 변경이 들어올 때 OrderService는 한줄도 변화하지 않는 상태임
        int discountPrice = disountPolicy.discount(member, itemPrice); //최종적으로 할인된 가격

        // 4. 주문 결과 반환
        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 생성하여 반환됨
    }
}
