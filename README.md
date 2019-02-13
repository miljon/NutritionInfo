## Currently closed

## API key is notprovided due to limitation of requestes.. To get fully working app

1. Register on USDA Food Composition Database https://ndb.nal.usda.gov/ndb/doc/index
2. Get API key
3. Put your key as a value and a name `API_KEY` in `gradle.properties` file in directory: `C:\Users\userName\.gradle`
4. Rebulid and run :)

Example of proper implementation key-value API key:
```
API_KEY="yourApiKey"
```
## USED TECHNOLOGIES

**1. Architecture**
  - MVP
  - Clean Code

**2. Frameworks and libs:**
  - Dagger 2
  - RxJava 2
  - Retrofit 2
  - ButterKnife

**3. Tests:**
  - Expresso
  - JUnit
  - Mockito
  - MockWebServer

## Other
- Expresso requires switching off animation in phone
