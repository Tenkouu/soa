# User JSON Service

Энэхүү сервис нь User Profile-ийн CRUD үйлдлийг хариуцдаг. Профайл мэдээлэлд хандахдаа SOAP Service-ээр дамжуулан Authentication хийдэг Middleware агуулсан.

## Үндсэн функцууд
- **GET /users/{id}:** Хэрэглэгчийн профайлыг JSON форматаар авах.
- **Middleware:** Хүсэлт бүрийн Authorization Header-ийг шалгаж, SOAP Service-рүү ValidateToken хүсэлт илгээнэ.

## Ажиллуулах заавар
- Port: 8080
- Tech: Java 21, Spring Web, Spring Web Services (Client)
- CORS: http://localhost:8082 хаягаас хандахыг зөвшөөрсөн.
