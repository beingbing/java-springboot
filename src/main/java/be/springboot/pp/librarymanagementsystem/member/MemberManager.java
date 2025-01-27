package be.springboot.pp.librarymanagementsystem.member;

import be.springboot.pp.librarymanagementsystem.book.BookCopy;
import be.springboot.pp.librarymanagementsystem.user.Member;

import java.util.List;

public class MemberManager {

    public boolean blockMember(Member member) {
        return false;
    }

    public List<BookCopy> getIssuedBooksToMember(Member member) {
        return null;
    }
}
