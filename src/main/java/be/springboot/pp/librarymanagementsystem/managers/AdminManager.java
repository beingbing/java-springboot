package be.springboot.pp.librarymanagementsystem.managers;

import be.springboot.pp.librarymanagementsystem.dtos.NewBookCopy;
import be.springboot.pp.librarymanagementsystem.entities.BookCopy;
import be.springboot.pp.librarymanagementsystem.entities.Member;
import be.springboot.pp.librarymanagementsystem.services.BookCopyService;
import be.springboot.pp.librarymanagementsystem.services.MemberService;

public class AdminManager {
    private MemberService memberService;
    private BookCopyService bookCopyService;

    // issue a book-copy
    public boolean issueBook(Long bookCopyId, Long memberId) {
        Member member = memberService.getMemberById(memberId);
        BookCopy bookCopy = bookCopyService.getBookCopyById(bookCopyId);
        if (memberService.isAllowedToBorrow(member)) {
            return memberService.issueBookToMember(bookCopy, member);
        }
        return false;
    }

    // submit a book-copy
    public boolean submitBook(Long bookCopyId) {
        return bookCopyService.submitBookCopy(bookCopyId);
    }

    // add a new book-copy to the inventory
    public boolean addBookCopy(NewBookCopy newBookCopy) {
        // TODO: validation for book-copy data
        return bookCopyService.addBookCopy(newBookCopy);
    }

    // remove old book-copy from inventory
    public boolean removeBookCopy(Long bookCopyId) {
        // TODO: validate book-copy-id
        return bookCopyService.convertBookForReference(bookCopyId);
    }

    // ban a member
    public boolean banMember(Long memberId) {
        // TODO: validation on member-id
        Member member = memberService.getMemberById(memberId);
        // TODO: validations on member
        return memberService.banMember(member);
    }

    // calculate fine amount
}
