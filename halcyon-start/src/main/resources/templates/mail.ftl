<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="description" content="email code">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            background-color: #ECECEC;
            font-family: 'Microsoft YaHei', '黑体', sans-serif;
            line-height: 1.5;
        }
        .container {
            width: 800px;
            margin: 35px auto;
            padding: 35px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(153, 153, 153, 0.5);
        }
        .header {
            background-color: #409EFF;
            color: #fff;
            padding: 15px 35px;
            border-radius: 5px 5px 0 0;
            font-size: 24px;
            text-align: center;
        }
        .content {
            padding: 25px 35px 40px;
            background-color: #fff;
            opacity: 0.9;
        }
        .content h2 {
            margin: 5px 0;
            font-size: 18px;
            color: #333;
        }
        .content p {
            margin: 10px 0;
            color: #333;
        }
        .content span {
            color: #ff8c00;
            font-weight: bold;
            font-size: 18px;
        }
        .footer {
            padding: 10px 35px;
            border-top: 1px solid #ccc;
            color: #747474;
            font-size: 12px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        ${title}
    </div>
    <div class="content">
        <h2>尊敬的用户：</h2>
        <p>您好！感谢您使用${platformName}使用，您的账号正在进行邮箱验证，验证码为：<span>${verificationCode}</span>，有效期10分钟，请尽快填写验证码完成验证！</p>
        <h2>Dear user:</h2>
        <p>Hello! Thanks for using ${platformName}, your account is being authenticated by email, the verification code is: <span>${verificationCode}</span>, valid for 10 minutes. Please fill in the verification code as soon as possible!</p>
    </div>
    <div class="footer">
        <p>此为系统邮件，请勿回复<br>Please do not reply to this system email</p>
    </div>
</div>
</body>
</html>
