package be.springboot.pp.librarymanagementsystem.search.members;

import be.springboot.pp.librarymanagementsystem.user.Member;

import java.util.List;

public class IdBasedMemberSearcher implements MemberSearcher {

    private final Long memberId;

    public IdBasedMemberSearcher(Long memberId) {
        this.memberId = memberId;
    }

    @Override
    public List<Member> search() {
        return List.of();
    }
}
