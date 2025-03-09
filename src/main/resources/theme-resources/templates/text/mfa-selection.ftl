<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Select 2 Factor</title>
</head>
<body>
    <h2>Please select your 2-factor authentication method</h2>
    <form action="${url.loginAction}" method="post">
        <div>
            <input type="radio" id="emailOption" name="2FactorChoice" value="email" required>
            <label for="emailOption">Email Authenticator</label>
        </div>
        <div>
            <input type="radio" id="smsOption" name="2FactorChoice" value="sms">
            <label for="smsOption">SMS Authenticator</label>
        </div>
        <div>
            <input type="radio" id="recoveryOption" name="2FactorChoice" value="recovery">
            <label for="recoveryOption">Recovery Code</label>
        </div>
        <div>
            <button type="submit">Continue</button>
        </div>
    </form>
    <#if error??>
        <div style="color:red;">${error}</div>
    </#if>
</body>
</html>
