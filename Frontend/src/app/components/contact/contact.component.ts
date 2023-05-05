import { Component } from '@angular/core';
import { ContactForm } from 'src/app/models/contactForm';
import { ContactService } from 'src/app/services/contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  formData = new ContactForm();
  constructor(private contactService: ContactService) {}

  onSubmit() {
    this.contactService.sendContactForm(this.formData)
      .subscribe(
        () => {
          this.formData = {
            name: '',
            email: '',
            subject: '',
            message: ''
          };
        },
        error => {
        }
      );
      window.location.href = "/"
  }
}
