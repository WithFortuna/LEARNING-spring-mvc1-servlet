package groupName.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    //static을 씀으로써 Map, sequence 객체는 싱글톤,
    private static Map<Long, Member> store = new HashMap<>(); //실제론 동시성 문제 때문에 concurrentHashMap, AtomicLong 고려
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository(); //MemberRepository는 싱글톤 (스프링을 쓴다면 자동으로 적용해줬을 것)

    public static MemberRepository getInstance() {
        return instance;
    }


    private MemberRepository() {

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());

    }

    public void clearStore() {
        store.clear();
    }


}
