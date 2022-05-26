package de.dbae.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * This is the tag handler class for the search.tld tag (getSearch).
 * 
 * @author Maxim Shulyatev
 */
public class Search extends TagSupport {

    private static final long serialVersionUID = 1L;
    
    /**
     * Creates the form for the search.
     * 
     * @return The next action of tag.
     */
    public int doStartTag() {
        
        // Quellcode fuer die Suche
        String sucheFormular = "<form action='SearchServlet' method='get'>"
            + "<div class='form-group'><label for='search'>Search: </label>" 
            + "<input type='text' class='form-control' name='searchStr'"
            + "required><br><button type='submit' class='btn btn-primary'> " 
            + "Suchen </button></div></form>";
        
        try {
            pageContext.getOut().append(sucheFormular).flush();

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
