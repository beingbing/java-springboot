package be.springboot.pp.librarymanagementsystem.search.members;

import be.springboot.pp.librarymanagementsystem.user.Member;

import java.util.List;

public interface MemberSearcher {

    List<Member> search();

}
