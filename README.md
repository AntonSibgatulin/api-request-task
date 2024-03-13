#  Пример использования

```java
    public static void main(String[] args) {

        IApiRequestAdapter alone = new AloneApiImpl(TimeUnit.SECONDS, 5);
        IApiRequest multiply = new MultiplyApiImpl(TimeUnit.SECONDS, 5);

        alone.createDocument(App.generateTestDocument(), "signature");


        IUser iUser = new UserImpl();
        UserData userData = new UserData(String.valueOf(1));
        iUser.setUserData(userData);

        multiply.createDocument(App.generateTestDocument(), "signature", iUser);
    }
```