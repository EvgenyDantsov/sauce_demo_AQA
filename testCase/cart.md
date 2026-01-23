# Test Cases

## TC-CART-00: Cart Item Count – Parameterized Test

**Test Class:** `CartItemCountParameterizedTest`

### Description

Verify that the shopping cart item counter displays the correct number when one or more products are added from the Products page.

### Preconditions

* User is logged in as a valid user
* Products page is opened
* Cart is empty

### Test Data

| Products to add                                                     | Expected cart count |
| ------------------------------------------------------------------- | ------------------- |
| Sauce Labs Backpack                                                 | 1                   |
| Sauce Labs Backpack, Sauce Labs Bike Light                          | 2                   |
| Sauce Labs Backpack, Sauce Labs Bike Light, Sauce Labs Bolt T-Shirt | 3                   |

### Steps

1. Add each product from the provided list to the cart
2. Observe the cart badge counter

### Expected Result

* Cart badge displays the expected number of added products

### Type

* Functional
* Parameterized

# Cart

## TC-CART-01: User can open cart page after login
**Preconditions:** User is logged in  
**Steps:**
1. Click shopping cart icon

**Expected Result:**
- Cart page is opened
- Page title is "Your Cart"

## TC-CART-02: Removing product from cart resets cart item counter
**Preconditions:** User is logged in  
**Steps:**
1. Add product to cart
2. Open cart page
3. Click Remove button for the product

**Expected Result:**
- Cart item counter is updated to 0