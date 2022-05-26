package de.dbae.tag;


import java.io.IOException;


import javax.servlet.jsp.tagext.TagSupport;

import de.dbae.sql.TagOperation;

/**
 * This is the tag handler class for the getFilter.tld tag (getFilter).
 * 
 * @author Maxim Shulyatev
 */
public class FilterDoc extends TagSupport {
    
   
    private static final long serialVersionUID = 1L;
    private String kurs = "";
    
    public void setKurs(String kursid) {
        this.kurs = kursid;
    }

    /**
     * Creates the four select boxes ({@link FileType}, {@link FileFormat}, 
     * semester and uploader) for the filtering of documents.
     * 
     * @return The next action of tag.
     */
    public int doStartTag() {
        
        
        // Strings mit Quellcode fuer Select-Boxes
        String type = TagOperation.getType(kurs);
        String format = TagOperation.getFormat(kurs);
        String semester = TagOperation.getSemester(kurs);
        String uploader = TagOperation.getUploader(kurs);
        
        
        
        String sucheVerfeinern = "<form action='TagServlet' method='get'>"
            + "<div class='form-group'><table class='tableResult' ><tr><td>" 
            + "<label>Semester: </label>" + semester + "</td><td>" 
            + "<label>Format: </label>" + format + "</td><td>"
            + "<label>Type: </label> " + type + "</td><td>"
            + "<label>Uploader: </label>" + uploader + "</td></tr></table>"
            + "<input type='hidden' name='kursid' value='" + kurs + "'><br>"
            + "<button type='submit' class='btn btn-primary'> "
            + "Apply Filter</button></div></form>";
        
        try {
            pageContext.getOut().append(sucheVerfeinern).flush();

        } catch (IOException e) {
        }
        
       
        return EVAL_PAGE;
    }

    public int doAfterBody() {
        return EVAL_PAGE;
    } 

    public int doEndTag() {
       
        return EVAL_PAGE;
    }
}