package com.lays.indexer.fileparser;

import com.lays.indexer.Document;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.charset.Charset;

public class TextParser extends FileParser {
    public TextParser() {
        super(new String[]{"txt"});
    }

    @Override
    public String Parse(Part filepart) throws IOException {
        return IOUtils.toString(filepart.getInputStream(), Charset.forName("utf-8"));
    }
    @Override
    public void handleResponse(Document doc, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "filename=\""+doc.getName()+"\"");
//        ServletOutputStream outputStream = null;

        try (ServletOutputStream outputStream = response.getOutputStream()){
            outputStream.write(doc.getBinaryContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
