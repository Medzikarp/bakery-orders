import {Injectable} from '@angular/core';
import {HttpHeaders} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class MainService {
    private _baseUrl: string = "http://localhost:8081/";
    private _apiUrl: string = this._baseUrl + 'api/v1';
    private _options = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor() {
    }

    get baseUrl(): string {
        return this._baseUrl;
    }

    get options(): { headers: HttpHeaders } {
        return this._options;
    }


    get apiUrl(): string {
        return this._apiUrl;
    }
}
