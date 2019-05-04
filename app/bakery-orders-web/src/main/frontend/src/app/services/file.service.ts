import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Product} from "../model/product";

@Injectable({
    providedIn: 'root'
})

export class FileService {

    baseUrl: string = "http://localhost:8080/api";

    constructor(private httpClient: HttpClient) {
    }

    uploadImage(file: File, folder: string, name: Number) {
        const formData = new FormData();
        formData.append('file', file);
        formData.append('name', name.toString());
        formData.append('folder', folder);
        formData.append('type', this.getSuffix(file.type));
        return this.httpClient.post(this.baseUrl + '/upload/image', formData);
    }

    public getSuffix(type: string): string {
        switch (type) {
            case 'image/gif':
                return '.gif';
            case 'image/jpeg':
                return '.jpg';
            case 'image/png':
                return '.png';
            default:
                return 'unknown';
        }
    }

}
