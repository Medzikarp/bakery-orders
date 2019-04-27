package org.bakery.orders.model;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;

public class File {

    @FormParam("name")
    @PartType("application/octet-stream")
    private String name;

    @FormParam("folder")
    @PartType("application/octet-stream")
    private String folder;

    @FormParam("type")
    @PartType("application/octet-stream")
    private String type;

    @FormParam("file")
    @PartType("application/octet-stream")
    private byte[] data;

    public byte[] getData() {
        return data;
    }


    public void setData(byte[] data) {
        this.data = data;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}