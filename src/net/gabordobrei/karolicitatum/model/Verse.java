package net.gabordobrei.karolicitatum.model;

import java.util.List;

public class Verse extends Object {
	int number;
	String cited_text;
	String text;
	List<Cite> cites;
	
	public Verse(int number, String cited_text) {
		this.number = number;
		this.cited_text = cited_text;

		cites = Cite.get(cited_text);
		
		text = cited_text.replaceAll("\\[.+?\\]", "*").replaceAll("  ", " ");
	}
}
