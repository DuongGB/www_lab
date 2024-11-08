
# RESTful API

**REST** (Representational State Transfer) là một kiến trúc cho các dịch vụ web, cung cấp cách thức đơn giản và có thể mở rộng để trao đổi dữ liệu giữa các hệ thống khác nhau, thông qua các giao thức chuẩn, chủ yếu là HTTP. Khi một API được gọi là **RESTful API**, điều đó có nghĩa là nó tuân theo các nguyên tắc của kiến trúc REST.

## 1. REST là gì?
REST là một kiến trúc được đề xuất bởi Roy Fielding vào năm 2000. Nó tập trung vào các nguyên tắc đơn giản, sử dụng các phương thức HTTP tiêu chuẩn và không ràng buộc với công nghệ hay định dạng dữ liệu. REST giúp ứng dụng web trở nên linh hoạt, dễ mở rộng và bảo trì tốt hơn.

## 2. Các nguyên lý chính của REST
Một API được coi là RESTful khi tuân thủ các nguyên lý sau:
1. **Giao diện thống nhất (Uniform Interface)**: Các tương tác với tài nguyên đều tuân theo một chuẩn nhất định. Các tài nguyên được định danh qua URI.
2. **Không trạng thái (Stateless)**: Mỗi yêu cầu từ client đều phải chứa đầy đủ thông tin cần thiết để server hiểu và xử lý mà không cần lưu trạng thái từ các yêu cầu trước.
3. **Khả năng lưu vào bộ nhớ đệm (Cacheable)**: Server có thể thông báo cho client về khả năng cache của dữ liệu nhằm tối ưu hóa hiệu suất.
4. **Kiến trúc Client-Server**: Client và server tách biệt, giúp tăng tính linh hoạt và khả năng mở rộng.
5. **Hệ thống phân cấp (Layered System)**: REST cho phép sử dụng hệ thống phân cấp với các lớp trung gian để cải thiện bảo mật, khả năng cân bằng tải và khả năng mở rộng.
6. **Mã có thể thực thi (Code on Demand) - Tùy chọn**: REST có thể cho phép tải và thực thi mã trên client (ví dụ: JavaScript).

## 3. Các thành phần chính trong RESTful API
### Tài nguyên (Resource)
- Mỗi tài nguyên trong REST được định danh bằng một URI duy nhất (ví dụ: `/users`, `/products/123`).

### Phương thức HTTP phổ biến
- **GET**: Truy vấn dữ liệu từ server (ví dụ: `GET /users/1`).
- **POST**: Tạo mới một tài nguyên (ví dụ: `POST /users`).
- **PUT**: Cập nhật tài nguyên hoặc tạo mới nếu không tồn tại (ví dụ: `PUT /users/1`).
- **PATCH**: Cập nhật một phần của tài nguyên (ví dụ: `PATCH /users/1`).
- **DELETE**: Xóa tài nguyên (ví dụ: `DELETE /users/1`).

### Status Code HTTP
- **200 OK**: Thành công.
- **201 Created**: Tạo thành công.
- **204 No Content**: Thành công nhưng không có dữ liệu trả về.
- **400 Bad Request**: Yêu cầu không hợp lệ.
- **401 Unauthorized**: Không có quyền truy cập.
- **404 Not Found**: Không tìm thấy tài nguyên.
- **500 Internal Server Error**: Lỗi phía server.

### Định dạng dữ liệu
REST thường sử dụng **JSON** vì nhẹ và dễ xử lý. XML cũng được dùng trong một số trường hợp.

### Header và Body trong HTTP
- **Header** chứa thông tin mô tả yêu cầu hoặc phản hồi (như loại dữ liệu).
- **Body** chứa dữ liệu thực tế (thường trong các yêu cầu POST hoặc PUT).

## 4. Ví dụ RESTful API cơ bản
Giả sử có một RESTful API quản lý người dùng với các endpoint sau:
- **GET /users**: Lấy danh sách người dùng.
- **GET /users/{id}**: Lấy thông tin người dùng cụ thể.
- **POST /users**: Tạo mới người dùng.
  ```json
  {
    "name": "John Doe",
    "email": "john@example.com"
  }
  ```
- **PUT /users/{id}**: Cập nhật người dùng.
  ```json
  {
    "name": "John Updated",
    "email": "john.updated@example.com"
  }
  ```
- **DELETE /users/{id}**: Xóa người dùng.

## 5. Lợi ích và nhược điểm của REST
### Lợi ích
- **Dễ hiểu và sử dụng**: Tuân theo các phương thức HTTP chuẩn.
- **Độc lập công nghệ**: REST hoạt động tốt với nhiều ngôn ngữ.
- **Dễ mở rộng và bảo trì**: Tính chất không trạng thái và giao diện thống nhất giúp dễ bảo trì.
- **Khả năng cache**: Tăng hiệu năng nhờ caching.

### Nhược điểm
- **Thiếu trạng thái**: Mỗi yêu cầu đều độc lập, có thể gây khó khăn trong tình huống phức tạp.
- **Giới hạn trong HTTP**: REST phụ thuộc chủ yếu vào HTTP, không linh hoạt với giao thức khác.

## 6. RESTful vs SOAP
- **REST**:
  - Nhẹ, không trạng thái.
  - Dữ liệu trao đổi thường dùng JSON.
  - Dễ triển khai.
- **SOAP**:
  - Phức tạp hơn, sử dụng XML.
  - Hỗ trợ nhiều tính năng mạnh như bảo mật, giao dịch.

---

RESTful API là tiêu chuẩn phổ biến cho xây dựng các dịch vụ web nhờ sự đơn giản, linh hoạt và khả năng mở rộng.
