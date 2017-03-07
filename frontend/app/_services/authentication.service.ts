import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions, RequestMethod } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    public token: string;

    private baseUrl = 'http://localhost:8080';  // URL to web API
    private loginUrl = this.baseUrl + '/user/login';
    private registerUrl = this.baseUrl + '/user/register';
    private profileUrl = this.baseUrl + '/user/profile';

    constructor(private http: Http) {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
    }

    login(username: string, password: string): Observable<Boolean> {
        let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8', 'Accept': 'application/json; charset=utf-8' });
        //let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify({ username: username, password: password });
        let options = new RequestOptions({ headers: headers });


        return this.http.post(this.loginUrl, body, options)
            .map((response: Response) => {

                let data = response.json();

                if (data.status === "ok") {
                    this.token = data.token;

                    localStorage.setItem('currentUser', JSON.stringify({ token: this.token }));
                    return true;
                }

                return false;
            });
    }

    register(username: string, email: string, password: string): Observable<any> {

        let headers = new Headers({ 'Content-Type': 'application/json; charset=utf-8', 'Accept': 'application/json; charset=utf-8' });
        let body = JSON.stringify({ username: username, password: password, email: email });
        let options = new RequestOptions({ headers: headers });

        return this.http.post(this.registerUrl, body, options)
            .map((response: Response) => {
                return response.json();
            });
    }

    profile(): Observable<any> {
        console.log("profile token is : " + this.token);
        let headers = new Headers({ 'X-Auth-Token': 'Bearer ' + this.token });
        let options = new RequestOptions({ headers: headers });

        return this.http.get(this.profileUrl, options)
            .map((response: Response) => {
                if (response.status != 200) {
                    return null;
                }
                return response.json();
            });
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');
    }
}