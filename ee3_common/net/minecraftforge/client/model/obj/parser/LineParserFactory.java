package net.minecraftforge.client.model.obj.parser;

import java.util.Hashtable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.model.obj.WavefrontObject;

@SideOnly(Side.CLIENT)
public abstract class LineParserFactory {

    protected Hashtable<String, LineParser> parsers = new Hashtable<String, LineParser>();
    protected WavefrontObject object = null;

    public LineParser getLineParser(String line) {

        if (line == null)
            return null;

        //String[] lineWords = line.split(" ");		// Nhaaaaaaaaaaa, 3DS max doesn't use clean space but some other shity character :( !

        // So I could use something like String regularExpression = "[A-Za-z]*([^\\-\\.0-9]*(\\-\\.0-9]*))";
        // .
        // .
        // .
        // or be nasty :P
        //line = line.replaceAll("[^ \\.\\-A-Za-z0-9#/]","");
        line = line.replaceAll("  ", " ");
        line = line.replaceAll("	", "");
        String[] lineWords = line.split(" ");

        // lineType is the first word in the line (except v,vp,vn,vt)

        if (lineWords.length < 1)
            return new DefaultParser();
        ;

        String lineType = lineWords[0];

        LineParser parser = parsers.get(lineType);
        if (parser == null) {
            //System.out.println("ParserFactory: Cannot find the type of the line "+filename+"#"+lineNumber+"for key:'"+lineType+"' , returning default constructor");
            parser = new DefaultParser();
        }

        parser.setWords(lineWords);
        return parser;
    }
}
