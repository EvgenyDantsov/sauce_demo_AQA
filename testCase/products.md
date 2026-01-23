# Products Page Test Cases

## TC-PROD-01: Products page title is displayed correctly after login
**Preconditions:** User is logged in  
**Steps:**
1. Open Products page  
   **Expected Result:**
- Page title is "Products"

## TC-PROD-02: Products list is displayed and contains items
**Preconditions:** Products page is opened  
**Steps:**
1. Check products list  
   **Expected Result:**
- List contains at least one product

## TC-PROD-03: All products have name, description, price and action button
**Preconditions:** Products page is opened  
**Steps:**
1. Inspect all product items  
   **Expected Result:**
- Each product has a name, description, price, and action button

## TC-PROD-04: Product price has correct format $XX.XX
**Preconditions:** Products page is opened  
**Steps:**
1. Inspect price of a product  
   **Expected Result:**
- Price matches format $XX.XX

## TC-PROD-05: Product button text changes to 'Remove' after adding to cart
**Preconditions:** Products page is opened  
**Steps:**
1. Click "Add to cart" for a product  
   **Expected Result:**
- Button text changes to "Remove"

## TC-PROD-06: Button text toggles between 'Add to cart' and 'Remove'
**Preconditions:** Products page is opened  
**Steps:**
1. Click "Add to cart"
2. Click "Remove"  
   **Expected Result:**
- Button text toggles back to "Add to cart"

## TC-PROD-07: Footer should be visible on Products page
**Preconditions:** Products page is opened  
**Steps:**
1. Check visibility of footer  
   **Expected Result:**
- Footer is visible

## TC-PROD-08: Reset App State clears cart and resets product buttons to 'Add to cart'
**Preconditions:** User has products added to cart  
**Steps:**
1. Click "Add to cart"
2. Open menu and select "Reset App State"  
   **Expected Result:**
- Cart counter resets to 0
- Product button text returns to "Add to cart"

## TC-PROD-09: Products can be sorted by price from low to high
**Preconditions:** Products page is opened  
**Steps:**
1. Select sort option "Price (low to high)"  
   **Expected Result:**
- Products are sorted correctly by price in ascending order