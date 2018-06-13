package zohar.com.fristreview;

import android.support.v7.widget.DialogTitle;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {

    private static final String TAG = "ContentHandler";

    private String node;

    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        node = localName;
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("id".equals(node)){
            id.append(ch,start,length);
        }else if ("name".equals(node)){
            name.append(ch, start, length);
        } else if ("version".equals(node)){
            version.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(node)){
            Log.d(TAG,"id is "+ id);
            Log.d(TAG,"name is "+ name);
            Log.d(TAG,"version is "+ version);
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
