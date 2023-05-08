import { Component } from '@angular/core';
import { FAQ } from 'src/app/models/faq';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent {
  faq:FAQ[] = [
    { title: 'How do I place an order for ingredients on your platform?', content: "Placing an order for ingredients is simple! Once you've selected a recipe you want to try, simply click on the 'Add to Cart' button next to the ingredient list. You can review your virtual shopping cart, make any necessary adjustments, and then proceed to the checkout process to place your order for delivery.", isExpanded: false },
    { title: 'How do I access the step-by-step instructions for a recipe?', content: "Each recipe on our platform comes with detailed step-by-step instructions. Once you've selected a recipe, simply scroll down to the recipe details page where you'll find the instructions, including cooking times and techniques, organized in an easy-to-follow format.", isExpanded: false },
    { title: 'Can I save my favorite recipes for future reference?', content: 'Yes, you can! Our platform allows you to create an account and save your favorite recipes for easy access in the future. You can bookmark recipes and this way  you can easily find and revisit your favorite recipes whenever you want.', isExpanded: false },
    { title: 'Can I submit my own recipes to your platform?', content: 'Yes, absolutely! We encourage our users to share their own recipes on our platform. You can easily submit your own recipes through our website or app. Simply follow the submission guidelines and provide all the necessary details, such as ingredients, measurements, step-by-step instructions and accompany that with some images of the final product.', isExpanded: false },
    { title: 'Are the recipes on your platform suitable for beginner cooks?', content: "Yes, our platform features recipes submitted by our community of users. This means that you can find a diverse range of recipes with varying levels of difficulty, including options for beginner cooks. You can easily filter recipes based on difficulty level as well as by category", isExpanded: false }
  ];
  onAccordionItemClick(item:FAQ) {
    item.isExpanded = !item.isExpanded;

    this.faq.forEach(q => {
      if (q !== item) {
        q.isExpanded = false;
      }
    });
  }

}
