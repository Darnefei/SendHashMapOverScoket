package launcher.controller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherFetcher {

	private static WeatherFetcher instance;

	private WeatherFetcher() {
	}

	public static WeatherFetcher getInstance() {
		if (instance == null) {
			instance = new WeatherFetcher();
		}
		return instance;
	}

	public String fetch(String city) throws Exception {

		String temperature = null;

		if (city.matches("[a-zA-Z]+")) {
			String uri = "https://api.openweathermap.org/data/2.5/weather?q=" + city
					+ ",de&mode=xml&units=metric&appid=3647b74e7c3ea97b9d0f5a46470ff4b3";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();

			Document document = documentBuilder.parse(uri);
			NodeList temps = document.getElementsByTagName("temperature");

			Node temp = temps.item(0);
			NamedNodeMap tempAttributes = temp.getAttributes();
			temperature = tempAttributes.getNamedItem("value").getNodeValue();

		} else {

			if (city.matches("[0-9]+")) {
				String uri = "https://api.openweathermap.org/data/2.5/weather?zip=" + city
						+ ",de&mode=xml&units=metric&appid=3647b74e7c3ea97b9d0f5a46470ff4b3";

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = factory.newDocumentBuilder();

				Document document = documentBuilder.parse(uri);
				NodeList temps = document.getElementsByTagName("temperature");

				Node temp = temps.item(0);
				NamedNodeMap tempAttributes = temp.getAttributes();
				temperature = tempAttributes.getNamedItem("value").getNodeValue();

			}

		}
		return temperature;

	}
}