package org.bakery.orders.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bakery.orders.model.File;
import org.bakery.orders.service.UploadService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/upload")
public class UploadEndpoint {

    @Inject
    private UploadService uploadService;

    @POST
    @Path("/image")
    @Consumes("multipart/form-data")
    public Response uploadImage(@MultipartForm File file) {
        Response.ResponseBuilder builder;
        try {
            uploadService.uploadImage(file.getData(), file.getName(), file.getType(), file.getFolder());
            builder = Response.ok();
        } catch (Exception e) {
            builder = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
        }
        return builder.build();
    }
}