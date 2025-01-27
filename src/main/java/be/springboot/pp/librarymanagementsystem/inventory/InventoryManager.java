package be.springboot.pp.librarymanagementsystem.inventory;

import be.springboot.pp.librarymanagementsystem.book.BookCopy;
import be.springboot.pp.librarymanagementsystem.user.Member;

public class InventoryManager {

    public boolean addBookCopy(BookCopy bookCopy) {
        return false;
    }

    public boolean deleteBookCopy(BookCopy bookCopy) {
        return false;
    }

    public boolean issueBookCopy(BookCopy bookCopy, Member member) {
        return false;
    }

    public boolean submitBookCopy(BookCopy bookCopy, Member member) {
        return false;
    }

    public Member getBorrower(BookCopy bookCopy) {
        return null;
    }
}
