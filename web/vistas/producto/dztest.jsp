<%-- 
    Document   : dztest
    Created on : Oct 14, 2014, 9:49:08 AM
    Author     : kavesa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dropzone Page</title>
        <script src="../../static/dropzone/dropzone.js"></script>
        <link rel="stylesheet" href="../../static/dropzone/css/dropzone.css" />
    </head>

    <body>
        <h1>Dropzone Test</h1>

        <form action="/target-url" id="my-dropzone" class="dropzone"></form>

        <div class="dropzone-previews dz-message">
        </div> <!-- this is were the previews should be shown. -->

        <script>
            // myDropzone is the configuration for the element that has an id attribute
            // with the value my-dropzone (or myDropzone)
            Dropzone.options.myDropzone = {
                init: function() {
                    this.on("addedfile", function(file) {

                        // Create the remove button
                        var removeButton = Dropzone.createElement("<button>Remove file</button>");

                        // Capture the Dropzone instance as closure.
                        var _this = this;

                        // Listen to the click event
                        removeButton.addEventListener("click", function(e) {
                            // Make sure the button click doesn't submit the form:
                            e.preventDefault();
                            e.stopPropagation();

                            // Remove the file preview.
                            _this.removeFile(file);
                            // If you want to the delete the file on the server as well,
                            // you can do the AJAX request here.
                        });

                        // Add the button to the file preview element.
                        file.previewElement.appendChild(removeButton);
                    });
                }
            };
        </script>
        <!-- Now setup your input fields -->
        <h4>Fin</h4>
    </form>
</body>
</html>
