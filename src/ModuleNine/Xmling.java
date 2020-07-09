package ModuleNine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Xmling {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Taking input path input as string.
            System.out.print("Enter path to XML file to validate: ");
            String xmlPath = scanner.nextLine();

            DvdXMLUtil xmlUtil = new DvdXMLUtil();

            // Explicit XML validation 
            if (xmlUtil.parse(xmlPath) != null) {
                System.out.println("XML file is valid.");
            }

            // Aggregation by decade
            System.out.println("---------------------------\nAggregating dvds by decade and showing:");
            xmlUtil.aggregateDvdsByDecade(xmlPath);

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

class XMLUtil {

    protected DocumentBuilder _domBuilder;

    XMLUtil() throws ParserConfigurationException {
        createDOMBuilder();
    }

    private void createDOMBuilder() throws ParserConfigurationException {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            _domBuilder = factory.newDocumentBuilder();

            _domBuilder.setErrorHandler(new ErrorHandler() {

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    System.out.println("WARNING: " + exception.getMessage());
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    System.out.println("FATAL: " + exception.getMessage());
                    throw exception;
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    System.out.println("ERROR: " + exception.getMessage());
                    throw exception;
                }
            });

        } catch (ParserConfigurationException pcException) {
            System.out.println("Could not configure DOM builder. Exception: " + pcException.getMessage());
            throw pcException;
        }
    }

    public Document parse(String xmlPath) throws IOException, SAXException {
        try {
            return _domBuilder.parse(xmlPath);
        } catch (IOException ioException) {
            System.out.println("File IO exception: " + ioException.getMessage());
            throw ioException;
        } catch (SAXException saxException) {
            System.out.println("Invalid XML file. Error: " + saxException.getMessage());
            throw saxException;
        }
    }
}

class DvdXMLUtil extends XMLUtil {

    public DvdXMLUtil() throws ParserConfigurationException {
        super();
    }

    public void aggregateDvdsByDecade(String dvdXMLPath) throws IOException, TransformerException, SAXException {

        try {
            var dvdInputDoc = parse(dvdXMLPath);
            var dvdDecades = getDvdDecadeCounts(dvdInputDoc);
            var dvdSummaryDom = createDvdSummaryDom(dvdDecades);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(dvdSummaryDom);
            StreamResult streamResult = new StreamResult(System.out);

            transformer.transform(domSource, streamResult);
        } catch (TransformerException transformerException) {
            System.out.println("Exception while creating XML output. Error: " + transformerException.getMessage());
            throw transformerException;
        }
    }

    private Document createDvdSummaryDom(HashMap<Integer, Integer> dvdDecades) {
        var outputDocument = _domBuilder.newDocument();
        var summaryNode = outputDocument.createElement("summary");

        dvdDecades.forEach((key, value) -> {
            // create Count element
            var decadeCountElement = outputDocument.createElement("count");
            decadeCountElement.setAttribute("decade", key.toString());
            decadeCountElement.appendChild(outputDocument.createTextNode(value.toString()));

            summaryNode.appendChild(decadeCountElement);
        });

        var rootElement = outputDocument.createElement("DVD");
        rootElement.appendChild(summaryNode);

        outputDocument.appendChild(rootElement);

        return outputDocument;
    }

    private HashMap<Integer, Integer> getDvdDecadeCounts(Document dvdInputDoc) {

        var dvdDecades = new HashMap<Integer, Integer>();
        var dvdYearNodes = dvdInputDoc.getElementsByTagName("release_year");

        for (int yearNodeIndex = 0; yearNodeIndex < dvdYearNodes.getLength(); yearNodeIndex++) {
            var yearNode = dvdYearNodes.item(yearNodeIndex);
            var nodeYear = Integer.parseInt(yearNode.getTextContent());
            var nodeDecade = (nodeYear / 10) * 10;

            if (dvdDecades.containsKey(nodeDecade)) {
                var presentDecadeCount = dvdDecades.get(nodeDecade);
                dvdDecades.put(nodeDecade, presentDecadeCount + 1);
            } else {
                dvdDecades.put(nodeDecade, 1);
            }
        }

        return dvdDecades;
    }

}