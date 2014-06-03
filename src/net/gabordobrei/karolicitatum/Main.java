package net.gabordobrei.karolicitatum;

import net.gabordobrei.karolicitatum.collector.WebSourceCollector;

public class Main {

	public static void main(String[] args) {

		WebSourceCollector wsc = new WebSourceCollector();
		
		wsc.parseBible(true);
		wsc.parseBible(false);
		
	}

}
