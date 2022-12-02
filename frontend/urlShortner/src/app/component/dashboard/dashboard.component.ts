import { Component, OnInit } from '@angular/core';
import { UrlShortnerService } from 'src/app/shared/url-shortner.service';
import { Url } from 'src/app/shared/model/Url';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  url : string = "";
  backend: string = environment.backend_endpoint;
  originalUrl :string = "";
  isUrlGenerated : boolean = false;
  isErrorGenerated : boolean = false;
  shortUrl : string ="";
  constructor(private urlShortnerService : UrlShortnerService) { }

  ngOnInit(): void {
    this.isUrlGenerated = false;
  }

  generateShortUrl() {
    this.urlShortnerService.getShortUrl(this.url).subscribe({
      next: (urlPayload: Url) => {
        console.log('Url',urlPayload.shortUrl);
        
        if(urlPayload == null) {
          this.isErrorGenerated = true; 
        } else {
          this.isUrlGenerated = true;
          this.isErrorGenerated = false;
          this.shortUrl = urlPayload.shortUrl;
          this.originalUrl = urlPayload.originalUrl;
        }
      },
      error: () => {
        this.isUrlGenerated = false;
        this.isErrorGenerated = true;
      }
    });
      
      
  }
}