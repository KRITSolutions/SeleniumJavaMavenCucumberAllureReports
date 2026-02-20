# XPath Selectors Guide

## XPath Basics

XPath (XML Path Language) is a query language used to navigate through XML documents. In Selenium, we use XPath to locate elements on a web page.

## Syntax

```
//*[@attribute='value']
```

## Types of XPath

### 1. Absolute XPath

Starts from root element of the HTML document. Uses single forward slash `/`.

```xpath
/html/body/div[1]/div[2]/form/input[1]
```

**Disadvantages:**
- Brittle - breaks with any HTML structure change
- Long and hard to maintain
- Not recommended for automation

### 2. Relative XPath

Starts from any element in the document. Uses double forward slash `//`.

```xpath
//input[@name='email']
//button[@id='submit-btn']
```

**Advantages:**
- More flexible
- Shorter and readable
- Recommended for automation

## XPath Expressions

### Basic Locators

**By ID:**
```xpath
//*[@id='userId']
//input[@id='userId']
```

**By Name:**
```xpath
//*[@name='email']
//input[@name='password']
```

**By Class:**
```xpath
//*[@class='btn btn-primary']
//button[@class='btn']
```

**By Attribute:**
```xpath
//*[@placeholder='Enter email']
//*[@data-qa='submit-btn']
```

### Text-Based Locators

**By Text:**
```xpath
//*[text()='Login']
//button[text()='Sign In']
```

**By Partial Text:**
```xpath
//*[contains(text(), 'Click')]
//button[contains(text(), 'Submit')]
```

**By Case-Insensitive Text:**
```xpath
//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'login')]
```

### Complex Expressions

**Using And:**
```xpath
//input[@name='email' and @type='email']
//button[@class='btn' and @id='submit']
```

**Using Or:**
```xpath
//input[@name='email' or @name='username']
//button[@class='primary' or @class='secondary']
```

**Using Not:**
```xpath
//button[not(@disabled)]
//*[not(@class='hidden')]
```

**Using Starts-With:**
```xpath
//input[starts-with(@name, 'user')]
//button[starts-with(@id, 'btn-')]
```

**Using Ends-With:**
```xpath
//input[ends-with(@name, 'email')]
//div[ends-with(@class, '-container')]
```

**Using Normalize-Space:**
```xpath
//button[normalize-space()='Click Here']
//*[contains(normalize-space(), 'Welcome')]
```

## Axes

### Child Axis
```xpath
//div/input  // Direct children only
```

### Descendant Axis
```xpath
//div//input  // All descendants
```

### Parent Axis
```xpath
//input/..  // Parent element
//input/parent::div
```

### Following-Sibling Axis
```xpath
//input/following-sibling::button  // Next sibling button
```

### Preceding-Sibling Axis
```xpath
//button/preceding-sibling::input  // Previous sibling input
```

### Following Axis
```xpath
//input/following::button  // Any button after this input
```

### Preceding Axis
```xpath
//button/preceding::input  // Any input before this button
```

## Predicates

### Index
```xpath
//button[1]  // First button
//button[2]  // Second button
//button[last()]  // Last button
//button[last()-1]  // Second to last
```

### Count
```xpath
//button[count(preceding-sibling::button) = 0]  // First button
```

### Position
```xpath
//button[position()=1]
//table/tr[position()>1]  // All rows except header
```

## Practical Examples

### Form Elements
```xpath
// Email input
//input[@type='email']
//input[@placeholder='Email']
//input[contains(@name, 'email')]

// Password input
//input[@type='password']
//input[@placeholder='Password']

// Login button
//button[@type='submit']
//button[@class='btn-login']
//button[contains(text(), 'Login')]
```

### Dynamic Elements
```xpath
// By partial ID
//input[contains(@id, 'user')]

// By dynamic attribute
//*[@data-test-id='element']

// By changing content
//div[contains(@class, 'error') and contains(text(), 'Required')]
```

### Table Elements
```xpath
// Specific cell
//table//tr[2]//td[1]

// By header
//th[contains(text(), 'Email')]/..

// By row value
//tr[contains(.//td, 'John')]

// By row and column
//table//tr[contains(., 'John')]//td[3]
```

### Navigation Elements
```xpath
// Links
//a[@href='#home']
//a[contains(text(), 'Contact')]

// Dropdowns
//select[@id='country']
//select/option[@value='USA']
```

## Best Practices

1. **Use Relative XPath**
   - More maintainable
   - Faster execution
   - Less brittle

2. **Prefer Attributes Over Position**
   ```xpath
   // Good
   //button[@id='submit-btn']
   
   // Avoid
   //div/div/div/button[3]
   ```

3. **Use Meaningful Attributes**
   ```xpath
   // Prefer data attributes
   //*[@data-qa='login-button']
   
   // Use aria labels
   //*[@aria-label='Close']
   ```

4. **Keep XPath Simple**
   ```xpath
   // Good
   //input[@name='email']
   
   // Avoid
   //div[@class='container']//form[@id='login']//div[@class='input-group']//input[@name='email']
   ```

5. **Test XPath in Browser Console**
   ```javascript
   // In browser console
   document.evaluate("//button[@id='submit']", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue
   ```

## Common Pitfalls

1. **Using Single Slash for Dynamic Content**
   ```xpath
   // Wrong
   /html/body/div/button
   
   // Right
   //button
   ```

2. **Hardcoding Text**
   ```xpath
   // Fragile
   //button[text()='Exact Button Text']
   
   // Better
   //button[contains(text(), 'Button')]
   ```

3. **Relying on Position**
   ```xpath
   // Brittle
   //button[3]
   
   // Better
   //button[@id='specific-id']
   ```

4. **Ignoring Case Sensitivity**
   ```xpath
   // May fail
   //button[text()='login']
   
   // Better
   //button[contains(translate(text(), 'LOGIN', 'login'), 'login')]
   ```

## Debugging XPath

### In Browser (Chrome DevTools)
1. Open DevTools (F12)
2. Press Ctrl+F in Elements tab
3. Enter XPath expression
4. Press Enter

### In Firefox
1. Open DevTools (F12)
2. Use Inspector
3. Test XPath in console

### In IntelliJ IDEA
1. Use Selenium IDE plugin
2. Test XPath directly in IDE

## Resources

- [W3C XPath Specification](https://www.w3.org/TR/xpath/)
- [MDN XPath Guide](https://developer.mozilla.org/en-US/docs/Web/XPath)
- [Selenium WebDriver XPath](https://www.selenium.dev/documentation/webdriver/elements/locators/)
- [XPath Cheatsheet](https://devhints.io/xpath)

