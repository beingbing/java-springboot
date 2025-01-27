package be.springboot.pp.librarymanagementsystem.search.members;

import be.springboot.pp.librarymanagementsystem.user.Member;

import java.util.List;

public class NameBasedMemberSearcher implements MemberSearcher {

    private final String memberName;

    public NameBasedMemberSearcher(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public List<Member> search() {
        return List.of();
    }
}
