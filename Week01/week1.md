# 🔥NỘI DUNG BÀI LÀM: QUẢN LÝ TÀI KHOẢN

-   Mô tả bài toán: Quản lý tài khoản người dùng có kiểm tra tài khoản mật khẩu. Hợp lệ thì đăng nhập thành công, sau đó có thể thực hiện các thao tác thêm, cập nhật, xóa tài khoản và lọc tài khoản theo quyền,...

-   Gồm 4 package:

## 1.**controllers**: dùng xử lý yêu cầu người dùng, tương tác với model và chọn view hiển thị

-   ControllerServlet

## 2.**entities**: chứa các entity

-   Account (accountId, fullName, password, email, phone, status)
-   GrantAccess (role, account, isGrant, note)
-   GrantAccessId (roleId, accountId)
-   Log (id, accountId, loginTime, logoutTime, notes)
-   Role (roleId, roleName, description, status)

## 3.**repositories**: dùng nối lớp model và cơ sở dữ liệu, cung cấp các phương thức thực hiện từ cơ sở dữ liệu

-   AccountRepository
-   GrantAccessRepository
-   LogRepository
-   RoleRepository

## 4.**services**: chứa logic nghiệp vụ, trung gian giữa controllers và repositories

-   AccountService
-   RoleService

# 🔥LÝ THUYẾT

# Servlet trong Java

**Servlet** là một thành phần quan trọng trong lập trình web với Java. Đây là một chương trình được viết bằng Java, chạy trên máy chủ để xử lý các yêu cầu từ client (thường là trình duyệt web) và gửi lại phản hồi. Servlet là nền tảng cơ bản để tạo các ứng dụng web động trong Java, thường được triển khai trong các máy chủ ứng dụng (như Tomcat, Jetty hoặc các máy chủ ứng dụng khác hỗ trợ Java).

## 1. Khái niệm cơ bản về Servlet:

-   Servlet hoạt động dựa trên mô hình **yêu cầu - phản hồi** (request-response).
-   Khi một client gửi yêu cầu tới server, máy chủ sẽ chuyển tiếp yêu cầu này tới một Servlet cụ thể để xử lý. Servlet có thể truy xuất dữ liệu từ yêu cầu, xử lý logic, và sau đó trả về kết quả dưới dạng HTML, JSON, hoặc bất kỳ định dạng nào khác.

## 2. Chu kỳ sống của Servlet (Servlet Life Cycle):

Servlet có một chu kỳ sống bao gồm các giai đoạn sau:

1. **Tải và khởi tạo (Loading & Initialization):**
    - Servlet được tải vào bộ nhớ khi ứng dụng web được triển khai hoặc khi Servlet được gọi lần đầu tiên.
    - **Phương thức `init(ServletConfig config)`**: Được gọi chỉ một lần khi Servlet được khởi tạo. Nó được dùng để thực hiện các bước thiết lập ban đầu (như kết nối cơ sở dữ liệu).
2. **Xử lý yêu cầu (Request Handling):**
    - **Phương thức `service(HttpServletRequest request, HttpServletResponse response)`**: Được gọi mỗi khi có một yêu cầu mới tới Servlet. Phương thức này xác định loại yêu cầu (GET, POST, PUT, DELETE, v.v.) và gọi các phương thức tương ứng như `doGet()`, `doPost()`.
    - Ví dụ:
        ```java
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Xử lý yêu cầu GET
        }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Xử lý yêu cầu POST
        }
        ```
3. **Hủy (Destruction):**
    - **Phương thức `destroy()`**: Được gọi khi Servlet bị hủy (thường là khi ứng dụng web ngừng hoạt động hoặc Servlet được gỡ bỏ khỏi bộ nhớ). Đây là nơi để dọn dẹp tài nguyên.

## 3. Các lớp và giao diện chính của Servlet:

-   **`javax.servlet.Servlet`**: Đây là giao diện cơ bản mà tất cả các Servlet phải triển khai.
-   **`javax.servlet.http.HttpServlet`**: Đây là lớp cơ bản phổ biến nhất dành cho các Servlet xử lý giao thức HTTP. Thông thường, các Servlet sẽ mở rộng lớp này thay vì trực tiếp triển khai `Servlet`.

## 4. Cấu trúc một Servlet cơ bản:

Dưới đây là một ví dụ đơn giản về cách tạo và triển khai một Servlet:

```java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Annotation để định nghĩa URL mà Servlet sẽ xử lý
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Xử lý yêu cầu GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello, World! Đây là Servlet đơn giản!</h1>");
    }
}
```

-   **`@WebServlet("/hello")`**: Định nghĩa URL mà Servlet sẽ phản hồi.
-   **`doGet()`**: Phương thức xử lý yêu cầu HTTP GET.

## 5. Quản lý session và cookie trong Servlet:

-   **Session** là cách để lưu trữ thông tin người dùng trong một khoảng thời gian hoạt động, giúp quản lý các trạng thái người dùng như giỏ hàng, đăng nhập,...
    -   **`HttpSession session = request.getSession();`**: Lấy hoặc tạo mới một phiên làm việc.
    -   **`session.setAttribute("key", value);`**: Lưu dữ liệu vào session.
-   **Cookies** cũng có thể được sử dụng để lưu trữ thông tin người dùng trên trình duyệt.
    -   **Tạo cookie**:
        ```java
        Cookie cookie = new Cookie("username", "john");
        response.addCookie(cookie);
        ```
    -   **Đọc cookie**:
        ```java
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                // Lấy giá trị cookie
            }
        }
        ```

## 6. Triển khai Servlet trong web.xml:

Trước khi có các annotation như `@WebServlet`, các Servlet cần được định nghĩa trong tệp cấu hình `web.xml`:

```xml
<servlet>
    <servlet-name>HelloWorld</servlet-name>
    <servlet-class>HelloWorldServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>HelloWorld</servlet-name>
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

## 7. Các tính năng nâng cao:

-   **Servlet Filters**: Cho phép xử lý yêu cầu hoặc phản hồi trước hoặc sau khi Servlet xử lý. Thường dùng cho việc xác thực, nén dữ liệu hoặc ghi log.
-   **Forward và Include**: Dùng để chuyển tiếp yêu cầu tới một Servlet khác hoặc bao gồm nội dung từ một nguồn khác (chẳng hạn JSP).
    -   **Forward**:
        ```java
        request.getRequestDispatcher("/otherServlet").forward(request, response);
        ```
    -   **Include**:
        ```java
        request.getRequestDispatcher("/header.jsp").include(request, response);
        ```

## 8. Ứng dụng thực tế của Servlet:

Servlet thường được sử dụng trong việc xây dựng các ứng dụng web động, quản lý session, xử lý biểu mẫu web, thực hiện xác thực, và tạo giao diện động bằng cách tích hợp với JSP hoặc các framework khác. Dù các công nghệ như Spring Boot giúp đơn giản hóa lập trình web với Java, Servlet vẫn đóng vai trò cơ bản và là nền tảng quan trọng cho các ứng dụng Java web.
