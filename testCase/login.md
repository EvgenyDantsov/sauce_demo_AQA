## TC-LOGIN-01: Login Page – Parameterized Validation

**Test Class:** `LoginPageParameterizedTest`

### Description
Verify that the login form behaves correctly for valid and invalid credentials.  
Check that the user can log in successfully with valid credentials and sees an error message for invalid credentials.

### Preconditions
* Login page is opened
* User has valid or invalid credentials to test

### Test Data
| Username           | Password        | Should login successfully? |
| ----------------- | --------------- | ------------------------- |
| standard_user      | secret_sauce    | true                      |
| (empty)            | (empty)         | false                     |
| locked_out_user    | secret_sauce    | false                     |
| invalidUser        | invalidPassword | false                     |

### Steps
1. Enter username and password
2. Click Login button
3. Observe behavior

### Expected Result
* If credentials are valid, Products page is opened
* If credentials are invalid, an error message is displayed

### Type
* Functional
* Parameterized

## TC-AUTH-01: User can log out successfully

**Test Class:** `LogoutUserTest`

### Description
Verify that a logged-in user can log out using the menu and is redirected back to the login page.

### Preconditions
- User is logged in as a valid user
- Products page is opened

### Steps
1. Click the menu button in the header
2. Click the Logout option

### Expected Result
- User is redirected to the login page
- Login page displays the logo "Swag Labs"

### Type
- Functional