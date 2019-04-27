package org.bakery.orders.service;

/**
 * Created by Lukas Kotol on 27.04.2019.
 */
public interface UploadService {
    void uploadImage(byte[] content, String name, String suffix, String folder);
}
