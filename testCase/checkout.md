## TC-CHECKOUT-01: End-to-End Checkout Flow

**Test Class:** `CheckoutEndToEndTest`

### Description
Verify that a user can successfully complete the checkout flow from logging in, adding a product to the cart, entering checkout information, reviewing the order, and seeing the order confirmation.

### Preconditions
* User is logged in as a valid user
* Products page is opened
* Cart is empty

### Test Data
| Product Name           | First Name | Last Name | Postal Code |
| ---------------------- | ---------- | --------- | ----------- |
| Sauce Labs Backpack    | Tom        | Holland   | 12345       |

### Steps
1. Add product to cart
2. Open the shopping cart
3. Click the Checkout button
4. Fill in checkout information form
5. Click Continue
6. Review the order on the Checkout Overview page
7. Click Finish to complete the checkout
8. Verify that the Checkout Complete page is displayed

### Expected Result
* The Checkout Complete page is displayed with the title "Checkout: Complete!"

### Type
* Functional
* End-to-End

## TC-CHECKOUT-02: Checkout Information Form – Parameterized Validation

**Test Class:** `CheckoutPageParameterizedTest`

### Description
Verify that the checkout information form validates input fields correctly.  
Check that the user can proceed only if all required fields (first name, last name, postal code) are filled.

### Preconditions
* User is logged in
* Products page is opened
* Cart has at least one product

### Test Data
| First Name | Last Name | Postal Code | Should proceed? |
| ---------- | --------- | ----------- | --------------- |
| Tom        | Holland   | 12345       | true            |
| Tom        | Holland   |             | false           |
| Tom        |           | 12345       | false           |
|            | Holland   | 12345       | false           |
|            |           |             | false           |

### Steps
1. Add product to cart
2. Open shopping cart
3. Click Checkout
4. Fill in checkout information form with data from the table
5. Click Continue
6. Check if user is able to proceed to Checkout Overview or sees an error

### Expected Result
* If all fields are filled, the Checkout Overview page is displayed
* If any field is missing, an error message is displayed and the user stays on Checkout Information page

### Type
* Functional
* Parameterized