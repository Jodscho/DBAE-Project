package de.dbae.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * <p>This class holds the implementation for a single Menu Item displayed by the Star symbol 
 * on the admin page and the premium user page.</p>
 * 
 * Types of the Menu-Item:
 * <ul>
 * 	<li>rename</li>
 * 	<li>delete</li>
 * 	<li>download</li>
 * 	<li>notDeprecated</li>
 * </ul>
 * 
 * @author Jonathan Lochmann
 *
 */
public class StarItemCourse extends TagSupport {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The type of the Menu-Item.
	 */
	private String type;
	
	/**
	 * The ID of the Course.
	 */
	private String id;
	
	/**
	 * The name of the Course (optional).
	 */
	private String name;
	
	/**
	 * Getter for the Name of the Course.
	 * 
	 * @return The Name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the Name.
	 * 
	 * @param name The Name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the Type.
	 * 
	 * @return The Type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for the Type.
	 * 
	 * @param type The Type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter for the ID.
	 * 
	 * @return id The ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Setter for the ID.
	 * 
	 * @param id The ID.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Print the corresponding Button.
	 */
	public int doStartTag() {
		
		String item = "";
		
		if(type.equals("rename")){
			
			item = "<button type='button' data-id='"+id+"'"
					+ " class='btn btn-block btn-md open_modal_rename btn_hover'"
					+ " data-toggle='modal' data-target='#renameCourseModal'>"
					+ "<span class='glyphicon glyphicon-pencil pull-right '></span> Rename Course</button>";
			
		} else if(type.equals("delete")){
			
			item = "<form action='DeleteCourse' method='post'>"
					+ "<button type='submit' class='btn btn-block btn-md btn_hover'>"
					+ "<span class='glyphicon glyphicon-trash pull-right'></span> Delete Course</button>"
					+ "<input type='hidden' name='id' value='"+id+"'></form>";
			
			
		} else if(type.equals("download")){
			
			item = "<form action='DownloadCourse' method='get'>"
					+ "<button type='submit' class='btn btn-block btn-md btn_hover'>"
					+ "<span class='glyphicon glyphicon-download pull-right'></span> Download Course</button>"
					+ "<input type='hidden' name='id' value='"+id+"'>"
					+ "<input type='hidden' name='name' value='"+name+"'></form>";
			
		} else if(type.equals("notDeprecated")){
			
			item = "<form action='DeprecateCourse' method='post'>"
					+ "<button type='submit' class='btn btn-block btn-md btn_hover'>"
					+ "<span class='glyphicon glyphicon-remove pull-right'></span> Not Deprecated</button>"
					+ "<input type='hidden' name='id' value='"+id+"'></form>";
			
		}

		try {
			pageContext.getOut().append(item).flush();

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
