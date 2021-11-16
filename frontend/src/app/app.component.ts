import { Component } from '@angular/core';
import { MatIconRegistry } from "@angular/material/icon";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'codex-academy';
  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
    ){
    this.matIconRegistry.addSvgIcon(
      `course-fill`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/course-fill.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `course-outline`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/course-outline.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `category-fill`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/category-fill.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `category-outline`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons//category-outline.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `test-outline`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/test-outline.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `question-outline`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/question-outline.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `account-fill`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/account-fill.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `account-outline`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/account-outline.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `avatar`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/icon-avatar.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `arrow-down`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/arrow-down.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `search`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/search.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `setting-account`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/setting-account.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `logout`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/logout.svg")
    );
  }
}
