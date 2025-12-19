import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  // Properties for Interpolation
  title = 'Angular Data Binding Demo';
  userName = 'John Doe';
  currentDate = new Date();

  // Properties for Property Binding
  imageUrl = 'https://angular.dev/assets/images/press-kit/angular_gradient.png';
  isDisabled = false;
  imageWidth = 200;

  // Properties for Two-Way Binding
  userInput = '';
  email = '';
  selectedColor = 'blue';

  // Properties for Event Binding
  clickCount = 0;
  lastClickedButton = '';

  // Properties for Class and Style Binding
  isActive = false;
  hasError = false;
  fontSize = 16;
  textColor = 'black';

  // Array for ngFor demonstration
  colors = ['red', 'blue', 'green', 'yellow', 'purple'];

  // Methods for Event Binding
  incrementCounter() {
    this.clickCount++;
  }

  handleButtonClick(buttonName: string) {
    this.lastClickedButton = buttonName;
    alert(`You clicked: ${buttonName}`);
  }

  onInputChange(event: Event) {
    const target = event.target as HTMLInputElement;
    console.log('Input changed:', target.value);
  }

  toggleActive() {
    this.isActive = !this.isActive;
  }

  toggleError() {
    this.hasError = !this.hasError;
  }

  increaseFontSize() {
    this.fontSize += 2;
  }

  decreaseFontSize() {
    if (this.fontSize > 10) {
      this.fontSize -= 2;
    }
  }

  resetAll() {
    this.userInput = '';
    this.email = '';
    this.clickCount = 0;
    this.isActive = false;
    this.hasError = false;
    this.fontSize = 16;
    this.textColor = 'black';
  }
}
