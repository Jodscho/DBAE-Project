package de.dbae.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * <p>This class holds the implementation for the "Delete Document" and "Download Document" Button.</p>
 * 
 * 	Possible Types for the Button:
 * 	<ul>
 *   <li>download</li>
 *   <li>delete</li>
 *  </ul>
 * 
 * @author Jonathan Lochmann
 *
 */
public class DocumentButton extends TagSupport {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The type of the Button.
	 * 
	 */
	private String type;
	
	/**
	 * The ID of the Document.
	 */
	private String id;
	
	/**
	 * Get the Type of the Button.
	 * 
	 * @return The Type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the Type of the Button.
	 *  
	 * @param type The Type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter for the Document ID.
	 * 
	 * @return id The ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the ID of the Document.
	 *  
	 * @param id The ID.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Print the accurate Button.
	 */
	public int doStartTag() {
		
		String button = "";
		
		if(type.equals("download")){
			
			button = "<form action='DownloadDokument' method='get' style='float: right; margin-left: 10px;'>"
					+ "<button type='submit' class='btn btn-info pull-right btn-xs'>"
					+ "<span class='glyphicon glyphicon-download-alt'></span> Download</button>"
					+ "<input type='hidden' name='id' value='"+id+"'></form>";
			
		} else if(type.equals("delete")){
			button = "<form action='DeleteDokument' method='post' style='float: right;'>"
					+ "<button type='submit' class='btn btn-danger pull-right btn-xs'>"
					+ "<span class='glyphicon glyphicon-trash'></span> Delete</button>"
					+ "<input type='hidden' name='id' value='"+id+"'></form>";
		}

		try {
			pageContext.getOut().append(button).flush();

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
