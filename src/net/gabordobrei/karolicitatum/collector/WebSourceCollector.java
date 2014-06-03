package net.gabordobrei.karolicitatum.collector;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import net.gabordobrei.karolicitatum.model.Bible;
import net.gabordobrei.karolicitatum.model.Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WebSourceCollector {

	List<String> book;
	String idx;

	public WebSourceCollector() {
		book = Lists.newLinkedList();
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public void getBook(String idx) throws IOException {
		this.idx = idx;
		book.clear();

		try {

			Document doc = Jsoup.connect("http://www.bibl.u-szeged.hu/Biblia/" + idx + ".html").get();

			String title = doc.body().getElementsByTag("H1").text();

			book.add(title + "\n");

			for (Element e : doc.body().getElementsByTag("P")) {

				String line = e.text();

				book.add(line);
			}

		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void writeFile() throws IOException {
		File out = new File("biblia/" + idx + ".txt");

		try {

			Files.write(Joiner.on("\n").join(book), out, Charsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Book parseBook(String idx, boolean isPretty) {

		setIdx(idx);

		File in = new File("biblia/" + idx + ".txt");

		try {

			List<String> content = Splitter.on("\n").splitToList(Files.toString(in, Charsets.UTF_8));

			Book book = new Book(content);

			Gson gson = isPretty ? new GsonBuilder().setPrettyPrinting().create() : new Gson();
			String fileName = isPretty ? "biblia.parsed/pretty/" + idx + ".json" : "biblia.parsed/" + idx + ".json";
			Files.write(gson.toJson(book), new File(fileName), Charsets.UTF_8);

			return book;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void parseBible(boolean isPretty) {

		Bible bible = new Bible();
		try {

			for (int i = 1; i < 74; i++) {
				String idx = i < 10 ? "0" + String.valueOf(i) : String.valueOf(i);
				System.out.println(idx);

				Book book = parseBook(idx, isPretty);

				if (book != null) {
					bible.addBook(book);
				}
			}

			Gson gson = isPretty ? new GsonBuilder().setPrettyPrinting().create() : new Gson();

			String fileName = isPretty ? "biblia.parsed/pretty/biblia.json" : "biblia.parsed/biblia.json";
			Files.write(gson.toJson(bible), new File(fileName), Charsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
