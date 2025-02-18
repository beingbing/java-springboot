package be.springboot.pp.librarymanagementsystem.search.members;

import be.springboot.pp.librarymanagementsystem.entities.Member;

import java.util.List;

public interface MemberSearcher {
    List<Member> search();
}
