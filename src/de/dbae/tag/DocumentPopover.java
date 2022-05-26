package de.dbae.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import de.dbae.administration.Dokument;

/**
 * 
 * This class holds the implementation for the Popover which is used to display useful 
 * Information about a specific Document.
 * 
 * @author Jonathan Lochmann
 *
 */
public class DocumentPopover extends TagSupport {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The Document which is displayed by this popover.
	 */
	private Dokument dok;

	/**
	 * Getter for the Document.
	 *  
	 * @return The Document.
	 */
	public Dokument getDok() {
		return dok;
	}
	
	/**
	 * Setter for the Document.
	 * 
	 * @param dok The Document.
	 */
	public void setDok(Dokument dok) {
		this.dok = dok;
	}

	/**
	 * Print the Popover with the Information.
	 */
	public int doStartTag() {
		
		String popover = "<a style='color:black;' data-html='true' data-toggle='popover' data-trigger='hover' data-content='"
				+ "<b>Kurzbeschreibung:</b> " + dok.getBeschreibung()
				+ "<br><b>Uploader:</b> " + dok.getUploader()
				+ "<br><b>Datum:</b> " + dok.getPresentableDate()
				+" <br><b>Groesse:</b> " + dok.getPresentableSize()
				+" <br><b>Semester:</b> " + dok.getSemester()
				+" <br><b>Type:</b> " + dok.getType()
				+" <br><b>Format:</b> " + dok.getFormat()+"'>"
				+ "<span class='glyphicon glyphicon-info-sign hoverglyph'></span> </a>";
		try {
			pageContext.getOut().append(popover).flush();

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
