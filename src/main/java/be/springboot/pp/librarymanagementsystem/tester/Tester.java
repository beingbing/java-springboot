package be.springboot.pp.librarymanagementsystem.tester;

import be.springboot.pp.librarymanagementsystem.auth.UserAuthenticator;
import be.springboot.pp.librarymanagementsystem.dtos.NewBookCopy;
import be.springboot.pp.librarymanagementsystem.entities.BookCopy;
import be.springboot.pp.librarymanagementsystem.entities.Member;
import be.springboot.pp.librarymanagementsystem.inventory.InventoryManager;
import be.springboot.pp.librarymanagementsystem.managers.MemberManager;
import be.springboot.pp.librarymanagementsystem.search.books.AuthorBasedBookSearcher;
import be.springboot.pp.librarymanagementsystem.search.books.BookSearcher;
import be.springboot.pp.librarymanagementsystem.search.books.IdBasedBookSearcher;
import be.springboot.pp.librarymanagementsystem.search.books.NameBasedBookSearcher;
import be.springboot.pp.librarymanagementsystem.search.members.IdBasedMemberSearcher;
import be.springboot.pp.librarymanagementsystem.search.members.MemberSearcher;
import be.springboot.pp.librarymanagementsystem.search.members.NameBasedMemberSearcher;
import be.springboot.pp.librarymanagementsystem.services.BookCopyService;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/*
* Whenever writing an API, always validate the parameters.
* */
public class Tester {
    private final InventoryManager inventoryManager;
    private final MemberManager memberManager;
    private BookCopyService bookCopyService;

    public Tester(InventoryManager inventoryManager, MemberManager memberManager) {
        this.inventoryManager = inventoryManager;
        this.memberManager = memberManager;
    }

    public List<BookCopy> searchBookByName(String bookName) {
        if (ObjectUtils.isEmpty(bookName))
            throw new IllegalArgumentException("book name can not be null");

        BookSearcher bookSearcher = new NameBasedBookSearcher(bookName);
        return bookSearcher.search();
    }

    public List<BookCopy> searchBookByAuthor(List<String> authors) {
        if (ObjectUtils.isEmpty(authors) || authors.isEmpty())
            throw new IllegalArgumentException("author names can't be null or empty");

        BookSearcher bookSearcher = new AuthorBasedBookSearcher(authors);
        return bookSearcher.search();
    }

    public boolean isBookCopyAvailable(Long bookCopyId) {
        if (ObjectUtils.isEmpty(bookCopyId))
            throw new IllegalArgumentException("no book-copy id provided");

        BookSearcher bookSearcher = new IdBasedBookSearcher(bookCopyId);
        List<BookCopy> bookCopyList = bookSearcher.search();
        if (bookCopyList.isEmpty()) return false;
        BookCopy bookCopy = bookCopyList.getFirst();
        return bookCopy.isAvailable();
    }

    public List<Member> searchMemberByName(String memberName, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        if (ObjectUtils.isEmpty(memberName))
            throw new IllegalArgumentException("member name can not be null");

        MemberSearcher memberSearcher = new NameBasedMemberSearcher(memberName);
        return memberSearcher.search();
    }

    public Member searchMemberById(Long memberId, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        if (ObjectUtils.isEmpty(memberId))
            throw new IllegalArgumentException("member id can not be null");

        MemberSearcher memberSearcher = new IdBasedMemberSearcher(memberId);
        List<Member> members = memberSearcher.search();
        return members.isEmpty() ? null : members.getFirst();
    }

    public boolean addBook(String name, List<String> authors, Date publicationDate, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        /*
        * TODO: validation for book details
        * */

        return bookCopyService.addBookCopy(new NewBookCopy());
    }

    public boolean deleteBook(Long bookCopyId, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        /*
         * TODO: validation for book copy id
         * */

        BookSearcher bookSearcher = new IdBasedBookSearcher(bookCopyId);
        List<BookCopy> bookCopyList = bookSearcher.search();
        if (ObjectUtils.isEmpty(bookCopyList) || bookCopyList.isEmpty())
            throw new RuntimeException("No book copy found for given id");

        return inventoryManager.deleteBookCopy(bookCopyList.getFirst());
    }

    public boolean blockMember(Long memberId, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        /*
         * TODO: validation for member id
         * */

        MemberSearcher memberSearcher = new IdBasedMemberSearcher(memberId);
        List<Member> memberList = memberSearcher.search();
        if (ObjectUtils.isEmpty(memberList) || memberList.isEmpty())
            throw new RuntimeException("No book copy found for given id");

        return memberManager.blockMember(memberList.getFirst());
    }

    public boolean issueBookToMember(Long bookCopyId, Long memberId, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        /*
         * TODO: validation for book copy id and member id
         * */

        MemberSearcher memberSearcher = new IdBasedMemberSearcher(memberId);
        List<Member> memberList = memberSearcher.search();
        if (ObjectUtils.isEmpty(memberList) || memberList.isEmpty())
            throw new RuntimeException("No book copy found for given id");

        BookSearcher bookSearcher = new IdBasedBookSearcher(bookCopyId);
        List<BookCopy> bookCopyList = bookSearcher.search();
        if (ObjectUtils.isEmpty(bookCopyList) || bookCopyList.isEmpty())
            throw new RuntimeException("No book copy found for given id");

        return inventoryManager.issueBookCopy(bookCopyList.getFirst(), memberList.getFirst());
    }

    public boolean submitBook(Long bookCopyId, Long memberId, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        /*
         * TODO: validation for book copy id and member id
         * */

        MemberSearcher memberSearcher = new IdBasedMemberSearcher(memberId);
        List<Member> memberList = memberSearcher.search();
        if (ObjectUtils.isEmpty(memberList) || memberList.isEmpty())
            throw new RuntimeException("No book copy found for given id");

        BookSearcher bookSearcher = new IdBasedBookSearcher(bookCopyId);
        List<BookCopy> bookCopyList = bookSearcher.search();
        if (ObjectUtils.isEmpty(bookCopyList) || bookCopyList.isEmpty())
            throw new RuntimeException("No book copy found for given id");

        return inventoryManager.submitBookCopy(bookCopyList.getFirst(), memberList.getFirst());
    }

    public Member getBorrowerOfBook(Long bookCopyId, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        /*
         * TODO: validation for book copy id
         * */

        BookSearcher bookSearcher = new IdBasedBookSearcher(bookCopyId);
        List<BookCopy> bookCopyList = bookSearcher.search();
        if (ObjectUtils.isEmpty(bookCopyList) || bookCopyList.isEmpty())
            throw new RuntimeException("No book copy found for given id");

        return inventoryManager.getBorrower(bookCopyList.getFirst());
    }

    public List<BookCopy> getIssuedBooksToMember(Long memberId, String adminToken) throws IllegalAccessException {
        if (!UserAuthenticator.isAdmin(adminToken))
            throw new IllegalAccessException("Only admins are authorized to interact with this interface");

        /*
         * TODO: validation for member id
         * */

        MemberSearcher memberSearcher = new IdBasedMemberSearcher(memberId);
        List<Member> memberList = memberSearcher.search();
        if (ObjectUtils.isEmpty(memberList) || memberList.isEmpty())
            throw new RuntimeException("No book copy found for given id");

        return memberManager.getIssuedBooksToMember(memberList.getFirst());
    }
}
