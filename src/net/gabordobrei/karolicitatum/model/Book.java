package net.gabordobrei.karolicitatum.model;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Book {
	String title;
	List<Chapter> chapters;

	public Book(List<String> book) {
		title = book.get(0);

		chapters = new LinkedList<Chapter>();

		Table<Integer, Integer, String> table = HashBasedTable.create();

		for (int i = 1; i < book.size(); i++) {
			List<String> line = splitLine(book.get(i));
			if (!line.isEmpty()) {
				table.put(Integer.parseInt(line.get(0)), Integer.parseInt(line.get(1)), line.get(2));
			}
		}

		for (int i = 0; i < table.rowKeySet().size() + 1; i++) {
			List<Verse> verses = new LinkedList<Verse>();

			for (int j = 0; j < table.row(i + 1).size(); j++) {
				verses.add(new Verse(j + 1, table.get(i + 1, j + 1)));
			}

			if (i != table.rowKeySet().size()) {
				chapters.add(new Chapter(i + 1, verses));
			}
		}

	}

	private List<String> splitLine(String line) {
		Pattern REGEX = Pattern.compile("^([0-9]+?),([0-9]+?) (.+?)$");

		List<String> values = new LinkedList<String>();
		Matcher matcher = REGEX.matcher(line);

		while (matcher.find()) {
			values.add(matcher.group(1));
			values.add(matcher.group(2));
			values.add(matcher.group(3));
		}

		return values;

	}
}
