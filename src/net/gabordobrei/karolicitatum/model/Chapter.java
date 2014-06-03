package net.gabordobrei.karolicitatum.model;

import java.util.LinkedList;
import java.util.List;

public class Chapter {
	int number;
	List<Verse> verses;

	public Chapter(int number, List<Verse> verses) {
		this.number = number;
		this.verses = new LinkedList<Verse>();
		this.verses.addAll(verses);
	}
}
