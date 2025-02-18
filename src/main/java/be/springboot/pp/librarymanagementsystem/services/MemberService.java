package be.springboot.pp.librarymanagementsystem.services;

import be.springboot.pp.librarymanagementsystem.entities.BookCopy;
import be.springboot.pp.librarymanagementsystem.entities.Member;
import be.springboot.pp.librarymanagementsystem.enums.BookStatus;

import java.util.List;

import static be.springboot.pp.librarymanagementsystem.constants.Constants.BORROWING_LIMIT;

public class MemberService {

    public Member getMemberById(Long memberId) {
        //
        return null;
    }

    public boolean isAllowedToBorrow(Member member) {
        return member.getBorrowedBooksCount() < BORROWING_LIMIT;
    }

    public boolean issueBookToMember(BookCopy bookCopy, Member member) {
        bookCopy.setBookStatus(BookStatus.BORROWED);
        //
        return false;
    }

    public List<BookCopy> booksIssuedToMember(Member member) {
        //
        return null;
    }

    public Boolean banMember(Member member) {
        member.setIsBanned(true);
        // TODO: save member data
        return true;
    }
}
