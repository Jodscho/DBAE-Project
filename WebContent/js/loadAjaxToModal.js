
/*
 * This code is used to load content, using ajax, to one of the 
 * following three Modals: dokumenteFreigebenModal.jsp, deleteNutzerModal.jsp, zahlungenEinsehenModal.jsp
 * 
 * Why is it implemented like this?
 * Since the Modals are not seperate jsp's on their own (they are being called seperatly via bootstrap)
 * the Information could not be loaded using just a Servlet since it would had to redirect to another
 * single jsp after the necessary Information would be retrieved from the Database which is not possible, because
 * the modals are not "stand-alone" jsp's.
 * 
 * How does it work?
 * The Servlets get method is called via the $.get() method (implemented in JQuery),
 * retrieves the wanted Information from the Database and returns a Json Object which is 
 * displayed on the modal_body.
 * (For more specifc Information read the comments in the methods.)
 * 
 * Problems? 
 * The content can only be retrieved and displayed for one of the above mentioned modals, in this particular context.
 * This is a messy and not very generalising solution.
 * 
 * More Information on Servlets interacting with ajax: https://stackoverflow.com/a/4113258
 * 
 * @author Jonathan Lochmann
 * 
 * */

	// called inside the modals, with the proper parameters, to initalise the loading process
    function initAjaxLoadingProcess(servletName, modalName, resultContainer, messageNoContent) {

    		// if the container is being closed, clean it 
    	   $(modalName).on('hidden.bs.modal', function(e) {
               $(resultContainer).empty();
           });
    	   
    	   // initate request to the specific servlet
            $.get(servletName, function(responseJson) {
            	// remove previously displayed information
            	$(resultContainer).empty();
            	// print the returned Json on the modal: "modalName" in the container: "resultContainer"
                printResult(responseJson);
            });



        function printResult(responseJson) {
        	// empty once more
            $(resultContainer).empty();

            var $listgroup = $("<div class='list-group'>").appendTo($(resultContainer));
            // first hide the listgroup, so that it can slide down when its done creating the containers
            $listgroup.hide();

            // if the returned Json is empty print the specific message
            if (jQuery.isEmptyObject(responseJson)) {
            	$("<div class='alert alert-info'>"+messageNoContent+"</div>").appendTo($listgroup);
            } else {
            	// otherwise iterate through each object in the returned List
                $.each(responseJson, function(index, object) {
                	
                	// print the html code according to the modal name, distinguishing between the three
                	// following modals
                	// NOTE: this is not a good solution (there needs to be a more generalising way of achieving this)
                	
                    if (modalName === "#dokumenteFreigeben") {
                    	// print html like the following:
                    	// Name of document  <Download> <Ja> <Nein>
                    	// (keep in mind that each button calls a different form which is also specified)
                    	// NOTE: not the prettiest way of doing this
                    	
                        $("<li class='list-group-item'>" + object.name + "</li>")
                            .appendTo($listgroup)
                            .append($("<button id='neinBtn' value='" + object.dokId + "' class='btn btn-warning pull-right btn-xs accessPoint' style='margin-left:10px;'><span class='glyphicon glyphicon-remove'></span> Nein</button>"))
                            .append($("<button id='jaBtn' value='" + object.dokId + "' class='btn btn-success pull-right btn-xs accessPoint' style='margin-left:10px;'><span class=' glyphicon glyphicon-ok'></span> Ja</button>"))
                            .append($("<form action='DownloadDokument' method='get' style='float: right; margin-left: 10px;'><button type='submit' id='downloadBtnDok' class='btn btn-info pull-right btn-xs'><span class='glyphicon glyphicon-download-alt'></span> Download</button><input type='hidden' name='id' value='" + object.dokId + "'>"))
                    } else if (modalName === "#deleteNutzer") {
                    	// print html like the following:
                    	// Name: Username, Mail: user@mail.de  <Delete>
                    	
                        $("<li class='list-group-item'>" + "<b>Name:</b> " +object.name +", <b>Mail:</b> " +object.mail+ "</li>")
                            .appendTo($listgroup)
                            .append($("<button value='" + object.mail + "' class='btn btn-danger pull-right btn-xs accessPoint'><span class='glyphicon glyphicon-trash'></span> Delete</button>"))
                    } else if (modalName === "#zahlungenEinsehen") {
                    	// print html like the following:
                    	// Username <Zahlung eingegangen>
                        $("<li class='list-group-item' value='" + object.kunde + "'>" + object.kunde + "</li>")
                            .appendTo($listgroup)
                            .append($("<button value='" + object.zahlungid + "' class='btn btn-success pull-right btn-xs accessPoint'><span class='glyphicon glyphicon-ok'></span> Zahlung eingegangen</button>"))
                    }

                });

            }
            // slide down the prepeared container
            $listgroup.slideDown(500);
        }


        // this function is called when a button was clicked that requires the container to change
        function resfreshResultContainer(id) {
        	// iterate over each button that has the "accessPoint" class and remove the 
        	// parent ("list-group-item") if the correct button could be identified via the id
            $('button.accessPoint').each(function() {
                if ($(this).val() === id) {
                    $(this).parent().remove();
                    return false;
                }
            });
            
            // if there are no more objects to display left, print the "emptyMessage"
            if($(resultContainer+" .list-group").children().size() == 0){
            	$("<div class='alert alert-info'>"+messageNoContent+"</div>").appendTo($(resultContainer+" .list-group"));
            }
        }


        // called when any of the "accessPoint" buttons are pressed and
        // used to transfer extra parameters depending on which modal is present
        $("body").on("click", "button.accessPoint", function() {

            var params = {
                id: $(this).val(),
            }

            if (modalName === "#dokumenteFreigeben") {
                params['btnAction'] = $(this).attr('id');
            } else if (modalName === "#zahlungenEinsehen") {
                params['mail'] = $(this).parent().attr("value");
            }

            // call the post method in the specific servlet with the parameters
            $.post(servletName, params, function(id) {
            	// refresh the container after the servelt has succesfully finished
            	// (id of the object that was deleted)
            	resfreshResultContainer(id);
            });

        });
    }
