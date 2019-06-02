package org.bakery.orders.service.impl;

import org.apache.commons.io.FilenameUtils;
import org.bakery.orders.service.UploadService;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Lukas Kotol on 27.04.2019.
 */
@ApplicationScoped
public class UploadServiceImpl implements UploadService {

    private static final String UPLOAD_PATH = "../standalone/tmp/images";

    @Override
    public void uploadImage(byte[] content, String name, String suffix, String folder) {
        try {
            deleteOtherFilesWithName(folder, name);
            String filePath = UPLOAD_PATH + "/" + folder + "/" + name + suffix;
            writeFile(content, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(byte[] content, String filename) throws IOException {

        File file = new File(filename);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();
    }

    private void deleteOtherFilesWithName(String folder, String name) {
        File actualFolder = new File(UPLOAD_PATH + "/" + folder);
        File[] listOfFiles = actualFolder.listFiles();
        Arrays.stream(listOfFiles).forEach(file -> {
            String fileNameWithoutExtension = FilenameUtils.removeExtension(file.getName());
            if (fileNameWithoutExtension.equals(name)) {
                file.delete();
            }
        });
    }

}
