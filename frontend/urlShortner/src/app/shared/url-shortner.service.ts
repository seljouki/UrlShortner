import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UrlShortnerService {
  serviceUrl : string = '';
  backend_api: string = environment.backend_endpoint;
  constructor(private http : HttpClient) { 
    this.serviceUrl = `${this.backend_api}/url/shortner`;
  }

  getShortUrl(url : string) {
    return this.http.post<any>(this.serviceUrl,url);
  }

}