package net.gabordobrei.karolicitatum.model;

import java.util.LinkedList;
import java.util.List;

public class Bible {
	String title = "SZENT BIBLIA, AZAZ ISTENNEK Ó ÉS ÚJ TESTAMENTOMÁBAN FOGLALTATOTT EGÉSZ SZENT ÍRÁS";
	String version = "KÁROLI GÁSPÁR";
	List<Book> books;
	
	public Bible() {
		books = new LinkedList<Book>();
	}
	
	public void addBook(Book book){
		books.add(book);
	}

}
