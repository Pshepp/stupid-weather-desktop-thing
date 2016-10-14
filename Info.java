package ass;

import java.io.InputStream;
import java.net.URL;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Info {
	static String hum, temp, temH, temL, loc, cond;
	private static Document doc;
	private static final int locCode=2356940;
	private static DocumentBuilderFactory domF = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder bdr;

	public static void Update(){
		try{
			URL xURL = new URL("https://query.yahooapis.com/v1/public/yql?=xml&q=SELECT%20*%20FROM%20weather.forecast%20WHERE%20u=%27f%27%20AND%20woeid%20=%20%27"+locCode+"%27");//use yahoo search query instead of apis to grab xml then parse l8r
			InputStream iS = xURL.openStream();
			pLxml(iS);
		} catch (Exception ex){
			System.err.println("XML fucked up sorry");
			System.exit(1);
		}
		Node n;	Element e;
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("results");
  	for (int i = 0; i < nList.getLength(); i++) {
			if (nList.item(i).getNodeType()!=Node.ELEMENT_NODE){
				continue;
			} else {
				try{
				n = ((Element) nList.item(i)).getElementsByTagName("yweather:location").item(0);
      	e = (Element) n;
      	loc = e.getAttribute("city");
        loc +=",";
      	loc += e.getAttribute("region");

     		n = ((Element) nList.item(i)).getElementsByTagName("yweather:condition").item(0);
        e = (Element) n;
        temp=e.getAttribute("temp");
        cond=e.getAttribute("text");

				n = ((Element) nList.item(i)).getElementsByTagName("yweather:forecast").item(0);
        e = (Element) n;
        temH=e.getAttribute("high");
        temL=e.getAttribute("low");

        n = ((Element) nList.item(i)).getElementsByTagName("yweather:atmosphere").item(0);
        e = (Element) n;
        hum = e.getAttribute("humidity");
				} catch (Exception ex){
					
				}
  	 	}//endif
  	}//edn4
	}

	private static void pLxml(InputStream iS) {//parse and loads xml
		try {
		bdr = domF.newDocumentBuilder();
    	domF.setValidating(false);
    	domF.setNamespaceAware(false);
    	doc=null;
    	doc = bdr.parse(iS);
  	} catch (Exception ex) {
      System.err.println("unable to load XML: " + ex);
  	}
	}

}
