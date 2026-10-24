# Test Scenarios: OrangeHRM Demo

| ID | Area | Scenario | Expected Result |
|----|------|----------|-----------------|
| TC01 | Login | Login with valid credentials | Dashboard page opens |
| TC02 | Login | Login with wrong password | "Invalid credentials" message shown |
| TC03 | Navigation | Click Admin in the left menu | Admin page opens |
| TC04 | Navigation | Click PIM in the left menu | PIM page opens |
| TC05 | Search | Search users by username "Admin" | At least 1 record found |
| TC06 | Filter | Filter users by User Role "Admin" | Every row shows role Admin |
| TC07 | Table | Open the users list | Table has rows and a "Records Found" count |
| TC08 | Form | Add a new employee with first and last name | Success message shown |