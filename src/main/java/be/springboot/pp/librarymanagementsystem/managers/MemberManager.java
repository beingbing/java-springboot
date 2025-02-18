package be.springboot.pp.librarymanagementsystem.managers;

import be.springboot.pp.librarymanagementsystem.entities.BookCopy;
import be.springboot.pp.librarymanagementsystem.entities.Member;
import be.springboot.pp.librarymanagementsystem.services.MemberService;

import java.util.List;

public class MemberManager {
    private MemberService memberService;

    // search a member by name

    // get issued books to a member
    public List<BookCopy> getIssuedBooksToMember(Long memberId) {
        Member member = memberService.getMemberById(memberId);
        // TODO: required validations
        if (member.getBorrowedBooksCount() > 0) return memberService.booksIssuedToMember(member);
        return null;
    }
}
