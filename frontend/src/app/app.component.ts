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
      `test-fill`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/test-fill.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `test-outline`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/test-outline.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `question-fill`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/question-fill.svg")
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
    this.matIconRegistry.addSvgIcon(
      `add`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/add.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `close`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/close.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `subscribe`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/subscribe.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `public-status`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/public-status.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `private-status`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/private-status.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `level-basic`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/level-basic.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `level-advanced`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/level-advanced.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `3-dot-white`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/3-dot-white.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `edit`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/edit.svg")
    );
    this.matIconRegistry.addSvgIcon(
      `trash`,
      this.domSanitizer.bypassSecurityTrustResourceUrl("../assets/icons/trash.svg")
    );
  }
}
