package net.gabordobrei.karolicitatum.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class Cite {
	String link;
	String displayable_name;
	int offset;

	static Pattern citeGroupRegex = Pattern.compile("\\[(.+?)\\]");
	static Pattern REGEX = Pattern.compile("([1-5 ]{0,2}[\\p{L}r]{3,5}[\\.]?) ([0-9\\.,-]*)");
	final static List<String> labels = Lists.newArrayList("rész", "1 Móz.", "2 Móz.", "3 Móz.", "4 Móz.", "5 Móz.",
			"Józs.", "Bir.", "Ruth.", "1 Sám.", "2 Sám.", "1 Kir.", "2 Kir.", "1 Krón.", "2 Krón.", "Ezsdr.", "Nehem.",
			"Eszt.", "Jób.", "Zsolt.", "Péld.", "Préd.", "Én.", "Ésa.", "Jer.", "Siral.", "Ezék.", "Dán.", "Hós.",
			"Jóel.", "Ámós.", "Abd.", "Jón.", "Mik.", "Náh.", "Hab.", "Sof.", "Agge.", "Zak.", "Malak.", "Mát.",
			"Márk.", "Luk.", "Ján.", "Csel.", "Róm.", "1 Kor.", "2 Kor.", "Gal.", "Eféz.", "Fil.", "Kol.", "1 Thess.",
			"2 Thess.", "1 Tim.", "2 Tim.", "Tit.", "Filem.", "Zsid.", "Jak.", "1 Pét.", "2 Pét.", "1 Ján.", "2 Ján.",
			"3 Ján.", "Júd.", "Jel.");

	public Cite(String link, String displayable_name, int offset) {
		this.link = link;
		this.displayable_name = displayable_name;
		this.offset = offset;
	}

	public static List<Cite> get(String text) {
		List<Cite> toReturn = Lists.newLinkedList();

		Matcher citeGroupMatcher = citeGroupRegex.matcher(text);
		int index = 0, offs = 0;
		
		while (citeGroupMatcher.find()) {
			String citeText = citeGroupMatcher.group(1);
			Matcher matcher = REGEX.matcher(citeText);

			String temp = text;
			String o = temp.replaceAll("\\[.+?\\]", "*").replaceAll("  ", " ");
			List<String> offsetString = Splitter.on("*").splitToList(o);

			offs += offsetString.get(index).length() - index;
			index++;

			while (matcher.find()) {
				String label = matcher.group(1);
				String number = matcher.group(2);

				if (label.startsWith(" "))
					label = label.substring(1);

				if (labels.contains(label) && !Strings.isNullOrEmpty(label + number)) {
					String cite = label + " " + number;

					toReturn.add(new Cite(cite, cite, offs));
				}

			}
		}

		return toReturn;
	}

}
